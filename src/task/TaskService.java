package task;

import exception.TaskNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private final Map<Integer, Task> taskMap = new HashMap<>();
    private Collection<Task> removedTasks;

    public void add(Task task){
        this.taskMap.put(task.getId(),task);
    }
    public void remove(int taskId) throws TaskNotFoundException {
        if (this.taskMap.containsKey(taskId)){
            this.taskMap.remove(taskId);
        }
        else{
            throw new TaskNotFoundException(taskId);
        }
    }
    public Collection<Task> getAllByDate(LocalDate date){
        Collection<Task> tasksAllByDate = new ArrayList<>();
        for (Task task : taskMap.values()) {

            if (task.appearsIn(date)){
                tasksAllByDate.add(task);
            }
        }
        return tasksAllByDate;
    }
}
