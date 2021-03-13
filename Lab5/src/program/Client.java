package program;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * The program.Main class of the program. Contains method main() where the main logic of the program is
 */
public class Main {

    static private DatagramSocket s;
    static private SocketAddress a;
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            a = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 2222);
            s = new DatagramSocket();


            String filePath = "Data.csv";
            try {
                filePath = System.getenv("TEMP");
            } catch (Exception e) {
                System.out.println("Something wrong with your Environment Variable 'TEMP' - it must be equal to the name of your database");
                System.exit(1);
            }
            if (filePath == null) {
                System.out.println("Something wrong with your Environment Variable 'TEMP' - it must be equal to the name of your database");
                System.exit(1);
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(filePath);
            out.flush();
            byte[] byteArray = bos.toByteArray();
            DatagramPacket o = new DatagramPacket(byteArray, byteArray.length, a);
            s.send(o);



            File backup = new File("BACKUP");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String filePath = "Data.csv";
        try {
            filePath = System.getenv("TEMP");
        } catch (Exception e) {
            System.out.println("Something wrong with your Environment Variable 'TEMP' - it must be equal to the name of your database");
            System.exit(1);
        }
        if (filePath == null) {
            System.out.println("Something wrong with your Environment Variable 'TEMP' - it must be equal to the name of your database");
            System.exit(1);
        }


        /*if (!file.exists()) {
            System.out.println("Could not find the data-file: it will be created when you save your collection");
        } else if (!file.canRead()) {
            System.out.println("There is no permission to read data-file");
            System.exit(1);
        }
        if (backup.exists()) {
            System.out.println("Do you want to load from backup? (Y/N)");
            Scanner scn = new Scanner(System.in);
            if (scn.nextLine().equalsIgnoreCase("Y")) {
                if (!backup.canRead()) {
                    System.out.println("There is no permission to read backup-file");
                    System.exit(1);
                }
                if (!file.canWrite()) {
                    System.out.println("There is no permission to write in data-file");
                    System.exit(1);
                }
                try {
                    PrintWriter writer = new PrintWriter(file);
                    Scanner scanner = new Scanner(backup);
                    writer.write("");
                    while (scanner.hasNextLine()) {
                        writer.println(scanner.nextLine());
                    }
                    writer.close();
                    scanner.close();
                    Files.delete(Paths.get("BACKUP"));
                } catch (FileNotFoundException e) {
                    System.out.println("Something wrong happened with your files");
                } catch (IOException ignored) {
                }
            }
        }*/


    }
}

