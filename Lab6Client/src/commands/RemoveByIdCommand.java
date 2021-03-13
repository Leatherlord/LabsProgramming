package commands;

import additionals.SpaceMarine;
import program.commands.Commands;

import java.io.Serializable;
import java.util.LinkedList;

public class RemoveByIdCommand implements Command, Serializable {

    private final Long id;
    private final String filename;
    private String message;

    public RemoveByIdCommand(Long id, String filename) {
        this.id = id;
        this.filename = filename;
    }

    @Override
    public void execute(LinkedList<SpaceMarine> collection) {
        Commands commands = new Commands();
        commands.removeById(collection, this.id);
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
