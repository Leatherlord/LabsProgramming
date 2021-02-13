import java.util.Date;
import java.util.LinkedList;

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

        CSVInputReader csvInputReader = new CSVInputReader();
        marines = csvInputReader.csvCollector(marines, chapters, file);
        marines.sort(null);

        new CommandReader(date, marines, chapters, file);
    }
}
