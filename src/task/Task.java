package task;

import exception.InCorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task{
    private static int idGenerator = 1;
    public enum Type {WORK, PERSONAL}
    private String title;
    private final Type type;
    private int id;
    private final LocalDateTime dateTime;
    private String description;

    public Task(String title, String description, Type type, LocalDateTime dateTime) throws InCorrectArgumentException {
        this.type = type;
        this.id = idGenerator;
        this.dateTime = dateTime;
        setTitle(title);
        setDescription(description);
        idGenerator++;
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) throws InCorrectArgumentException {
        if (title != null && !title.isEmpty()){
            this.title = title;
        }
        else{
            throw new InCorrectArgumentException("Заголовок");
        }
    }

    public void setDescription(String description) throws InCorrectArgumentException {
        if (description != null && !description.isEmpty()){
            this.description = description;
        }
        else{
            throw new InCorrectArgumentException("Описание");
        }
    }
    public abstract boolean appearsIn(LocalDate date);

    @Override
    public String toString() {
        return "Task{" +
                "idGenerator=" + idGenerator +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && type == task.type && Objects.equals(dateTime, task.dateTime) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, id, dateTime, description);
    }
}
