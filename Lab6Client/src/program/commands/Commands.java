package program.commands;

import additionals.AstartesCategory;
import additionals.Chapter;
import additionals.MeleeWeapon;
import additionals.SpaceMarine;
import program.csvparse.InfoGetter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.stream.Collectors;

import static java.lang.System.out;


public class Commands {

    public String help() {
        return ("GUIDE\n" +
                "\n" +
                "help: display help for available commands\n" +
                "\n" +
                "info: print information about the collection to standard output\n" +
                "\n" +
                "show: display all elements of the collection in String representation to standard output\n" +
                "\n" +
                "add {element}: add a new element to the collection\n" +
                "** Instead of {element} put the name, health (numeric value) and achievements - than follow the instructions. If the name\n" +
                "contains more than 1 word, write them using \"_\" as blank space. Multiple achievements are preferably written\n" +
                "using \"/\" as separation symbol. **\n" +
                "\n" +
                "Example:\n" +
                "\n" +
                "add Van_Darkholme 300 Dungeonmaster/Leatherman/Dominant\n" +
                "\n" +
                "\n" +
                "update id {element}: update the value of the collection element which id is equal to the given\n" +
                "** Instead of {element} put the name, health (numeric value) and achievements - than follow the instructions. If the name\n" +
                "contains more than 1 word, write them using \"_\" as blank space. Multiple achievements are preferably written\n" +
                "using \"/\" as separation symbol. ID is numeric value. **\n" +
                "\n" +
                "Example:\n" +
                "\n" +
                "update 300 Van_Darkholme 300 Dungeonmaster/Leatherman/Dominant\n" +
                "\n" +
                "\n" +
                "remove_by_id id: remove an item from the collection by its id\n" +
                "** ID is numeric value. **\n" +
                "\n" +
                "clear: clear the collection\n" +
                "\n" +
                "save: save the collection to the data-file\n" +
                "\n" +
                "execute_script file_name: read and execute a script from the specified file.\n" +
                "** The script contains commands in the same form in which user enters them interactively. **\n" +
                "\n" +
                "exit: exit the program (without saving to file)\n" +
                "** Needs your permission when used from standard input. **\n" +
                "\n" +
                "remove_last: remove the last item from the collection\n" +
                "\n" +
                "shuffle: shuffle collection items in random order\n" +
                "\n" +
                "sort: sort the collection in natural order\n" +
                "** Elements are sorted with their names as an argument. **\n" +
                "\n" +
                "count_by_melee_weapon meleeWeapon: display the number of elements whose\n" +
                "meleeWeapon field is equal to the given one\n" +
                "\n" +
                "count_greater_than_category category: display the number of items whose\n" +
                "category field value is greater than the specified\n" +
                "** Enum constants are compared in standard way. Null value is less than any other. **\n" +
                "\n" +
                "print_descending: print collection items in descending order");
    }

    public String info(LinkedList<SpaceMarine> collection) {
        String message = ("Collection type: LinkedList\n" +
                "Number of elements: " + collection.size() + "\n" +
                "Names of elements: \n");
        sort(collection);
        message += collection.stream().map(SpaceMarine::getName).collect(Collectors.joining("\n"));
        return message;
    }

    public String show(LinkedList<SpaceMarine> collection) {
        sort(collection);
        return "\n" + collection.stream().map(InfoGetter::toString).collect(Collectors.joining("\n"));
    }

    public void add(LinkedList<SpaceMarine> collection, String name, Double health, String achievements, Double x, Double y, String category, String weapon, Chapter chapter) {

        long maxId = (collection.size() == 0) ? 1 : collection.stream().mapToLong(SpaceMarine::getId).max().getAsLong() + 1;
        SpaceMarine marine = new SpaceMarine(maxId, new Date());
        collection.add(marine);
        updateById(collection, marine.getId(), name, health, achievements, x, y, category, weapon, chapter);
        if (marine.getName() == null) {
            collection.remove(marine);
        }

    }

    public void updateById(LinkedList<SpaceMarine> collection, Long id, String name, Double health, String achievements, Double x, Double y, String category, String weapon, Chapter chapter) {

        collection.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .ifPresent(s -> {
                    s.setName(name);
                    s.setHealth(health);
                    s.setAchievements(achievements);
                    s.setCoordinates(x, y);
                    s.setCategory(category);
                    s.setMeleeWeapon(weapon);
                    s.setChapter(chapter);
                });
    }

    public void removeById(LinkedList<SpaceMarine> collection, Long id) {

        collection.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .ifPresent(collection::remove);

    }

    public void clearCollection(LinkedList<SpaceMarine> collection) {
        collection.clear();
    }

    public void save(LinkedList<SpaceMarine> collection, String filename) {
        File file = new File(filename);
        if (!file.canWrite()) {
            out.println("There is no permission to write in data-file");
            System.exit(1);
        }
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            collection.forEach(i -> writer.println(i.getId() + "," + i.getCreationDate().getTime() + "," + i.getName().replaceAll("\\\\", "\\\\\\\\").replaceAll(",", "\\\\,") + "," + i.getCoords().getX() + "," + i.getCoords().getY() + "," +
                    i.getHealth() + "," + i.getAchievements().replaceAll("\\\\", "\\\\\\\\").replaceAll(",", "\\\\,") + "," + i.getCategory() + "," + i.getMeleeWeapon()
                    + "," + i.getChapter().getName().replaceAll("\\\\", "\\\\\\\\").replaceAll(",", "\\\\,")));
            writer.close();
        } catch (FileNotFoundException e) {
            out.println("Could not find your database, check your environment variable 'TEMP' to be equal\n" +
                    "to path to database or check its position");
        }
    }

    public void removeLast(LinkedList<SpaceMarine> collection) {
        collection.removeLast();
    }

    public void shuffle(LinkedList<SpaceMarine> collection) {
        Collections.shuffle(collection);
    }

    public void sort(LinkedList<SpaceMarine> collection) {
        Collections.sort(collection);
    }

    public int countWeapon(LinkedList<SpaceMarine> collection, MeleeWeapon weapon) {

        return (int) collection.stream()
                .filter(s -> s.getMeleeWeapon().equals(weapon))
                .count();

    }

    public int countGCategory(LinkedList<SpaceMarine> collection, AstartesCategory category) {

        return (int) collection.stream()
                .filter(s -> s != null &&s.getCategory().compareTo(category) > 0)
                .count();
    }

    public String descending(LinkedList<SpaceMarine> collection) {
        LinkedList<SpaceMarine> reversed = new LinkedList<>(collection);
        Collections.sort(reversed);
        Collections.reverse(reversed);
        return show(reversed);
    }
}
