package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Project;
import model.Material;
import model.Employee;
import model.Appliance;
import model.ExtraCost;
import util.SaveData;

public class MainApp extends JFrame {
    private JTabbedPane tabbedPane;
    private MaterialsPanel materialsPanel;
    private EmployeesPanel employeesPanel;
    private AppliancesPanel appliancesPanel;
    private ExtraCostPanel extraCostPanel;
    private FinancialPanel financialPanel;
    
    private DefaultListModel<Project> projects;
    private JList<Project> projectList;
    private JButton addProjectButton, deleteProjectButton, saveButton, loadButton;

    public MainApp() {
        setTitle("Home Appliances Company Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        materialsPanel = new MaterialsPanel();
        employeesPanel = new EmployeesPanel();
        appliancesPanel = new AppliancesPanel(materialsPanel);
        extraCostPanel = new ExtraCostPanel();
        financialPanel = new FinancialPanel(employeesPanel, appliancesPanel, extraCostPanel);

        tabbedPane.addTab("Materials", materialsPanel);
        tabbedPane.addTab("Employees", employeesPanel);
        tabbedPane.addTab("Appliances", appliancesPanel);
        tabbedPane.addTab("Extra Cost", extraCostPanel);
        tabbedPane.addTab("Financial", financialPanel);

        add(tabbedPane, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem loadMenuItem = new JMenuItem("Load");

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveData.saveProjectData(materialsPanel.getMaterialListModel(), employeesPanel.getEmployeeListModel(), appliancesPanel.getApplianceListModel(), extraCostPanel.getExtraCostListModel());
                JOptionPane.showMessageDialog(MainApp.this, "Data saved successfully.");
            }
        });

        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveData.loadProjectData(materialsPanel.getMaterialListModel(), employeesPanel.getEmployeeListModel(), appliancesPanel.getApplianceListModel(), extraCostPanel.getExtraCostListModel());
                JOptionPane.showMessageDialog(MainApp.this, "Data loaded successfully.");
            }
        });

        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainApp().setVisible(true);
            }
        });
    }
}
