package ru.pashintsev.tm.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Tasks {
    private final List<Task> taskList;

    public Tasks () {
        this.taskList = new ArrayList<>();
    }

    public void addTask(String taskName) {
        taskList.add(new Task(taskName));
        taskList.get(taskList.size()-1).setTaskUuid(UUID.randomUUID());
    }

    public void removeTaskById(int id) {
        taskList.remove(id);
    }

    public List<Task> showAllTasks() {
        return taskList;
    }

    public void deleteAllTasks() {
        taskList.clear();
    }

    public Task getTask() {return taskList.get(taskList.size()-1);}

}
