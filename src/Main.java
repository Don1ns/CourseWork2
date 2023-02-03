import exception.InCorrectArgumentException;
import exception.TaskNotFoundException;
import task.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final TaskService TASK_BOOK = new TaskService();
    private static final Pattern DATE_TIME_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}\\:\\d{2}");
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static void main(String[] args){
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            try{
                                inputTask(scanner);
                                break;
                            }catch (InCorrectArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        case 2:
                            removeTask(scanner);
                            break;
                        case 3:
                            printTasksAllByDate(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void removeTask(Scanner scanner) throws TaskNotFoundException {
        System.out.println("Введите id задачи: ");
        int id = scanner.nextInt();

        try{
            TASK_BOOK.remove(id);
        }catch (TaskNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    private static void printTasksAllByDate(Scanner scanner){
        System.out.println("Введите дату в формате dd.MM.yyyy");
        LocalDate taskDate = null;
        if(scanner.hasNext(DATE_PATTERN)) {
            String dateTime = scanner.next();
            taskDate = LocalDate.parse(dateTime, DATE_FORMATTER);
        }
        else{
            System.out.println("Введите дату в формате dd.MM.yyyy");
        }
        System.out.println(TASK_BOOK.getAllByDate(taskDate));

    }
    private static void inputTask(Scanner scanner) throws InCorrectArgumentException {
        scanner.useDelimiter("\n");
        System.out.print("Введите название задачи: ");
        String title = scanner.next();

        System.out.print("Введите описание задачи: ");
        String description = scanner.next();

        System.out.print("Выберите тип задачи (1 - рабочая, 2 - личная) : ");
        Type type = null;
        int typeChoice = scanner.nextInt();
        switch (typeChoice) {
            case 1:
                type = Type.WORK;
                break;
            case 2:
                type = Type.PERSONAL;
                break;
            default:
                System.out.println("Данные введены неверно!");
        }
        System.out.println("Введите дату и время задачи в формате dd.MM.yyyy HH:mm");
        LocalDateTime taskDateTime = null;
        if(scanner.hasNext(DATE_TIME_PATTERN)) {
            String dateTime = scanner.next();
            taskDateTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
        }
        else{
            System.out.println("Введите дату и время задачи в формате dd.MM.yyyy HH:mm");
        }

        System.out.println("Введите повторяемость задачи (1 - однократная, 2 - ежедневная, 3 - еженедельная, 4 - ежемесячная, 5 - ежегодная): ");
        int repeatabilityChoice = scanner.nextInt();
        Task task = null;

        try{
            switch (repeatabilityChoice){
                case 1:
                    task = new OneTimeTask(title, description, type, taskDateTime);
                    break;
                case 2:
                    task = new DailyTask(title, description, type, taskDateTime);
                    break;
                case 3:
                    task = new WeeklyTask(title, description, type, taskDateTime);
                    break;
                case 4:
                    task = new MounthlyTask(title, description, type, taskDateTime);
                    break;
                case 5:
                    task = new YearlyTask(title, description, type, taskDateTime);
                    break;
                default:
                    System.out.println("Данные введены неверно!");
            }
        }catch (InCorrectArgumentException e){
            System.out.println(e.getMessage());
        }


        TASK_BOOK.add(task);
        System.out.println("Задача была добавлена!");
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу\n2. Удалить задачу\n3. Получить задачу на указанный день\n0. Выход");
    }
}