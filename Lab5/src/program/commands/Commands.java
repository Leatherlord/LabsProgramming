package program.commands;

import additionals.AstartesCategory;
import additionals.Chapter;
import additionals.MeleeWeapon;
import additionals.SpaceMarine;
import program.csvparse.InfoGetter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

import static java.lang.System.out;

/**
 * The class where all the commands are stored
 */
public class Commands {

    /**
     * The help command. Prints all the information that is important to know about the commands
     *
     * @see CommandReader
     */
    public void help() {
        out.println("GUIDE\n" +
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

    /**
     * The info command. Prints the information about the collection and names of all the objects included
     *
     * @param date       the date of initialization of the collection
     * @param collection the collection which we work with
     * @see CommandReader
     */
    public void info(Date date, LinkedList<SpaceMarine> collection) {

        out.println("Collection type: LinkedList\n" +
                "Initialization date: " + date + "\n" +
                "Number of elements: " + collection.size() + "\n" +
                "Names of elements: ");
        for (SpaceMarine i : collection) {
            out.println(i.getName());
        }
    }

    /**
     * The show command. Shows all the information about each of the elements of the collection
     *
     * @param collection the collection which we work with
     * @see CommandReader
     */
    public void show(LinkedList<SpaceMarine> collection) {
        for (SpaceMarine i : collection) {
            out.println(InfoGetter.toString(i));
        }
    }

    /**
     * The add command. Adds new element to the collection using the given information about this element
     *
     * @param collection   the collection which we work with
     * @param name         the name of new element
     * @param health       the health of new element
     * @param achievements the achievements of new element
     * @see CommandReader
     */
    public void add(LinkedList<SpaceMarine> collection, String name, Double health, String achievements, Double x, Double y, String category, String weapon, Chapter chapter) {
        long maxId = -1;
        for (SpaceMarine i : collection) {
            if (i.getId() > maxId) {
                maxId = i.getId() + 1;
            }
        }
        SpaceMarine marine = new SpaceMarine(maxId, new Date());
        collection.add(marine);
        updateById(collection, marine.getId(), name, health, achievements, x, y, category, weapon, chapter);
        if (marine.getName() == null) {
            collection.remove(marine);
        }

    }

    /**
     * The update_by_id command. Updates the element which has the same id as given
     *
     * @param collection   the collection which we work with
     * @param id           the id of updating element
     * @param name         the name of updating element
     * @param health       the health of updating element
     * @param achievements the achievements of updating element
     * @see CommandReader
     */
    public void updateById(LinkedList<SpaceMarine> collection, Long id, String name, Double health, String achievements, Double x, Double y, String category, String weapon, Chapter chapter) {
        for (SpaceMarine marine : collection) {
            if (marine.getId() == id) {
                marine.setName(name);
                marine.setHealth(health);
                marine.setAchievements(achievements);
                marine.setCoordinates(x, y);
                marine.setCategory(category);
                marine.setMeleeWeapon(weapon);
                marine.setChapter(chapter);
                break;
            }
        }
    }

    /**
     * The remove_by_id command. Removes the element which has the same id as given
     *
     * @param collection the collection which we work with
     * @param id         the id of removing element
     * @see CommandReader
     */
    public void removeById(LinkedList<SpaceMarine> collection, Long id) {

        for (SpaceMarine i : collection) {
            if (i.getId() == id) {
                collection.remove(i);
            }
        }

    }

    /**
     * The clear command. Erases everything from the collection
     *
     * @param collection the collection which we work with
     * @see CommandReader
     */
    public void clearCollection(LinkedList<SpaceMarine> collection) {
        collection.clear();
    }

    /**
     * The save command. Saves all the info about the collection to the data-file
     *
     * @param collection the collection which we work with
     * @param filename       the file where the collection is stored
     * @see CommandReader
     */
    public void save(LinkedList<SpaceMarine> collection, String filename) {
        File file = new File(filename);
        if (!file.canWrite() && !filename.equals("BACKUP")) {
            out.println("There is no permission to write in data-file");
            System.exit(1);
        }
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            for (SpaceMarine i : collection) {
                writer.println(i.getId() + "," + i.getCreationDate().getTime() + "," + i.getName().replaceAll("\\\\", "\\\\\\\\").replaceAll(",", "\\\\,") + "," + i.getCoords().getX() + "," + i.getCoords().getY() + "," +
                        i.getHealth() + "," + i.getAchievements().replaceAll("\\\\", "\\\\\\\\").replaceAll(",", "\\\\,") + "," + i.getCategory() + "," + i.getMeleeWeapon()
                        + "," + i.getChapter().getName().replaceAll("\\\\", "\\\\\\\\").replaceAll(",", "\\\\,"));
            }
            if (!filename.equals("BACKUP")) {
                out.println("Successfully saved");
                Files.delete(Paths.get("BACKUP"));
            }
            writer.close();
        } catch (FileNotFoundException e) {
            out.println("Could not find your database, check your environment variable 'TEMP' to be equal\n" +
                    "to path to database or check its position");
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    /**
     * The remove_last command. Removes the last element of the collection
     *
     * @param collection the collection which we work with
     * @see CommandReader
     */
    public void removeLast(LinkedList<SpaceMarine> collection) {
        collection.removeLast();
    }

    /**
     * The shuffle command. Shuffles the collection in random order
     *
     * @param collection the collection which we work with
     * @see CommandReader
     */
    public void shuffle(LinkedList<SpaceMarine> collection) {
        Collections.shuffle(collection);
    }

    /**
     * The sort command. Sorts the collection in natural order
     *
     * @param collection the collection which we work with
     * @see CommandReader
     */
    public void sort(LinkedList<SpaceMarine> collection) {
        Collections.sort(collection);
    }

    /**
     * The count_by_melee_weapon command. Counts the amount of elements which use this kind of Melee Weapon
     *
     * @param collection the collection which we work with
     * @param weapon     the Melee Weapon enum constant which is used to count
     * @see CommandReader
     * @see MeleeWeapon
     */
    public void countWeapon(LinkedList<SpaceMarine> collection, MeleeWeapon weapon) {
        int i = 0;
        for (SpaceMarine marine : collection) {
            if (marine.getMeleeWeapon().equals(weapon)) {
                i++;
            }
        }
        out.println("Number of this weapon owners: " + i);
    }

    /**
     * The count_greater_than_category command. Counts the amount of elements with the category field value greater than given
     *
     * @param collection the collection which we work with
     * @param category   the Astartes Category enum constant which is used to count
     * @see CommandReader
     * @see AstartesCategory
     */
    public void countGCategory(LinkedList<SpaceMarine> collection, AstartesCategory category) {
        int i = 0;
        for (SpaceMarine marine : collection) {
            if (marine.getCategory() == null) {
                continue;
            }
            if (marine.getCategory().compareTo(category) > 0) {
                i++;
            }
        }
        out.println("Number of items with category greater than that: " + i);
    }

    /**
     * The print_descending command. Prints all the information about all the elements of the collection in descending order
     *
     * @param collection the collection which we work with
     * @see CommandReader
     */
    public void descending(LinkedList<SpaceMarine> collection) {
        LinkedList<SpaceMarine> reversed = new LinkedList<>(collection);
        Collections.sort(reversed);
        Collections.reverse(reversed);
        show(reversed);
    }
}
