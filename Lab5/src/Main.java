import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        LinkedList<SpaceMarine> marines = new LinkedList<>();
        Date date = new Date();

        String file = System.getenv("TEMP");

        LinkedList<Chapter> chapters = new LinkedList<>();

        Chapter test = new Chapter();

        test.setMarinesCount(0);
        test.setName("test");
        chapters.add(test);

        CSVInputReader reader = new CSVInputReader();
        marines = reader.csvCollector(marines, chapters, file);
        marines.sort(null);

        Commands creader = new Commands();
        System.out.println("Enter the command: ");
        Scanner scn = new Scanner(System.in);

        String[] command = scn.nextLine().split(" ");

        creader.add(marines, chapters, scn, command[1], Double.parseDouble(command[2]), command[3]);

        creader.show(marines);
    }

}
