package task;

import exception.InCorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task{
    public YearlyTask(String title, String description, Type type, LocalDateTime dateTime) throws InCorrectArgumentException {
        super(title, description, type, dateTime);
    }
    @Override
    public boolean appearsIn(LocalDate date) {
        return date.isAfter(getDateTime().toLocalDate()) || date.getDayOfYear() == getDateTime().toLocalDate().getDayOfYear();
    }
}
