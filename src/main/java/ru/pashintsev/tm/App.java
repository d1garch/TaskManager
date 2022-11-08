package ru.pashintsev.tm;

import ru.pashintsev.tm.entity.Projects;
import ru.pashintsev.tm.entity.Tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App
{
    public static void main( String[] args ) {
        Projects projects = new Projects();
        Tasks tasks = new Tasks();
        System.out.println("**** WELCOME TO TASK MANAGER ****");
        System.out.println("Enter \"help\" to display a list of commands");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try {
                switch (reader.readLine()) {
                    case (Constants.PROJECT_CREATE):
                        Commands.projectCreate(projects, reader);
                        break;
                    case (Constants.PROJECT_REMOVE):
                        Commands.projectRemove(projects, tasks, reader);
                        break;
                    case (Constants.PROJECT_CLEAR):
                        Commands.projectClear(projects);
                        break;
                    case (Constants.PROJECT_LIST):
                        Commands.projectList(projects);
                        break;
                    case (Constants.SHOW_PROJECT_TASKS):
                        Commands.showProjectTasks(projects, tasks, reader);
                        break;
                    case (Constants.TASK_CREATE):
                        Commands.taskCreate(projects, tasks, reader);
                        break;
                    case (Constants.TASK_REMOVE):
                        Commands.taskRemove(tasks, reader);
                        break;
                    case (Constants.TASK_CLEAR):
                        Commands.taskClear(tasks);
                        break;
                    case (Constants.TASK_LIST):
                        Commands.taskList(tasks);
                        break;
                    case(Constants.EXIT):
                        Commands.exit();
                        break;
                    case (Constants.HELP):
                        Commands.help();
                        break;
                    default:
                        System.out.println("[UNAVAILABLE COMMAND. ENTER 'help' TO SEE COMMAND LIST]");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
