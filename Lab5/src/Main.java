import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.LinkedList;

/**
 * The Main class of the program. Contains method main() where the main logic of the program is
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        LinkedList<SpaceMarine> marines = new LinkedList<>();
        Date date = new Date();

        String filePath = System.getenv("TEMP");
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Could not find the data-file: it will be created when you save your collection");
        } else if (!file.canWrite()) {
            System.out.println("There is no permission to write in data-file");
            System.exit(1);
        } else if (!file.canRead()) {
            System.out.println("There is no permission to read data-file");
            System.exit(1);
        }


        Path path = Paths.get(filePath);
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
        marines = csvInputReader.csvCollect(marines, chapters, filePath);
        marines.sort(null);
        System.out.println("Enter the command: ");
        new CommandReader(date, marines, chapters, filePath);
    }
}

