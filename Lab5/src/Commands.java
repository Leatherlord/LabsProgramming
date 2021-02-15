import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

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
        out.println("""
                GUIDE
                                
                help: display help for available commands
                                
                info: print information about the collection to standard output
                                
                show: display all elements of the collection in String representation to standard output
                                
                add {element}: add a new element to the collection
                ** Instead of {element} put the name, health (numeric value) and achievements - than follow the instructions. If the name
                contains more than 1 word, write them using "_" as blank space. Multiple achievements are preferably written
                using "/" as separation symbol. Commas are not allowed. **
                                
                Example:
                                
                add Van_Darkholme 300 Dungeonmaster/Leatherman/Dominant
                                
                                
                update id {element}: update the value of the collection element which id is equal to the given
                ** Instead of {element} put the name, health (numeric value) and achievements - than follow the instructions. If the name
                contains more than 1 word, write them using "_" as blank space. Multiple achievements are preferably written
                using "/" as separation symbol. Commas are not allowed. ID is numeric value. **
                                
                Example:
                                
                update 300 Van_Darkholme 300 Dungeonmaster/Leatherman/Dominant
                                
                                
                remove_by_id id: remove an item from the collection by its id
                ** ID is numeric value. **
                                
                clear: clear the collection
                                
                save: save the collection to the data-file
                                
                execute_script file_name: read and execute a script from the specified file.
                ** The script contains commands in the same form in which user enters them interactively. **
                                
                exit: exit the program (without saving to file)
                ** Needs your permission when used from standard input. **
                                
                remove_last: remove the last item from the collection
                                
                shuffle: shuffle collection items in random order
                                
                sort: sort the collection in natural order
                ** Elements are sorted with their names as an argument. **
                                
                count_by_melee_weapon meleeWeapon: display the number of elements whose
                meleeWeapon field is equal to the given one
                                
                count_greater_than_category category: display the number of items whose
                category field value is greater than the specified
                ** Enum constants are compared in standard way. Null value is less than any other. **
                                
                print_descending: print collection items in descending order""");
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
     * @param chpts        the chapters collection where all the chapters are stored
     * @param scn          the Scanner tuned with constructor of the Command Reader to read from System.in or from script-file
     * @param name         the name of new element
     * @param health       the health of new element
     * @param achievements the achievements of new element
     * @see CommandReader
     */
    public void add(LinkedList<SpaceMarine> collection, LinkedList<Chapter> chpts, Scanner scn, String name, Double health, String achievements) {
        try {
            SpaceMarine marine = new SpaceMarine();
            collection.add(marine);
            updateById(collection, chpts, scn, marine.getId(), name, health, achievements);
            if (marine.getName() == null) {
                collection.remove(marine);
            }
        } catch (NumberFormatException e) {
            out.println("Health must be Double");
        }
    }

    /**
     * The update_by_id command. Updates the element which has the same id as given
     *
     * @param collection   the collection which we work with
     * @param chpts        the chapters collection where all the chapters are stored
     * @param scn          the Scanner tuned with constructor of the Command Reader to read from System.in or from script-file
     * @param id           the id of updating element
     * @param name         the name of updating element
     * @param health       the health of updating element
     * @param achievements the achievements of updating element
     * @see CommandReader
     */
    public void updateById(LinkedList<SpaceMarine> collection, LinkedList<Chapter> chpts, Scanner scn, Long id, String name, Double health, String achievements) {
        try {
            int iter = 0;
            for (SpaceMarine marine : collection) {
                if (marine.getId() == id) {
                    iter = 1;
                    if (marine.setName(name)) {
                        return;
                    }
                    marine.setHealth(health);
                    marine.setAchievements(achievements);
                    double x;
                    double y;
                    while (true) {
                        try {
                            out.println("Enter the x-coord:");
                            x = Double.parseDouble(scn.nextLine());
                            break;
                        } catch (NumberFormatException e){
                            out.println("This field must be Double");
                        }
                    }
                    while (true) {
                        try {
                            out.println("Enter the y-coord:");
                            y = Double.parseDouble(scn.nextLine());
                            break;
                        } catch (NumberFormatException e){
                            out.println("This field must be Double");
                        }
                    }
                    marine.setCoordinates(x, y);
                    out.println("Enter the Category (INCEPTOR, SUPPRESSOR, TACTICAL):");
                    while (marine.setCategory(scn.nextLine())) ;
                    out.println("Enter the Weapon (CHAIN_SWORD, MANREAPER, LIGHTING_CLAW, POWER_FIST):");
                    while (marine.setMeleeWeapon(scn.nextLine())) ;
                    while (true) {
                        out.println("Enter the Chapter:");
                        String schpt = scn.nextLine();
                        if (schpt.contains(",")) {
                            out.println("Commas are not allowed");
                            continue;
                        }
                        for (Chapter i : chpts) {
                            if (i.getName().equals(schpt)) {
                                i.addCount();
                                marine.setChapter(i);
                                break;
                            }
                        }
                        if (marine.isNotChapter()) {
                            Chapter chpt = new Chapter();
                            if (schpt.equals("")) {
                                out.println("Chapter mustn't be equal to null");
                                continue;
                            } else {
                                chpt.setName(schpt);
                                chpt.setMarinesCount(1);
                            }
                            marine.setChapter(chpt);
                            chpts.add(chpt);
                            break;
                        }
                    }
                    out.println("Success");
                    break;
                }
            }
            if (iter == 0) {
                out.println("Wrong ID, try again");
            }
        } catch (NumberFormatException e) {
            out.println("Health must be Double, ID must be Long");
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
        try {


            int iter = 0;
            for (SpaceMarine i : collection) {
                if (i.getId() == id) {
                    iter = 1;
                    collection.remove(i);
                    out.println("Successfully removed");
                }
            }
            if (iter == 0) {
                out.println("Wrong ID, try again");
            }
        } catch (NumberFormatException e) {
            out.println("ID must be Long");
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
        out.println("Successfully cleared");
    }

    /**
     * The save command. Saves all the info about the collection to the data-file
     *
     * @param collection the collection which we work with
     * @param file       the file where the collection is stored
     * @see CommandReader
     */
    public void save(LinkedList<SpaceMarine> collection, String file) {

        try {
            PrintWriter writer = new PrintWriter(new File(file));
            writer.print("");
            for (SpaceMarine i : collection) {
                writer.println(i.getId() + "," + i.getCreationDate().getTime() + "," + i.getName() + "," + i.getCoords().getX() + "," + i.getCoords().getY() + "," +
                        i.getHealth() + "," + i.getAchievements() + "," + i.getCategory() + "," + i.getMeleeWeapon()
                        + "," + i.getChapter().getName());
            }
            out.println("Successfully saved to Data.csv");
            writer.close();
        } catch (FileNotFoundException e) {
            out.println("Could not find 'Data.csv', check your environment variable 'TEMP' to be equal\n" +
                    "to path to 'Data.csv' or check its position");
        }
    }

    /**
     * The exit command. Exits the program with user's permission
     *
     * @param scn the Scanner tuned with constructor of the Command Reader to read from System.in or from script-file
     * @see CommandReader
     */
    public void exit(Scanner scn) {
        out.println("R u sure 'bout that? (Yeah/Nah)");
        String answer = scn.nextLine();
        if ((answer.equals("Nah")) || (answer.equalsIgnoreCase("N"))) {
            out.println("Exiting stopped, you can continue");
        } else if ((answer.equals("Yeah")) || (answer.equalsIgnoreCase("Y"))) {
            out.println("Thanks for using our airline! See ya soon. All processes are stopped...");
            System.exit(0);
        } else {
            out.println("For your safe we managed to stop exiting");
        }
    }

    /**
     * The remove_last command. Removes the last element of the collection
     *
     * @param collection the collection which we work with
     * @see CommandReader
     */
    public void removeLast(LinkedList<SpaceMarine> collection) {
        if (collection.size() > 0) {
            collection.removeLast();
            out.println("Successfully removed last item");
        } else {
            out.println("There is nothing to remove");
        }
    }

    /**
     * The shuffle command. Shuffles the collection in random order
     *
     * @param collection the collection which we work with
     * @see CommandReader
     */
    public void shuffle(LinkedList<SpaceMarine> collection) {
        Collections.shuffle(collection);
        out.println("Successfully shuffled");
    }

    /**
     * The sort command. Sorts the collection in natural order
     *
     * @param collection the collection which we work with
     * @see CommandReader
     */
    public void sort(LinkedList<SpaceMarine> collection) {
        Collections.sort(collection);
        out.println("Successfully sorted");
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
