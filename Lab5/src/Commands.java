import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.out;

public class Commands {

    public void help() {
        out.println("""
                GUIDE
                help: display help for available commands
                info: print information about the collection to standard output
                show: display all elements of the collection in String representation to standard output
                add {element}: add a new element to the collection
                update id {element}: update the value of the collection element which id is equal to the given
                remove_by_id id: remove an item from the collection by its id
                clear: clear the collection
                save: save the collection to the file
                execute_script file_name: read and execute a script from the specified file.
                The script contains commands in the same form in which user enters them interactively
                exit: exit the program (without saving to file)
                remove_last: remove the last item from the collection
                shuffle: shuffle collection items in random order
                sort: sort the collection in natural order
                count_by_melee_weapon meleeWeapon: display the number of elements whose
                meleeWeapon field is equal to the given one
                count_greater_than_category category: display the number of items whose
                category field value is greater than the specified
                print_descending: print collection items in descending order
                                
                **IF YOU SEE '{element}' IN THE DESCRIPTION OF THE COMMAND, YOU SHOULD WRITE THE 'NAME' OF
                AN ELEMENT, 'HEALTH' AND 'ACHIEVEMENTS' INSTEAD OF '{element}'""");
    }

    public void info(Date date, LinkedList<SpaceMarine> collection) {
        out.println("Collection type: LinkedList\n" +
                "Initialization date: " + date + "\n" +
                "Number of elements: " + collection.size() + "\n" +
                "Names of elements: ");
        for (SpaceMarine i : collection) {
            out.println(i.getName());
        }
    }

    public void show(LinkedList<SpaceMarine> collection) {
        for (SpaceMarine i : collection) {
            out.println(i.toString());
        }
    }

    public void add(LinkedList<SpaceMarine> collection, LinkedList<Chapter> chpts, Scanner scn, String name, Double health, String achievements) {
        SpaceMarine marine = new SpaceMarine();
        collection.add(marine);
        updateById(collection, chpts, scn, marine.getId(), name, health, achievements);
    }

    public void updateById(LinkedList<SpaceMarine> collection, LinkedList<Chapter> chpts, Scanner scn, Long id, String name, Double health, String achievements) {
        int iter = 0;
        for (SpaceMarine marine : collection) {
            if (marine.getId() == id) {
                iter = 1;
                marine.setName(name);
                marine.setHealth(health);
                marine.setAchievements(achievements);
                out.println("Enter the x-coord:");
                double x = Double.parseDouble(scn.nextLine());
                out.println("Enter the y-coord:");
                double y = Double.parseDouble(scn.nextLine());
                marine.setCoordinates(x, y);
                out.println("Enter the Category (INCEPTOR, SUPPRESSOR, TACTICAL):");
                while (marine.setCategory(scn.nextLine())) ;
                out.println("Enter the Weapon (CHAIN_SWORD, MANREAPER, LIGHTING_CLAW, POWER_FIST):");
                while (marine.setMeleeWeapon(scn.nextLine())) ;
                out.println("Enter the Chapter:");
                String schpt = scn.nextLine();
                for (Chapter i : chpts) {
                    if (i.getName().equals(schpt)) {
                        i.addCount();
                        marine.setChapter(i);
                    }
                }
                if (marine.isNotChapter()) {
                    Chapter chpt = new Chapter();
                    if (schpt.equals("")){
                        chpt = null;
                    } else {
                        chpt.setName(schpt);
                        chpt.setMarinesCount(1);
                    }
                    marine.setChapter(chpt);
                    chpts.add(chpt);
                }
                out.println("Success");
                break;
            }
        }
        if (iter == 0) {
            out.println("Wrong ID, try again");
        }
    }

    public void removeById(LinkedList<SpaceMarine> collection, Long id) {
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
    }

    public void clearCollection(LinkedList<SpaceMarine> collection) {
        collection.clear();
        out.println("Successfully cleared");
    }

    public void save(LinkedList<SpaceMarine> collection, String file) {

        try {
            PrintWriter writer = new PrintWriter(new File(file));
            writer.print("");
            for (SpaceMarine i : collection) {
                writer.println(i.getName() + "," + i.getCoords().getX() + "," + i.getCoords().getY() + "," +
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

    public void exit(Scanner scn) {
        out.println("R u sure 'bout that? (Yeah/Nah)");
        String answer = scn.nextLine();
        if ((answer.equals("Nah"))||(answer.equalsIgnoreCase("N"))) {
            out.println("Exiting stopped, you can continue");
        } else if ((answer.equals("Yeah"))||(answer.equalsIgnoreCase("Y"))) {
            out.println("Thanks for using our airline! See ya soon. All processes are stopped...");
            System.exit(0);
        } else {
            out.println("For your safe we managed to stop exiting");
        }
    }

    public void removeLast(LinkedList<SpaceMarine> collection) {
        collection.removeLast();
        out.println("Successfully removed last item");
    }

    public void shuffle(LinkedList<SpaceMarine> collection) {
        Collections.shuffle(collection);
        out.println("Successfully shuffled");
    }

    public void sort(LinkedList<SpaceMarine> collection) {
        Collections.sort(collection);
        out.println("Successfully sorted");
    }

    public void countWeapon(LinkedList<SpaceMarine> collection, MeleeWeapon weapon) {
        int i = 0;
        for (SpaceMarine marine : collection) {
            if (marine.getMeleeWeapon().equals(weapon)) {
                i++;
            }
        }
        out.println("Number of this weapon owners: " + i);
    }

    public void countGCategory(LinkedList<SpaceMarine> collection, AstartesCategory category) {
        int i = 0;
        for (SpaceMarine marine : collection) {
            if ((marine.getCategory().compareTo(category) > 0)||(marine.getCategory()==null)) {
                i++;
            }
        }
        out.println("Number of items with category greater than that: " + i);
    }

    public void descending(LinkedList<SpaceMarine> collection) {
        LinkedList<SpaceMarine> reversed = new LinkedList<>(collection);
        Collections.sort(reversed);
        Collections.reverse(reversed);
        show(reversed);
    }
}
