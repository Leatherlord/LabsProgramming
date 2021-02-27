import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

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
        chose(new Commands(), scn, collection, chapters, date, file, 0);
    }

    /**
     * Constructor for instantiating a new Command reader to use the script-file as input. Creates a new Scanner and uses a method chose with anonymous Commands inheritor with muted commands
     *
     * @param date       the date of initialization of the collection
     * @param collection the collection which we work with
     * @param chapters   the chapters collection where all the chapters are stored
     * @param file       the file where the collection is stored
     * @param script     the script-file where we want to read the commands from
     * @param marker     the marker that helps us to know if we are reading from script-file (marker>=1) or from System.in (marker=0).
     * @see Commands
     */
    public CommandReader(Date date, LinkedList<SpaceMarine> collection, LinkedList<Chapter> chapters, String file, String script, int marker) {
        try {
            Scanner scn = new Scanner(new File(script));
            chose(new Commands() {
                public void updateById(LinkedList<SpaceMarine> collection, LinkedList<Chapter> chpts, Scanner scn, Long id, String name, Double health, String achievements) {
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
                            try {
                                x = Double.parseDouble(scn.nextLine());
                                y = Double.parseDouble(scn.nextLine());
                            } catch (NumberFormatException e) {
                                out.println("Coords must be Double");
                                collection.remove(marine);
                                return;
                            }
                            marine.setCoordinates(x, y);
                            if (marine.setCategory(scn.nextLine())) {
                                System.exit(1);
                            }
                            if (marine.setMeleeWeapon(scn.nextLine())) {
                                System.exit(1);
                            }
                            String schpt = scn.nextLine();
                            for (Chapter i : chpts) {
                                if (i.getName().equals(schpt)) {
                                    i.addCount();
                                    marine.setChapter(i);
                                }
                            }
                            if (marine.isNotChapter()) {
                                Chapter chpt = new Chapter();
                                if (schpt.equals("")) {
                                    out.println("Chapter mustn't be equal to null");
                                    collection.remove(marine);
                                    return;
                                } else {
                                    chpt.setName(schpt);
                                    chpt.setMarinesCount(1);
                                }
                                marine.setChapter(chpt);
                                chpts.add(chpt);
                            }
                            break;
                        }
                    }
                    if (iter == 0) {
                        out.println("Update_By_Id: Wrong ID");
                    }
                }


                public void removeById(LinkedList<SpaceMarine> collection, Long id) {
                    int iter = 0;
                    for (SpaceMarine i : collection) {
                        if (i.getId() == id) {
                            iter = 1;
                            collection.remove(i);
                        }
                    }
                    if (iter == 0) {
                        out.println("Remove_By_Id: Wrong ID");
                    }
                }

                public void clearCollection(LinkedList<SpaceMarine> collection) {
                    collection.clear();
                }

                public void exit(Scanner scn) {
                    System.exit(0);
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
            }, scn, collection, chapters, date, file, marker);
        } catch (FileNotFoundException e) {
            out.println("No such a file found");
        } catch (StackOverflowError error) {
            out.println("Could not continue because of danger of StackOverflow. " + marker + " iterations happened");
        }
    }

    /**
     * Method for reading the input and parsing the Strings into commands
     *
     * @param commands   the class which contains methods for all the commands
     * @param scn        the Scanner tuned with constructor to read from System.in or from script-file
     * @param collection the collection which we work with
     * @param chapters   the chapters collection where all the chapters are stored
     * @param date       the date of initialization of the collection
     * @param file       the file where the collection is stored
     * @param marker     the marker that helps us to know if we are reading from script-file (marker>=1) or from System.in (marker=0).
     */
    public void chose(Commands commands, Scanner scn, LinkedList<SpaceMarine> collection, LinkedList<Chapter> chapters, Date date, String file, int marker) {
        String[] words;
        String line;
        while (scn.hasNextLine()) {
            line = scn.nextLine();
            words = line.split(" ");
            switch (words[0]) {
                case "help":
                    commands.help();
                    commands.save(collection, "BACKUP");
                    break;
                case "info":
                    commands.info(date, collection);
                    commands.save(collection, "BACKUP");
                    break;
                case "show":
                    commands.show(collection);
                    commands.save(collection, "BACKUP");
                    break;
                case "add":
                    try {
                        commands.add(collection, chapters, scn, words[1], Double.parseDouble(words[2]), words[3]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    } catch (NumberFormatException e) {
                        out.println("Health must be Double");
                    }
                    commands.save(collection, "BACKUP");
                    break;
                case "update":
                    try {
                        commands.updateById(collection, chapters, scn, Long.parseLong(words[1]), words[2], Double.parseDouble(words[3]), words[4]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    } catch (NumberFormatException e) {
                        out.println("Health must be Double, id must be Long");
                    }
                    commands.save(collection, "BACKUP");
                    break;
                case "remove_by_id":
                    try {
                        commands.removeById(collection, Long.parseLong(words[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    } catch (NumberFormatException e) {
                        out.println("Id must be Long");
                    }
                    commands.save(collection, "BACKUP");
                    break;
                case "clear":
                    commands.clearCollection(collection);
                    commands.save(collection, "BACKUP");
                    break;
                case "save":
                    commands.save(collection, file);
                    try {
                        Files.delete(Paths.get("BACKUP"));
                    } catch (IOException ignored) {
                    }
                    break;
                case "exit":
                    commands.exit(scn);
                    break;
                case "remove_last":
                    commands.removeLast(collection);
                    commands.save(collection, "BACKUP");
                    break;
                case "shuffle":
                    commands.shuffle(collection);
                    commands.save(collection, "BACKUP");
                    break;
                case "sort":
                    commands.sort(collection);
                    commands.save(collection, "BACKUP");
                    break;
                case "count_by_melee_weapon":
                    try {
                        commands.countWeapon(collection, MeleeWeapon.valueOf(words[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    } catch (IllegalArgumentException e) {
                        out.println("Weapon must be one of the given list (CHAIN_SWORD, MANREAPER, LIGHTING_CLAW, POWER_FIST)");
                    }
                    commands.save(collection, "BACKUP");
                    break;
                case "count_greater_than_category":
                    try {
                        commands.countGCategory(collection, AstartesCategory.valueOf(words[1]));
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
                    commands.save(collection, "BACKUP");
                    break;
                case "print_descending":
                    commands.descending(collection);
                    commands.save(collection, "BACKUP");
                    break;
                case "execute_script":
                    try {
                        new CommandReader(date, collection, chapters, file, words[1], marker + 1);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    }
                    commands.save(collection, "BACKUP");
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
}
