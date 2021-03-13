package program.commands;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import additionals.AstartesCategory;
import additionals.Chapter;
import additionals.MeleeWeapon;
import commands.*;
import program.Container;


import static java.lang.System.out;

/**
 * The Command Reader class. It is meant to scan inputs and parse them into commands
 */
public class CommandReader {
    /**
     * Constructor for instantiating a new Command reader to use the System.in as input. Creates a new Scanner and uses a method chose
     *
     * @see Commands
     */
    public CommandReader(DatagramSocket socket, SocketAddress address, String filename) {
        Scanner scn = new Scanner(System.in);
        chose(scn,0, 10, socket, address, filename);
    }

    /**
     * Constructor for instantiating a new Command reader to use the script-file as input. Creates a new Scanner and uses a method chose with anonymous program.commands.Commands inheritor with muted commands
     *
     * @param script     the script-file where we want to read the commands from
     * @param marker     the marker that helps us to know if we are reading from script-file (marker>=1) or from System.in (marker=0).
     * @see Commands     
     */
    public CommandReader(String script, int marker, long maxit, DatagramSocket socket, SocketAddress address, String filename) {
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
            chose(scn, marker, maxit, socket, address, filename);
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
     * @param marker     the marker that helps us to know if we are reading from script-file (marker>=1) or from System.in (marker=0).
     */
    public void chose(Scanner scn, int marker, long maxit, DatagramSocket socket, SocketAddress address, String filename) {
        String[] words;
        String line;
        while (scn.hasNextLine()) {
            line = scn.nextLine();
            words = line.split(" ");
            switch (words[0]) {
                case "help":
                    send(new HelpCommand(filename), socket, address);
                    receive(socket);
                    //new SaveCommand("BACKUP");
                    break;
                case "info":
                    send(new InfoCommand(filename), socket, address);
                    receive(socket);
                    //new SaveCommand("BACKUP");
                    break;
                case "show":
                    send(new ShowCommand(filename), socket, address);
                    //new SaveCommand("BACKUP");
                    receive(socket);
                    break;
                case "add":
                    try {
                        if (words[1].equals("")) {
                            out.println("Name must exist! Try to fix inputs");
                            break;
                        }
                        String[] array = collectEasy(scn);
                        Chapter chapter = collectChapter(scn);
                        send(new AddCommand(words[1], Double.parseDouble(words[2]), words[3], Double.parseDouble(array[0]), Double.parseDouble(array[1]), array[2], array[3], chapter, filename), socket, address);
                        receive(socket);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    } catch (NumberFormatException e) {
                        out.println("Health must be Double");
                    }
                    //new SaveCommand("BACKUP");
                    break;
                case "update":
                    try {
                        if (words[2].equals("")) {
                            out.println("Name must exist! Try to fix inputs");
                            break;
                        }
                        String[] array = collectEasy(scn);
                        Chapter chapter = collectChapter(scn);
                        send(new UpdateByIdCommand(words[2], Double.parseDouble(words[3]), words[4], Long.parseLong(words[1]), Double.parseDouble(array[0]), Double.parseDouble(array[1]), array[2], array[3], chapter, filename), socket, address);
                        receive(socket);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    } catch (NumberFormatException e) {
                        out.println("Health must be Double, id must be Long");
                    }
                    //new SaveCommand("BACKUP");
                    break;
                case "remove_by_id":
                    try {
                        send(new RemoveByIdCommand(Long.parseLong(words[1]), filename), socket, address);
                        receive(socket);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    } catch (NumberFormatException e) {
                        out.println("Id must be Long");
                    }
                    //new SaveCommand("BACKUP");
                    break;
                case "clear":
                    send(new ClearCollectionCommand(filename), socket, address);
                    receive(socket);
                    //new SaveCommand("BACKUP");
                    break;
                case "exit":
                    exit(scn);
                    break;
                case "remove_last":
                    send(new RemoveLastCommand(filename ), socket, address);
                    receive(socket);
                    //new SaveCommand("BACKUP");
                    break;
                case "shuffle":
                    send(new ShuffleCommand(filename), socket, address);
                    receive(socket);
                    //new SaveCommand("BACKUP");
                    break;
                case "sort":
                    send(new SortCommand(filename), socket, address);
                    receive(socket);
                    //new SaveCommand("BACKUP");
                    break;
                case "count_by_melee_weapon":
                    try {
                        send(new CountByMeleeWeaponCommand(MeleeWeapon.valueOf(words[1]), filename), socket, address);
                        receive(socket);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    } catch (IllegalArgumentException e) {
                        out.println("Weapon must be one of the given list (CHAIN_SWORD, MANREAPER, LIGHTING_CLAW, POWER_FIST)");
                    }
                    //new SaveCommand("BACKUP");
                    break;
                case "count_greater_than_category":
                    try {
                        send(new CountGreaterThanCategoryCommand(AstartesCategory.valueOf(words[1]), filename), socket, address);
                        receive(socket);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Can't find the parameter");
                    } catch (IllegalArgumentException e) {
                        out.println("Category must be one of the given list (INCEPTOR, SUPPRESSOR, TACTICAL) or should not exist");
                    }
                    //new SaveCommand("BACKUP");
                    break;
                case "print_descending":
                    send(new PrintDescendingCommand(filename), socket, address);
                    receive(socket);
                    //new SaveCommand("BACKUP");
                    break;
                case "execute_script":
                    File script = new File(words[1]);
                    if (!script.canRead()) {
                        out.println("Script file is unable to read");
                        continue;
                    }
                    if (marker >= maxit) {
                        //new SaveCommand("BACKUP");
                        break;
                    }
                    try {
                        new CommandReader(words[1], marker + 1, maxit, socket, address, filename);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        out.println("Not enough arguments");
                    }
                    //new SaveCommand("BACKUP");
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
        double x;
        double y;
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
        return new String[]{Double.toString(x), Double.toString(y), cat, weap};
    }

    private Chapter collectChapter(Scanner scn) {
        while (true) {
            out.println("Enter the Chapter:");
            String schpt = scn.nextLine();
            if (schpt.equals("")) {
                out.println("Chapter mustn't be equal to null");
            } else {
                Chapter chpt = new Chapter();
                chpt.setName(schpt);
                chpt.setMarinesCount(1);
                return chpt;
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

    private void send(Command command, DatagramSocket socket, SocketAddress address) {
        byte[] sending;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(command);
            out.flush();
            sending = bos.toByteArray();
            DatagramPacket packet = new DatagramPacket(sending, sending.length, address);
            socket.send(packet);
        } catch (PortUnreachableException e) {
            out.println("Port is unreachable - try again later");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void receive(DatagramSocket socket) {
        byte[] message = new byte[1024];
        try {
            DatagramPacket packet = new DatagramPacket(message, message.length);
            socket.setSoTimeout(10000);
            socket.receive(packet);
            ByteArrayInputStream bis = new ByteArrayInputStream(message);
            ObjectInput in = new ObjectInputStream(bis);
            String received_message = (String) in.readObject();
            System.out.println(received_message);
        } catch (SocketTimeoutException e) {
            out.println("Timeout exceeded");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
