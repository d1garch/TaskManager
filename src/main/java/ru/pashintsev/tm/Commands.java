package ru.pashintsev.tm;

import ru.pashintsev.tm.entity.Projects;
import ru.pashintsev.tm.entity.Tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Commands {
    public static void projectCreate(Projects projects, BufferedReader reader) throws IOException {
        System.out.println("[PROJECT CREATE]\nENTER NAME:");
        projects.addProject(reader.readLine());
        System.out.println("ENTER PROJECT DESCRIPTION:");
        projects.getProject(projects.showAllProjects().size()).setDescription(reader.readLine());
        System.out.println("ENTER PROJECT START DATE [DD.MM.YYYY]:");
        projects.getProject(projects.showAllProjects().size()).setBeginDate(stringToDate(reader.readLine()));
        System.out.println("ENTER PROJECT END DATE [DD.MM.YYYY]:");
        projects.getProject(projects.showAllProjects().size()).setEndDate(stringToDate(reader.readLine()));
        System.out.println("[OK]");
    }

    public static void projectRemove(Projects projects, Tasks tasks, BufferedReader reader) throws IOException {
        System.out.println("[PROJECT-REMOVE]");

        if(!projects.showAllProjects().isEmpty()) {
            System.out.println("[PROJECT LIST]");
            for (int i = 0; i < projects.showAllProjects().size(); i++) {
                System.out.println((i+1) + ". " + projects.showAllProjects().get(i).getName() + " - " + projects.showAllProjects().get(i).getUuid());
            }
        }
        else  {
            System.out.println("[PROJECT LIST IS EMPTY]");
            return;
        }
        System.out.println("ENTER PROJECT NUMBER: ");
        int projectIndex = Integer.parseInt(reader.readLine());
        UUID projectUuid = projects.showAllProjects().get(projectIndex-1).getUuid();
        projects.removeProjectById(projectIndex-1);
        for(int i = tasks.showAllTasks().size()-1; i >= 0 ; i--) {
            if(tasks.showAllTasks().get(i).getProjectUuid() == projectUuid) {
                tasks.removeTaskById(i);
            }
        }
        System.out.println("[OK]");
    }

    public static void projectClear(Projects projects) {
        projects.deleteAllProjects();
        System.out.println("[ALL PROJECTS HAS BEEN DELETED]");
    }

    //убрать вывод даты
    public static void projectList(Projects projects) {

        if(!projects.showAllProjects().isEmpty()) {
            System.out.println("[PROJECT LIST]");
            for (int i = 0; i < projects.showAllProjects().size(); i++) {
                System.out.println((i+1) + ". " + projects.showAllProjects().get(i).getName() + " UUID: " + projects.showAllProjects().get(i).getUuid() +
                        " Start project: " + dateToString(projects.showAllProjects().get(i).getBeginDate()) +
                        " End project: " + dateToString(projects.showAllProjects().get(i).getEndDate()));
            }
        }
        else System.out.println("[PROJECT LIST IS EMPTY]");
    }

    public static void showProjectTasks(Projects projects, Tasks tasks, BufferedReader reader) throws IOException {

        UUID projectUuid;
        if(!projects.showAllProjects().isEmpty()) {
            System.out.println("CHOOSE PROJECT:");
            for (int i = 0; i < projects.showAllProjects().size(); i++) {
                System.out.println((i+1) + ". " + projects.showAllProjects().get(i).getName() + " - " + projects.showAllProjects().get(i).getUuid());
            }
            int projectIndex = Integer.parseInt(reader.readLine());
            if(projectIndex <= projects.showAllProjects().size()) {
                projectUuid = projects.showAllProjects().get(projectIndex - 1).getUuid();
            } else {
                System.out.println("THERE IS NO SUCH PROJECT");
                return;
            }
            if(!tasks.showAllTasks().isEmpty()) {
                for(int j = 0; j < tasks.showAllTasks().size(); j++) {
                    if(tasks.showAllTasks().get(j).getProjectUuid() == projectUuid) {
                        System.out.println("-> " + tasks.showAllTasks().get(j).getName());
                    } //else System.out.println("THERE ARE NO TASKS ON THIS PROJECT");
                }
            } else System.out.println("THERE ARE NO ANY TASKS");
        }
        else System.out.println("[PROJECT LIST IS EMPTY]");
    }

    public static void taskCreate(Projects projects, Tasks tasks, BufferedReader reader) throws IOException {
        System.out.println("[TASK CREATE]");
        System.out.println("CHOOSE PROJECT:");
        if(!projects.showAllProjects().isEmpty()) {
            for (int i = 0; i < projects.showAllProjects().size(); i++) {
                System.out.println((i+1) + ". " + projects.showAllProjects().get(i).getName());
            }
            int projectIndex = Integer.parseInt(reader.readLine());
            if(projectIndex > projects.showAllProjects().size()) {
                System.out.println("THERE IS NO SUCH PROJECT");
                return;
            } else {
                System.out.println("ENTER NAME: ");
                tasks.addTask(reader.readLine());
            }
            tasks.getTask().setProjectUuid(projects.getProject(projectIndex).getUuid());
        } else {
            System.out.println("[PROJECT LIST IS EMPTY]");
            return;
        }
        System.out.println("ENTER TASK DESCRIPTION:");
        tasks.getTask().setDescription(reader.readLine());
        System.out.println("ENTER TASK START DATE [DD.MM.YYYY]:");
        tasks.getTask().setBeginDate(stringToDate(reader.readLine()));
        System.out.println("ENTER TASK END DATE [DD.MM.YYYY]:");
        tasks.getTask().setEndDate(stringToDate(reader.readLine()));
        System.out.println("[OK]");
    }

    public static void taskRemove(Tasks tasks, BufferedReader reader) throws IOException {
        System.out.println("[TASK REMOVE]\nENTER TASK NUMBER:");
        int indexTask = Integer.parseInt(reader.readLine());
        tasks.removeTaskById(indexTask-1);
        System.out.println("[OK]");
    }

    public static void taskClear(Tasks tasks) {
        tasks.deleteAllTasks();
        System.out.println("[ALL TASKS HAS BEEN DELETED]");
    }

    public static void taskList(Tasks tasks) {
        System.out.println("[TASK LIST]");
        if(!tasks.showAllTasks().isEmpty()) {
            for (int i = 0; i < tasks.showAllTasks().size(); i++) {
                System.out.println((i+1) + ". " + tasks.showAllTasks().get(i).getName() + " Task UUID: " + tasks.showAllTasks().get(i).getTaskUuid() +
                        " Project UUID: " + tasks.showAllTasks().get(i).getProjectUuid() +
                        " Start task: " + dateToString(tasks.showAllTasks().get(i).getBeginDate()) +
                        " End task: " + dateToString(tasks.showAllTasks().get(i).getEndDate()));
            }
        }
        else System.out.println("[TASK LIST IS EMPTY]");
    }

    public static void exit() {
        System.exit(0);
    }

    public static void help() {
        System.out.println("'project-create' - adds new project to project list\n"+
                "'project-list' - shows all projects in project list\n"+
                "'project-remove' - removes project from project list by number\n"+
                "'project-clear' - clears project list\n"+
                "'task-create' - adds new task to task list\n"+
                "'task-list' - shows all tasks in task list\n"+
                "'task-remove' - removes task from task list by number\n"+
                "'task-clear' - clears task list\n"+
                "'show-project-tasks' - shows tasks in selected project\n"+
                "'exit' - closes application\n"+
                "'help' - shows all available commands");
    }

    private static Date stringToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date date = formatter.parse(strDate);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(date);
    }
}
