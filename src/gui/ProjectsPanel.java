package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectsPanel extends JPanel {
    private MainApp mainApp;
    private DefaultListModel<Project> projectListModel;
    private JList<Project> projectList;
    private JTextField projectNameField;
    private JButton addButton, deleteButton, selectButton;

    public ProjectsPanel(MainApp mainApp) {
        this.mainApp = mainApp;
        setLayout(new BorderLayout());

        projectListModel = new DefaultListModel<>();
        projectList = new JList<>(projectListModel);

        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        projectNameField = new JTextField();
        addButton = new JButton("Add Project");
        deleteButton = new JButton("Delete Project");
        selectButton = new JButton("Select Project");

        inputPanel.add(new JLabel("Project Name:"));
        inputPanel.add(projectNameField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(selectButton);

        add(new JScrollPane(projectList), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addProject());
        deleteButton.addActionListener(e -> deleteProject());
        selectButton.addActionListener(e -> selectProject());
    }

    private void addProject() {
        String name = projectNameField.getText().trim();
        if (!name.isEmpty()) {
            projectListModel.addElement(new Project(name));
            projectNameField.setText("");
        }
    }

    private void deleteProject() {
        int selectedIndex = projectList.getSelectedIndex();
        if (selectedIndex != -1) {
            projectListModel.remove(selectedIndex);
        }
    }

    private void selectProject() {
        Project selectedProject = projectList.getSelectedValue();
        if (selectedProject != null) {
            mainApp.addProjectTabs();
        }
    }
}
