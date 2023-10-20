import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Main {
    private static Gestionnaire gestionnaire = new Gestionnaire();
    private static Scanner scanner = new Scanner(System.in);
    private static final String SAVE_FILE = "todo_data.ser";

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    createTask();
                    break;
                case 2:
                    createProject();
                    break;
                case 3:
                    displayAllTasks();
                    break;
                case 4:
                    displayAllProjects();
                    break;
                case 5:
                    deleteTask();
                    break;
                case 6:
                     deleteProject();
                    break;

                // ... other cases will be added for remaining functionalities ...
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        loadData(); 
        saveData(); 
    }

    private static void displayMenu() {
        System.out.println("\n+--------------------------------+");
        System.out.println("| Welcome to the Todo List App!  |");
        System.out.println("+--------------------------------+");
        System.out.println("| 1. Add Task                    |");
        System.out.println("| 2. Add Project                 |");
        System.out.println("| 3. View Tasks                  |");
        System.out.println("| 4. View Projects               |");
        System.out.println("| 5. Delete Task                 |");
        System.out.println("| 6. Delete Project              |");

        // ... other options will be added ...
        System.out.println("| 8. Exit                        |");
        System.out.println("+--------------------------------+");
        System.out.print("Enter your choice: ");
    }


    private static void createTask() {
    System.out.println("\n========== ADD NEW TASK ==========");
    System.out.print("Enter task title: ");
    String title = scanner.nextLine();
    System.out.print("Enter task description: ");
    String description = scanner.nextLine();
    
    LocalDate dueDate = null;
    while (dueDate == null) {
        System.out.print("Enter due date (dd/MM/yyyy): ");
        String dateString = scanner.nextLine();
        dueDate = parseDate(dateString);
        if (dueDate == null) {
            System.out.println("Invalid date format! Please use dd/MM/yyyy.");
        }
    }
    
    System.out.print("Enter task priority (1: High, 2: Medium, 3: Low): ");
    int priority = scanner.nextInt();

    Tâche newTask = new Tâche(title, description, dueDate, priority);
    gestionnaire.ajouterElement(newTask);
    System.out.println("Task added successfully!");
    
}

private static LocalDate parseDate(String dateString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    try {
        return LocalDate.parse(dateString, formatter);
    } catch (DateTimeParseException e) {
        return null;
    }
}

    private static void createProject() {
        System.out.println("\n========== ADD NEW PROJECT ==========");
        System.out.print("Enter project title: ");
        String title = scanner.nextLine();
        System.out.print("Enter project description: ");
        String description = scanner.nextLine();

        Projet newProject = new Projet(title, description);
        gestionnaire.ajouterElement(newProject);
        System.out.println("Project added successfully!");
    }

    private static void displayAllTasks() {
        System.out.println("\n========== DISPLAYING ALL TASKS ==========");
        gestionnaire.afficherToutesLesTaches();
    }

    private static void displayAllProjects() {
        System.out.println("\n========== DISPLAYING ALL PROJECTS ==========");
        gestionnaire.afficherTousLesProjets();
    }    

    private static void deleteTask() {
    System.out.println("\n========== DELETE TASK ==========");
    List<Tâche> tasks = gestionnaire.getToutesLesTaches();
    if (tasks.isEmpty()) {
        System.out.println("No tasks available to delete.");
        return;
    }
    for (int i = 0; i < tasks.size(); i++) {
        System.out.println((i + 1) + ". " + tasks.get(i).getTitre());
    }
    System.out.print("Enter the number of the task to delete: ");
    int index = scanner.nextInt() - 1;
    if (index >= 0 && index < tasks.size()) {
        gestionnaire.supprimerElement(index);
        System.out.println("Task deleted successfully!");
    } else {
        System.out.println("Invalid choice!");
    }
}

private static void deleteProject() {
    System.out.println("\n========== DELETE PROJECT ==========");
    List<Projet> projects = gestionnaire.getTousLesProjets();
    if (projects.isEmpty()) {
        System.out.println("No projects available to delete.");
        return;
    }
    for (int i = 0; i < projects.size(); i++) {
        System.out.println((i + 1) + ". " + projects.get(i).getTitre());
    }
    System.out.print("Enter the number of the project to delete: ");
    int index = scanner.nextInt() - 1;
    if (index >= 0 && index < projects.size()) {
        gestionnaire.supprimerElement(index);
        System.out.println("Project deleted successfully!");
    } else {
        System.out.println("Invalid choice!");
    }
}

    private static void saveData() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
        oos.writeObject(gestionnaire);
    } catch (IOException e) {
        System.out.println("Error saving data: " + e.getMessage());
    }
}

    private static void loadData() {
    File file = new File(SAVE_FILE);
    if (file.exists()) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            gestionnaire = (Gestionnaire) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}


    // ... other helper methods for the remaining functionalities will be added here ...
}
