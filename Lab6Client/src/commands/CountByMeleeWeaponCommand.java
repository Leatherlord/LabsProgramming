package commands;

import additionals.MeleeWeapon;
import additionals.SpaceMarine;
import program.commands.Commands;

import java.io.Serializable;
import java.util.LinkedList;

public class CountByMeleeWeaponCommand implements Command, Serializable {

    private final MeleeWeapon weapon;
    private final String filename;
    private String message;

    public CountByMeleeWeaponCommand(MeleeWeapon weapon, String filename) {
        this.weapon = weapon;
        this.filename = filename;
    }

    @Override
    public void execute(LinkedList<SpaceMarine> collection) {
        Commands commands = new Commands();
        this.message = "Number of this weapon owners: " + commands.countWeapon(collection, this.weapon);
        commands.countWeapon(collection, this.weapon);
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
