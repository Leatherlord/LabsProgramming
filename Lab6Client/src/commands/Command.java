package commands;

import additionals.SpaceMarine;

import java.util.LinkedList;

public interface Command {
    void execute(LinkedList<SpaceMarine> collection);
    String getMessage();
    String getFilename();
    String getCommandName();
}
