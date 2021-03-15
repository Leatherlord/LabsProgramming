package commands;

import additionals.AstartesCategory;
import program.commands.Commands;
import additionals.SpaceMarine;

import java.io.Serializable;
import java.util.LinkedList;

public class CountGreaterThanCategoryCommand implements Command, Serializable {

    private final AstartesCategory category;
    private final String filename;
    private String message;

    public CountGreaterThanCategoryCommand(AstartesCategory category, String filename) {
        this.category = category;
        this.filename = filename;
    }

    @Override
    public void execute(LinkedList<SpaceMarine> collection) {
        Commands commands = new Commands();
        this.message = "Number of items with category greater than that: " + commands.countGCategory(collection, this.category);
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
        return "CountGreaterThanCategoryCommand";
    }
}
