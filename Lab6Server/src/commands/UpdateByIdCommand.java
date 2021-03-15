package commands;

import additionals.Chapter;
import program.commands.Commands;
import additionals.SpaceMarine;

import java.io.Serializable;
import java.util.LinkedList;

public class UpdateByIdCommand implements Command, Serializable {

    private final String name;
    private final Double health;
    private final String achievements;
    private final Long id;
    private final Double x;
    private final Double y;
    private final String category;
    private final String weapon;
    private final Chapter chapter;
    private final String filename;
    private String message;

    public UpdateByIdCommand(String name, Double health, String achievements, Long id, Double x, Double y, String category, String weapon, Chapter chapter, String filename) {
        this.name = name;
        this.health = health;
        this.achievements = achievements;
        this.id = id;
        this.x = x;
        this.y = y;
        this.category = category;
        this.weapon = weapon;
        this.chapter = chapter;
        this.filename = filename;
    }

    @Override
    public void execute(LinkedList<SpaceMarine> collection) {
        Commands commands = new Commands();
        commands.updateById(collection, this.id, this.name, this.health, this.achievements, this.x, this.y, this.category, this.weapon, this.chapter);
        this.message = "Successfully updated";
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
        return "UpdateByIdCommand";
    }
}
