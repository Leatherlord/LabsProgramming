package commands;

import additionals.SpaceMarine;
import program.commands.Commands;

import java.io.Serializable;
import java.util.LinkedList;

public class SortCommand implements Command, Serializable {

    private final String filename;
    private String message;

    public SortCommand(String filename) {
        this.filename = filename;
    }

    @Override
    public void execute(LinkedList<SpaceMarine> collection) {
        Commands commands = new Commands();
        commands.sort(collection);
        this.message = "Successfully sorted";
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
        return "SortCommand";
    }
}
