import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.out;

public class CommandReader {
    public CommandReader(Date date, LinkedList<SpaceMarine> collection, LinkedList<Chapter> chapters, String file) {
        Scanner scn = new Scanner(System.in);
        read(new Commands(), scn, collection, chapters, date, file, 0);
    }

    public CommandReader(Date date, LinkedList<SpaceMarine> collection, LinkedList<Chapter> chapters, String file, String script) {
        try {
            Scanner scn = new Scanner(new File(script));
            read(new Commands() {
                public void updateById(LinkedList<SpaceMarine> collection, LinkedList<Chapter> chpts, Scanner scn, Long id, String name, Double health, String achievements) {
                    int iter = 0;
                    for (SpaceMarine marine : collection) {
                        if (marine.getId() == id) {
                            iter = 1;
                            marine.setName(name);
                            marine.setHealth(health);
                            marine.setAchievements(achievements);
                            double x = Double.parseDouble(scn.nextLine());
                            double y = Double.parseDouble(scn.nextLine());
                            marine.setCoordinates(x, y);
                            while (marine.setCategory(scn.nextLine())) ;
                            while (marine.setMeleeWeapon(scn.nextLine())) ;
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
            }, scn, collection, chapters, date, file, 1);
        } catch (FileNotFoundException ignored) {
        }
    }

    public void read(Commands commands, Scanner scn, LinkedList<SpaceMarine> collection, LinkedList<Chapter> chapters, Date date, String file, int marker) {
        String[] words;
        while (true) {
            if (marker == 0) {
                out.println("Enter the command:");
            }
            words = scn.nextLine().split(" ");
            switch (words[0]) {
                case "help" -> commands.help();
                case "info" -> commands.info(date, collection);
                case "show" -> commands.show(collection);
                case "add" -> {
                    try {
                        commands.add(collection, chapters, scn, words[1], Double.parseDouble(words[2]), words[3]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    }
                }
                case "update" -> {
                    try {
                        commands.updateById(collection, chapters, scn, Long.parseLong(words[1]), words[2], Double.parseDouble(words[3]), words[4]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    }
                }
                case "remove_by_id" -> {
                    try {
                        commands.removeById(collection, Long.parseLong(words[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    }
                }
                case "clear" -> commands.clearCollection(collection);
                case "save" -> commands.save(collection, file);
                case "exit" -> commands.exit(scn);
                case "remove_last" -> commands.removeLast(collection);
                case "shuffle" -> commands.shuffle(collection);
                case "sort" -> commands.sort(collection);
                case "count_by_melee_weapon" -> {
                    try {
                        commands.countWeapon(collection, MeleeWeapon.valueOf(words[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    }
                }
                case "count_greater_than_category" -> {
                    try {
                        commands.countGCategory(collection, AstartesCategory.valueOf(words[1]));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    }
                }
                case "print_descending" -> commands.descending(collection);
                case "execute_script" -> {
                    try {
                        new CommandReader(date, collection, chapters, file, words[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    }
                }
                default -> out.println("Wrong command, try again");
            }
        }
    }
}