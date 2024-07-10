package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Material;
import model.Project;

public class MaterialsPanel extends JPanel {
    private DefaultListModel<Material> materialListModel;
    private JList<Material> materialList;
    private JTextField nameField, costField;

    public MaterialsPanel() {
        setLayout(new BorderLayout());

        materialListModel = new DefaultListModel<>();
        materialList = new JList<>(materialListModel);
        add(new JScrollPane(materialList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nameField = new JTextField();
        costField = new JTextField();

        inputPanel.add(new JLabel("Material Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Cost/Unit:"));
        inputPanel.add(costField);

        JButton addButton = new JButton("Add New Material");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMaterial();
            }
        });

        JButton deleteButton = new JButton("Delete Material");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMaterial();
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public DefaultListModel<Material> getMaterialListModel() {
        return materialListModel;
    }

    public void setMaterialListModel(DefaultListModel<Material> materialListModel) {
        this.materialListModel = materialListModel;
        materialList.setModel(materialListModel);
    }

    private void addMaterial() {
        String name = nameField.getText().trim();
        String costStr = costField.getText().trim();

        if (name.isEmpty() || costStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both Material Name and Cost.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double cost;
        try {
            cost = Double.parseDouble(costStr);
            if (cost < 0 || cost > 12) {
                JOptionPane.showMessageDialog(this, "Cost must be between 0 and 12.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid cost format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Material material = new Material(name, cost);
        materialListModel.addElement(material);
        clearInputFields();
    }

    private void deleteMaterial() {
        int selectedIndex = materialList.getSelectedIndex();
        if (selectedIndex != -1) {
            materialListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a material to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInputFields() {
        nameField.setText("");
        costField.setText("");
    }
}
