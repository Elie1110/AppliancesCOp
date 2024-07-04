package gui;

import javax.swing.*;

import model.Appliance;
import model.Material;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppliancesPanel extends JPanel {
    private DefaultListModel<Appliance> applianceListModel;
    private JList<Appliance> applianceList;
    private JTextField nameField, quantityField;
    private MaterialsPanel materialsPanel;
    private JList<Material> materialsList;

    public AppliancesPanel(MaterialsPanel materialsPanel) {
        this.materialsPanel = materialsPanel;
        setLayout(new BorderLayout());

        applianceListModel = new DefaultListModel<>();
        applianceList = new JList<>(applianceListModel);
        add(new JScrollPane(applianceList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        nameField = new JTextField();
        quantityField = new JTextField();
        materialsList = new JList<>(materialsPanel.getMaterialListModel());
        materialsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        inputPanel.add(new JLabel("Appliance Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("Materials:"));
        inputPanel.add(new JScrollPane(materialsList));

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Appliance");
        JButton deleteButton = new JButton("Delete Appliance");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int quantity;
                try {
                    quantity = Integer.parseInt(quantityField.getText());
                    if (quantity < 0) {
                        JOptionPane.showMessageDialog(AppliancesPanel.this, "Quantity must be positive.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AppliancesPanel.this, "Invalid quantity.");
                    return;
                }
                DefaultListModel<Material> selectedMaterials = new DefaultListModel<>();
                for (Material material : materialsList.getSelectedValuesList()) {
                    selectedMaterials.addElement(material);
                }
                applianceListModel.addElement(new Appliance(name, quantity, selectedMaterials));
                nameField.setText("");
                quantityField.setText("");
                materialsList.clearSelection();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Appliance selectedAppliance = applianceList.getSelectedValue();
                if (selectedAppliance != null) {
                    applianceListModel.removeElement(selectedAppliance);
                }
            }
        });
    }

    public DefaultListModel<Appliance> getApplianceListModel() {
        return applianceListModel;
    }
}
