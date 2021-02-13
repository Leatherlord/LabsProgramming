import java.util.Date;
import java.util.List;
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
                print_descending: print collection items in descending order""");
    }

    public void info(Date date, int num, List<SpaceMarine> collection) {
        out.println("Collection type: LinkedList\n" +
                "Initialization date: " + date + "\n" +
                "Number of elements: " + num + "\n" +
                "Names of elements: ");
        for (SpaceMarine i : collection) {
            out.println(i.getName());
        }
    }

    public void show(List<SpaceMarine> collection) {
        out.println(collection);
        for(SpaceMarine i: collection){
            out.println(i.getName() + ": \n" +
                    i + "\n" +
                    "Id: " + i.getId() + "\n" +
                    i.getCoords() + "\n" +
                    "Achievements: " + i.getAchievements() + "\n" +
                    "Creation date: " + i.getCreationDate() + "\n" +
                    "Health: " + i.getHealth() + "\n" +
                    "Astartes Category: " + i.getCategory() + "\n" +
                    "Melee Weapon: " + i.getMeleeWeapon() + "\n" +
                    "Chapter: " + i.getChapter().getName() + "\n");
        }
    }

    public void add(List<SpaceMarine> collection, List<Chapter> chpts, Scanner scn, String name, Double health, String achievements) {
        SpaceMarine marine = new SpaceMarine();
        collection.add(marine);
        updateById(collection, chpts, scn, marine.getId(), name, health, achievements);
    }

    public void updateById(List<SpaceMarine> collection, List<Chapter> chpts, Scanner scn, Long id, String name, Double health, String achievements){
        int iter = 0;
        for (SpaceMarine marine: collection){
            if (marine.getId() == id){
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
                while (marine.setCategory(scn.nextLine()));
                out.println("Enter the Weapon (CHAIN_SWORD, MANREAPER, LIGHTING_CLAW, POWER_FIST):");
                while (marine.setMeleeWeapon(scn.nextLine()));
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
                    chpt.setName(schpt);
                    chpt.setMarinesCount(1);
                    marine.setChapter(chpt);
                    chpts.add(chpt);
                }
                break;
            }
        }
        if (iter == 0){
            out.println("Wrong ID, try again");
        }
    }

}
