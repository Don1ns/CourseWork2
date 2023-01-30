package exception;

import task.Task;

public class TaskNotFoundException extends Exception{

    public TaskNotFoundException(Integer taskId) {
        super("Задача " + taskId + " не найдена");
    }
}
