package se.edu.streamdemo;

import se.edu.streamdemo.data.DataManager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager");
        DataManager dataManager = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

//        System.out.println("Printing all data ...");
//        printAllData(tasksData);
//
//        System.out.println("Printing deadlines ...");
//        printDeadlines(tasksData);

        printDataWithStreams(tasksData);
        printDeadlinesWithStreams(tasksData);

//        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println(countDeadlinesWithStream(tasksData));

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesWithStream(ArrayList<Task> tasks) {
        int count =  (int)tasks.stream()
                .filter((t) -> t instanceof Deadline )
                .count();
        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataWithStreams(ArrayList<Task> tasks) {
        System.out.println("Print with streams");
        tasks.stream()
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesWithStreams(ArrayList<Task> tasks) {
        System.out.println("Print deadlines with streams");
        tasks.stream()
                .filter((t) -> t instanceof Deadline )
                .forEach(System.out::println);
    }

}
