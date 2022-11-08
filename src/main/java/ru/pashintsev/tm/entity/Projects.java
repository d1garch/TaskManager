package ru.pashintsev.tm.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Projects {
    private final List<Project> projectList;


    public Projects() {
        this.projectList = new ArrayList<>();
    }

    public void addProject(String projectName) {
        projectList.add(new Project(projectName));
        projectList.get(projectList.size()-1).setUuid(UUID.randomUUID());
    }

    public void removeProjectById(int id) {
        projectList.remove(id);
    }

    public List<Project> showAllProjects() {
        return projectList;
    }

    public void deleteAllProjects() {
        projectList.clear();
    }

    public Project getProject(int index) {
        return projectList.get(index-1);
    }


}
