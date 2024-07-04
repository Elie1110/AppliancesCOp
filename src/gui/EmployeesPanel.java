package gui;

import javax.swing.*;

import model.Employee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeesPanel extends JPanel {
    private DefaultListModel<Employee> employeeListModel;
    private JList<Employee> employeeList;
    private JTextField nameField, hoursField;
    private JComboBox<String> departmentComboBox;

    public EmployeesPanel() {
        setLayout(new BorderLayout());

        employeeListModel = new DefaultListModel<>();
        employeeList = new JList<>(employeeListModel);
        add(new JScrollPane(employeeList), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        nameField = new JTextField();
        hoursField = new JTextField();
        departmentComboBox = new JComboBox<>(new String[]{"HR", "Cleaners", "IT", "Production"});

        inputPanel.add(new JLabel("Employee Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Hours Worked:"));
        inputPanel.add(hoursField);
        inputPanel.add(new JLabel("Department:"));
        inputPanel.add(departmentComboBox);

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Employee");
        JButton deleteButton = new JButton("Delete Employee");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int hoursWorked;
                try {
                    hoursWorked = Integer.parseInt(hoursField.getText());
                    if (hoursWorked < 0) {
                        JOptionPane.showMessageDialog(EmployeesPanel.this, "Hours worked must be positive.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EmployeesPanel.this, "Invalid hours worked.");
                    return;
                }
                String department = (String) departmentComboBox.getSelectedItem();
                double salaryPerHour = getSalaryPerHour(department);
                double salary = salaryPerHour * hoursWorked;
                employeeListModel.addElement(new Employee(name, department, hoursWorked, salary));
                nameField.setText("");
                hoursField.setText("");
                departmentComboBox.setSelectedIndex(0);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee selectedEmployee = employeeList.getSelectedValue();
                if (selectedEmployee != null) {
                    employeeListModel.removeElement(selectedEmployee);
                }
            }
        });
    }

    private double getSalaryPerHour(String department) {
        switch (department) {
            case "HR":
                return 10.0;
            case "Cleaners":
                return 4.0;
            case "IT":
                return 8.0;
            case "Production":
                return 7.0;
            default:
                return 0.0;
        }
    }

    public DefaultListModel<Employee> getEmployeeListModel() {
        return employeeListModel;
    }
}
