package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    private String projectName;
    private String projectDetails;
    private List<Material> materials;

    public Project(String projectName) {
        this.projectName = projectName;
        this.projectDetails = "";
        this.materials = new ArrayList<>();
    }

    public Project(String projectName, String projectDetails) {
        this.projectName = projectName;
        this.projectDetails = projectDetails;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(String projectDetails) {
        this.projectDetails = projectDetails;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void addMaterial(Material material) {
        materials.add(material);
    }

     public DefaultListModel<Material> getMaterialListModel() {
        DefaultListModel<Material> materialListModel = new DefaultListModel<>();
        for (Material material : materials) {
            materialListModel.addElement(material);
        }
        return materialListModel;
    }
    public void removeMaterial(Material material) {
        materials.remove(material);
    }
    @Override
    public String toString() {
        return projectName;
    }
}
