package exception;

public class InCorrectArgumentException extends Exception {
    private final String argument;

    public InCorrectArgumentException(String argument) {
        this.argument = argument;
    }

    @Override
    public String getMessage() {
        return "Параметр " + argument + " задан неверно!";
    }
}
