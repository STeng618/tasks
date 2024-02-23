package se.edu.streamdemo;

import se.edu.streamdemo.data.DataManager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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

        ArrayList<Task> filteredList = filterByString(tasksData, "11");
        printAllData(filteredList);

        System.out.println("Printing deadlines before sorting...");
        printDeadlines(tasksData);

        System.out.println("Deadliens after sorting");
        printDeadlinesWithStream(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        printDataWithStreams(tasksData);
        printDeadlinesWithStream(tasksData);

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

    public static void printDeadlinesWithStream(ArrayList<Task> tasks) {
        tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .sorted((t1, t2) -> t1.getDescription().compareToIgnoreCase(t2.getDescription()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterByString(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((task -> task.getDescription().contains(filterString)))
                .collect(toList());
        return  filteredList;
    }

}
