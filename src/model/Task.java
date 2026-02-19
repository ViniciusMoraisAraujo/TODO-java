package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task implements Comparable<Task> {
    private String name;
    private String description;
    private LocalDateTime deadline;
    private int priority;
    private String category;
    private Status status;
    
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public Task(String name, String description, LocalDateTime deadline, int priority, String category, Status status) {
        this.name = name != null ? name.replace(";", ",") : "";
        this.description = description != null ? description.replace(";", ",") : "";
        this.deadline = deadline;
        this.priority = priority;
        this.category = category != null ? category.replace(";", ",") : "";
        this.status = status;
    }

    public String getName() { return name; }
    public int getPriority() { return priority; }
    public String getCategory() { return category; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public LocalDateTime getDeadline() { return deadline; } 



    @Override
    public int compareTo(Task task) {
        return Integer.compare(task.priority, this.priority);
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s | Prioridade: %d | Categoria: %s | Prazo: %s | Desc: %s",
                status, name, priority, category, deadline.format(FORMATTER), description);
    }

    public String toFileFormat() {
        return name + ";" + description + ";" + deadline.format(FORMATTER) + ";" + priority + ";" + category + ";" + status;
    }

    public static Task fromFileFormat(String line) {
        String[] parts = line.split(";");
        if (parts.length < 6) return null;

        return new Task(
                parts[0],                       // name
                parts[1],                       // description
                LocalDateTime.parse(parts[2], FORMATTER),                       // deadline
                Integer.parseInt(parts[3]),     // priority 
                parts[4],                       // category
                Status.valueOf(parts[5])        // status
        );
    }
}
