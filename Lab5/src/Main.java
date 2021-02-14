import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        LinkedList<SpaceMarine> marines = new LinkedList<>();
        Date date = new Date();

        String file = System.getenv("TEMP");

        Path path = Paths.get(file);
        BasicFileAttributes attr;
        try {
            attr = Files.readAttributes(path, BasicFileAttributes.class);
            date = new Date(attr.creationTime().toMillis());
        } catch (IOException e) {
            System.out.println("Something wrong happened with getting date, it is set for today's date");
        }
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

