package commands;

import additionals.SpaceMarine;
import program.commands.Commands;

import java.io.Serializable;
import java.util.LinkedList;

public class RemoveLastCommand implements Command, Serializable {

    private final String filename;
    private String message;

    public RemoveLastCommand(String filename) {
        this.filename = filename;
    }

    @Override
    public void execute(LinkedList<SpaceMarine> collection) {
        Commands commands = new Commands();
        commands.removeLast(collection);
        this.message = "Successfully removed";
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getFilename() {
        return this.filename;
    }
}
