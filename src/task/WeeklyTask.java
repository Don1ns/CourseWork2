package task;

import exception.InCorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task{
    public WeeklyTask(String title, String description, Type type, LocalDateTime dateTime) throws InCorrectArgumentException {
        super(title, description, type, dateTime);
    }
    @Override
    public boolean appearsIn(LocalDate date) {
        return date.isAfter(getDateTime().toLocalDate()) || date.getDayOfWeek() == getDateTime().toLocalDate().getDayOfWeek();
    }
}
