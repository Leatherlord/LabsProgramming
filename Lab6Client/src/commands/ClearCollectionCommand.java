package commands;

import additionals.SpaceMarine;
import program.commands.Commands;

import java.io.Serializable;
import java.util.LinkedList;

public class ClearCollectionCommand implements Command, Serializable {

    private final String filename;
    private String message;

    public ClearCollectionCommand(String filename) {
        this.filename = filename;
    }

    @Override
    public void execute(LinkedList<SpaceMarine> collection) {
        Commands commands = new Commands();
        commands.clearCollection(collection);
        this.message ="Successfully cleared";
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
        return "ClearCollectionCommand";
    }
}
