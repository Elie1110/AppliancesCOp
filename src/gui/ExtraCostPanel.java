package gui;

import javax.swing.*;

import model.ExtraCost;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExtraCostPanel extends JPanel {
    private DefaultListModel<ExtraCost> extraCostListModel;
    private JList<ExtraCost> extraCostList;
    private JTextField nameField, costField;

    public ExtraCostPanel() {
        setLayout(new BorderLayout());

        extraCostListModel = new DefaultListModel<>();
        extraCostList = new JList<>(extraCostListModel);
        add(new JScrollPane(extraCostList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        nameField = new JTextField();
        costField = new JTextField();

        inputPanel.add(new JLabel("Extra Cost Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Cost:"));
        inputPanel.add(costField);

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Extra Cost");
        JButton deleteButton = new JButton("Delete Extra Cost");

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
                    if (cost < 0) {
                        JOptionPane.showMessageDialog(ExtraCostPanel.this, "Cost must be positive.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ExtraCostPanel.this, "Invalid cost.");
                    return;
                }
                extraCostListModel.addElement(new ExtraCost(name, cost));
                nameField.setText("");
                costField.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExtraCost selectedExtraCost = extraCostList.getSelectedValue();
                if (selectedExtraCost != null) {
                    extraCostListModel.removeElement(selectedExtraCost);
                }
            }
        });
    }

    public DefaultListModel<ExtraCost> getExtraCostListModel() {
        return extraCostListModel;
    }
}
