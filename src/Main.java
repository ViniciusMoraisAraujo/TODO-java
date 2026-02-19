import model.Status;
import model.Task;
import service.TaskService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static TaskService service = new TaskService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao ZG-Hero TODO List!");
        boolean running = true;

        while (running) {
            printMenu();
            String option = scanner.nextLine();

            switch (option) {
                case "1": createTask(); break;
                case "2": listAllTasks(); break;
                case "3": listByCategory(); break;
                case "4": listByStatus(); break;
                case "5": deleteTask(); break;
                case "6": updateStatus(); break;
                case "0":
                    running = false;
                    System.out.println("Saindo e salvando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            System.out.println("----------------------------------------");
        }
    }

    private static void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Adicionar Tarefa");
        System.out.println("2. Listar Todas (Ordenadas por Prioridade)");
        System.out.println("3. Listar por Categoria");
        System.out.println("4. Listar por Status");
        System.out.println("5. Remover Tarefa");
        System.out.println("6. Atualizar Status Tarefa");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void createTask() {
        System.out.print("Nome da Tarefa: ");
        String name = scanner.nextLine();

        System.out.print("Descrição: ");
        String desc = scanner.nextLine();

        System.out.print("Prazo (ex: 20/12/2024): ");
        String date = scanner.nextLine();

        int priority = 1;
        while (true) {
            System.out.print("Prioridade (1-Baixa a 5-Alta): ");
            try {
                priority = Integer.parseInt(scanner.nextLine());
                if (priority >= 1 && priority <= 5) break;
                System.out.println("Por favor, digite um número entre 1 e 5.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
            }
        }

        System.out.print("Categoria (Trabalho, Estudo, Casa): ");
        String category = scanner.nextLine();

        Task newTask = new Task(name, desc, date, priority, category, Status.TODO);
        service.addTask(newTask);
        System.out.println("Tarefa adicionada com sucesso!");
    }

    private static void listAllTasks() {
        List<Task> tasks = service.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            tasks.forEach(System.out::println);
        }
        waitForEnter();
    }

    private static void listByCategory() {
        System.out.print("Digite a categoria: ");
        String category = scanner.nextLine();
        List<Task> tasks = service.getTasksByCategory(category);
        tasks.forEach(System.out::println);
        waitForEnter();
    }

    private static void listByStatus() {
        System.out.println("Escolha o status (1-TODO, 2-DOING, 3-DONE): ");
        String op = scanner.nextLine();
        Status status = Status.TODO;
        if (op.equals("2")) status = Status.DOING;
        else if (op.equals("3")) status = Status.DONE;

        service.getTasksByStatus(status).forEach(System.out::println);
        waitForEnter();
    }

    private static void deleteTask() {
        System.out.print("Nome exato da tarefa a remover: ");
        String name = scanner.nextLine();
        service.removeTask(name);
        System.out.println("Solicitação de remoção processada.");
    }
    
    private static void updateStatus(){
        System.out.print("Nome exato da tarefa: ");
        String name = scanner.nextLine();

        System.out.println("Novo status (1-TODO, 2-DOING, 3-DONE): ");
        String op = scanner.nextLine();
        Status newStatus = Status.TODO;
        if (op.equals("2")) newStatus = Status.DOING;
        else if (op.equals("3")) newStatus = Status.DONE;

        boolean updated = service.updateTaskStatus(name, newStatus);
        if (updated) {
            System.out.println("Status atualizado com sucesso!");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }
    private static void waitForEnter() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}