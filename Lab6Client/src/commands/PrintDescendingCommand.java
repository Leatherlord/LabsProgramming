package commands;

import additionals.SpaceMarine;
import program.commands.Commands;

import java.io.Serializable;
import java.util.LinkedList;

public class PrintDescendingCommand implements Command, Serializable {

    private final String filename;
    private String message;

    public PrintDescendingCommand(String filename) {
        this.filename = filename;
    }

    @Override
    public void execute(LinkedList<SpaceMarine> collection) {
        Commands commands = new Commands();
        this.message = commands.descending(collection);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getFilename() {
        return this.filename;
    }

    public String getCommandName() {
        return "PrintDescendingCommand";
    }
}
