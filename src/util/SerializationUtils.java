package util;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import java.io.*;

import model.Project;

public class SerializationUtils {

    public static void serializeProjects(DefaultListModel<Project> projects, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(projects);
            System.out.println("Projects serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving projects: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static DefaultListModel<Project> deserializeProjects(String filename) {
        DefaultListModel<Project> projects = new DefaultListModel<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            projects = (DefaultListModel<Project>) ois.readObject();
            System.out.println("Projects loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading projects: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return projects;
    }
}
