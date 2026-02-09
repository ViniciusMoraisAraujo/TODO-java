package model;

import java.io.Serializable;

public class Task implements Comparable<Task>, Serializable {
    private String name;
    private String description;
    private String deadline;
    private int priority;
    private String category;
    private Status status;


    public Task(String name, String description, String deadline, int priority, String category, Status status) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.category = category;
        this.status = status;
    }

    public String getName() { return name; }
    public int getPriority() { return priority; }
    public String getCategory() { return category; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }



    @Override
    public int compareTo(Task task) {
        return Integer.compare(this.priority, task.priority);
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s | Prioridade: %d | Categoria: %s | Prazo: %s | Desc: %s",
                status, name, priority, category, deadline, description
        );
    }

    public String toFileFormat() {
        return String.format("%s|%s|%s|%d|%s|%s",
                name, description, deadline, priority, category, status);
    }

    public static Task fromFileFormat(String line) {
        String[] parts = line.split("\\|");
        return new Task(
                parts[0],
                parts[1],
                parts[2],
                Integer.parseInt(parts[3]),
                parts[4],
                Status.valueOf(parts[5])
        );
    }
}
