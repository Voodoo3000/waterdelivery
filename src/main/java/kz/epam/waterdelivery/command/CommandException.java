package kz.epam.waterdelivery.command;

public class CommandException extends Exception {

    public CommandException(){

    }

    public CommandException(String message){

    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
