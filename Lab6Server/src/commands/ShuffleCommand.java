package commands;

import program.commands.Commands;
import additionals.SpaceMarine;

import java.io.Serializable;
import java.util.LinkedList;

public class ShuffleCommand implements Command, Serializable {

    private final String filename;
    private String message;

    public ShuffleCommand(String filename) {
        this.filename = filename;
    }

    @Override
    public void execute(LinkedList<SpaceMarine> collection) {
        Commands commands = new Commands();
        commands.shuffle(collection);
        this.message = "Successfully shuffled";
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
        return "ShuffleCommand";
    }
}
