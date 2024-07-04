package gui;

import javax.swing.*;

import model.Material;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaterialsPanel extends JPanel {
    private DefaultListModel<Material> materialListModel;
    private JList<Material> materialList;
    private JTextField nameField, costField;

    public MaterialsPanel() {
        setLayout(new BorderLayout());

        materialListModel = new DefaultListModel<>();
        materialList = new JList<>(materialListModel);
        add(new JScrollPane(materialList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        nameField = new JTextField();
        costField = new JTextField();

        inputPanel.add(new JLabel("Material Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Cost/Unit:"));
        inputPanel.add(costField);

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Material");
        JButton deleteButton = new JButton("Delete Material");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double cost;
                try {
                    cost = Double.parseDouble(costField.getText());
                    if (cost < 0 || cost > 12) {
                        JOptionPane.showMessageDialog(MaterialsPanel.this, "Cost must be between 0 and 12.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MaterialsPanel.this, "Invalid cost.");
                    return;
                }
                materialListModel.addElement(new Material(name, cost));
                nameField.setText("");
                costField.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Material selectedMaterial = materialList.getSelectedValue();
                if (selectedMaterial != null) {
                    materialListModel.removeElement(selectedMaterial);
                }
            }
        });
    }

    public DefaultListModel<Material> getMaterialListModel() {
        return materialListModel;
    }
}
