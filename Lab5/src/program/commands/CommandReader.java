package program.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import additionals.AstartesCategory;
import additionals.Chapter;
import additionals.MeleeWeapon;
import additionals.SpaceMarine;
import commands.*;

import static java.lang.System.out;

/**
 * The Command Reader class. It is meant to scan inputs and parse them into commands
 */
public class CommandReader {
    /**
     * Constructor for instantiating a new Command reader to use the System.in as input. Creates a new Scanner and uses a method chose
     *
     * @param date       the date of initialization of the collection
     * @param collection the collection which we work with
     * @param chapters   the chapters collection where all the chapters are stored
     * @param file       the file where the collection is stored
     * @see Commands
     */
    public CommandReader(Date date, LinkedList<SpaceMarine> collection, LinkedList<Chapter> chapters, String file) {
        Scanner scn = new Scanner(System.in);
        chose(scn, collection, chapters, date, file, 0, 10);
    }

    /**
     * Constructor for instantiating a new Command reader to use the script-file as input. Creates a new Scanner and uses a method chose with anonymous program.commands.Commands inheritor with muted commands
     *
     * @param date       the date of initialization of the collection
     * @param collection the collection which we work with
     * @param chapters   the chapters collection where all the chapters are stored
     * @param file       the file where the collection is stored
     * @param script     the script-file where we want to read the commands from
     * @param marker     the marker that helps us to know if we are reading from script-file (marker>=1) or from System.in (marker=0).
     * @see Commands     
     */
    public CommandReader(Date date, LinkedList<SpaceMarine> collection, LinkedList<Chapter> chapters, String file, String script, int marker, long maxit) {
        try {
            Scanner scn = new Scanner(new File(script));
            Scanner scanner = new Scanner(System.in);
            if (marker == 2) {
                out.println("Seems like you are going deeper - how deep do you agree to go? Enter the number of iterations");
                while (true) {
                    try {
                        maxit = Long.parseLong(scanner.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        out.println("Use the number as a parameter");
                    }
                }
            }
            scanner.close();
            chose(scn, collection, chapters, date, file, marker, maxit);
        } catch (FileNotFoundException e) {
            out.println("No such a file found");
        } catch (StackOverflowError error) {
            out.println("Could not continue because of danger of StackOverflow. " + marker + " iterations happened");
        }
    }

    /**
     * Method for reading the input and parsing the Strings into commands
     *
     * @param scn        the Scanner tuned with constructor to read from System.in or from script-file
     * @param collection the collection which we work with
     * @param chapters   the chapters collection where all the chapters are stored
     * @param date       the date of initialization of the collection
     * @param file       the file where the collection is stored
     * @param marker     the marker that helps us to know if we are reading from script-file (marker>=1) or from System.in (marker=0).
     */
    public void chose(Scanner scn, LinkedList<SpaceMarine> collection, LinkedList<Chapter> chapters, Date date, String file, int marker, long maxit) {
        String[] words;
        String line;
        while (scn.hasNextLine()) {
            line = scn.nextLine();
            words = line.split(" ");
            switch (words[0]) {
                case "help":
                    new HelpCommand().execute();
                    new SaveCommand(collection, "BACKUP").execute();
                    break;
                case "info":
                    new InfoCommand(date, collection).execute();
                    new SaveCommand(collection, "BACKUP").execute();
                    break;
                case "show":
                    new ShowCommand(collection).execute();
                    new SaveCommand(collection, "BACKUP").execute();
                    break;
                case "add":
                    try {
                        if (words[1].equals("")) {
                            out.println("Name must exist! Try to fix inputs");
                            break;
                        }
                        String[] array = collectEasy(scn);
                        Chapter chapter = collectChapter(scn, chapters);
                        new AddCommand(collection, words[1], Double.parseDouble(words[2]), words[3], Double.parseDouble(array[0]), Double.parseDouble(array[1]), array[2], array[3], chapter).execute();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    } catch (NumberFormatException e) {
                        out.println("Health must be Double");
                    }
                    new SaveCommand(collection, "BACKUP").execute();
                    break;
                case "update":
                    try {
                        int iter = 0;
                        for (SpaceMarine marine : collection) {
                            if (marine.getId() == Long.parseLong(words[1])) {
                                iter = 1;
                            }
                        }
                        if (iter == 0) {
                            out.println("Wrong ID, try again");
                            break;
                        }
                        if (words[2].equals("")) {
                            out.println("Name must exist! Try to fix inputs");
                            break;
                        }
                        String[] array = collectEasy(scn);
                        Chapter chapter = collectChapter(scn, chapters);
                        new UpdateByIdCommand(collection, words[2], Double.parseDouble(words[3]), words[4], Long.parseLong(words[1]), Double.parseDouble(array[0]), Double.parseDouble(array[1]), array[2], array[3], chapter).execute();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    } catch (NumberFormatException e) {
                        out.println("Health must be Double, id must be Long");
                    }
                    new SaveCommand(collection, "BACKUP").execute();
                    break;
                case "remove_by_id":
                    try {
                        int iter = 0;
                        for (SpaceMarine marine : collection) {
                            if (marine.getId() == Long.parseLong(words[1])) {
                                iter = 1;
                            }
                        }
                        if (iter == 0) {
                            out.println("Wrong ID, try again");
                            break;
                        }
                        new RemoveByIdCommand(collection, Long.parseLong(words[1])).execute();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    } catch (NumberFormatException e) {
                        out.println("Id must be Long");
                    }
                    new SaveCommand(collection, "BACKUP").execute();
                    break;
                case "clear":
                    new ClearCollectionCommand(collection).execute();
                    new SaveCommand(collection, "BACKUP").execute();
                    break;
                /*case "save":
                    new SaveCommand(collection, file).execute();
                    try {
                        Files.delete(Paths.get("BACKUP"));
                    } catch (IOException ignored) {
                    }
                    break;*/
                case "exit":
                    exit(scn);
                    break;
                case "remove_last":
                    if (collection.size() > 0) {
                        new RemoveLastCommand(collection).execute();
                    } else {
                        out.println("There is nothing to remove");
                    }
                    new SaveCommand(collection, "BACKUP").execute();
                    break;
                case "shuffle":
                    new ShuffleCommand(collection).execute();
                    new SaveCommand(collection, "BACKUP").execute();
                    break;
                case "sort":
                    new SortCommand(collection).execute();
                    new SaveCommand(collection, "BACKUP").execute();
                    break;
                case "count_by_melee_weapon":
                    try {
                        new CountByMeleeWeaponCommand(collection, MeleeWeapon.valueOf(words[1])).execute();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    } catch (IllegalArgumentException e) {
                        out.println("Weapon must be one of the given list (CHAIN_SWORD, MANREAPER, LIGHTING_CLAW, POWER_FIST)");
                    }
                    new SaveCommand(collection, "BACKUP").execute();
                    break;
                case "count_greater_than_category":
                    try {
                        new CountGreaterThanCategoryCommand(collection, AstartesCategory.valueOf(words[1])).execute();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        int j = 0;
                        for (SpaceMarine i : collection) {
                            if (i.getCategory() == null) {
                                j++;
                            }
                        }
                        out.println("Number of items with category greater than that: " + (collection.size() - j));
                    } catch (IllegalArgumentException e) {
                        out.println("Category must be one of the given list (INCEPTOR, SUPPRESSOR, TACTICAL) or should not exist");
                    }
                    new SaveCommand(collection, "BACKUP").execute();
                    break;
                case "print_descending":
                    new PrintDescendingCommand(collection).execute();
                    new SaveCommand(collection, "BACKUP").execute();
                    break;
                case "execute_script":
                    File script = new File(words[1]);
                    if (!script.canRead()) {
                        out.println("Script file is unable to read");
                        continue;
                    }
                    if (marker >= maxit) {
                        new SaveCommand(collection, "BACKUP").execute();
                        break;
                    }
                    try {
                        new CommandReader(date, collection, chapters, file, words[1], marker + 1, maxit);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    }
                    new SaveCommand(collection, "BACKUP").execute();
                    break;
                default:
                    if (marker == 0) {
                        out.println("Wrong command, try again");
                    }
                    break;
            }
            if (marker == 0) {
                out.println("Enter the command:");
            }
        }
    }

    private String[] collectEasy(Scanner scn) {
        Double x;
        Double y;
        String cat;
        String weap;
        while (true) {
            try {
                out.println("Enter the x-coord:");
                x = Double.parseDouble(scn.nextLine());
                break;
            } catch (NumberFormatException e) {
                out.println("This field must be Double");
            }
        }
        while (true) {
            try {
                out.println("Enter the y-coord:");
                y = Double.parseDouble(scn.nextLine());
                break;
            } catch (NumberFormatException e) {
                out.println("This field must be Double");
            }
        }
        while (true) {
            out.println("Enter the Category (INCEPTOR, SUPPRESSOR, TACTICAL):");
            cat = scn.nextLine();
            if (!(cat.equals("INCEPTOR")||cat.equals("SUPPRESSOR")||cat.equals("TACTICAL")||cat.equals("")||cat.equals("null"))) {
                System.out.println("Wrong Category, try again");
                continue;
            }
            break;
        }
        while (true) {
            out.println("Enter the Weapon (CHAIN_SWORD, MANREAPER, LIGHTING_CLAW, POWER_FIST):");
            weap = scn.nextLine();
            if (!(weap.equals("CHAIN_SWORD")||weap.equals("MANREAPER")||weap.equals("LIGHTING_CLAW")||weap.equals("POWER_FIST"))) {
                System.out.println("Wrong Weapon, try again");
                continue;
            }
            break;
        }
        String[] arr = {x.toString(), y.toString(), cat, weap};
        return arr;
    }

    private Chapter collectChapter(Scanner scn, LinkedList<Chapter> chapters) {
        while (true) {
            out.println("Enter the Chapter:");
            String schpt = scn.nextLine();
            for (Chapter i : chapters) {
                if (i.getName().equals(schpt)) {
                    i.addCount();
                    return i;
                } else if (schpt.equals("")) {
                    out.println("Chapter mustn't be equal to null");
                    continue;
                } else {
                    Chapter chpt = new Chapter();
                    chpt.setName(schpt);
                    chpt.setMarinesCount(1);
                    chapters.add(chpt);
                    return chpt;
                }
            }
        }
    }

    private void exit(Scanner scn) {
        if (new File("BACKUP").exists()) {
            out.println("R u sure 'bout that? (Yeah/Nah)");
            String answer = scn.nextLine();
            if ((answer.equals("Nah")) || (answer.equalsIgnoreCase("N"))) {
                out.println("Exiting stopped, you can continue");
            } else if ((answer.equals("Yeah")) || (answer.equalsIgnoreCase("Y"))) {
                out.println("Thanks for using our airline! See ya soon. All processes are stopped...");
                try {
                    Files.delete(Paths.get("BACKUP"));
                } catch (IOException e) {
                    out.println(e.getMessage());
                }
                System.exit(0);
            } else {
                out.println("For your safe we managed to stop exiting");
            }
        } else {
            out.println("Thanks for using our airline! See ya soon. All processes are stopped...");
            System.exit(0);
        }
    }
}
