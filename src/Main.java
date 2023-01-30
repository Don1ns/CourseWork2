import exception.InCorrectArgumentException;
import task.DailyTask;
import task.Task;
import task.TaskService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws InCorrectArgumentException {
        TaskService taskBook = new TaskService();
        DailyTask cleaningDailyTask = new DailyTask("Уборка","Ежедневная уборка", Task.Type.WORK, LocalDateTime.now());
        taskBook.add(cleaningDailyTask);
    }
}