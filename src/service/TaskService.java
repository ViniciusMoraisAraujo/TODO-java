package service;

import model.Status;
import model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {
    private List<Task> tasks;
    public static final String FILE_NAME = "tasks.txt";

    public TaskService() {
        this.tasks = new ArrayList<>();
        loadTask();
    }

    public void addTask(Task task) {
        tasks.add(task);
        Collections.sort(tasks);
        saveTasks();
    }

    public void removeTask(String taskName) {
        boolean removed = tasks.removeIf(task -> task.getName().equalsIgnoreCase(taskName));
        if(removed) saveTasks();
    }

    public boolean updateTaskStatus(String taskName, Status newStatus) {
        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(taskName)) {
                task.setStatus(newStatus);
                saveTasks(); 
                return true;
            }
        }
        return false;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getTasksByCategory(String category) {
        return tasks.stream()
                .filter(task -> task.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Task> getTasksByStatus(Status status) {
        return tasks.stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing tasks to file" + e.getMessage());
        }
    }

    private void loadTask() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) !=  null){
                if (!line.trim().isEmpty()){
                    Task loadedTask = Task.fromFileFormat(line);
                    if (loadedTask != null) { 
                        tasks.add(loadedTask);
                    }
                }
            }
            Collections.sort(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
