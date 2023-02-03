package task;

import exception.InCorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task{
    public OneTimeTask(String title, String description, Type type, LocalDateTime dateTime) throws InCorrectArgumentException {
        super(title, description, type, dateTime);
    }
    @Override
    public boolean appearsIn(LocalDate date) {
        return date.isEqual(getDateTime().toLocalDate());
    }
}
