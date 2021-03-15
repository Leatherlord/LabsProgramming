package program;

import additionals.Chapter;
import additionals.SpaceMarine;
import ch.qos.logback.classic.Logger;
import commands.Command;
import commands.SaveCommand;
import org.slf4j.LoggerFactory;
import program.csvparse.CSVInputReader;

import java.io.*;
import java.nio.file.Files;
import java.util.LinkedList;

public class CommandExecutor {
    private final Logger logger = (Logger) LoggerFactory.getLogger(CommandExecutor.class);
    public String executeCommand(Command command) {
        try {
            FileInputStream fis = new FileInputStream(new File(command.getFilename()));
            fis.close();
        } catch (FileNotFoundException e) {
            logger.warn("File not found - CommandExecutor.executeCommand()");
            return "File not found - check your env variable TEMP to be equal to the name of the database";
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinkedList<SpaceMarine> collection = establishCollection(command.getFilename());

        command.execute(collection);
        logger.info("Command executed - CommandExecutor.executeCommand() - " + command.getCommandName());
        new SaveCommand(command.getFilename()).execute(collection);
        logger.debug("Successfully saved");
        return command.getMessage();
    }

    private LinkedList<SpaceMarine> establishCollection(String filename) {

        File file = new File(filename);

        if (!file.exists()) {
            logger.warn("File not found - CommandExecutor.establishCollection()");
        } else if (!file.canRead()) {
            logger.warn("Unable to read file - CommandExecutor.establishCollection()");
        }

        LinkedList<Chapter> chapters = new LinkedList<>();

        Chapter test = new Chapter();

        test.setMarinesCount(0);
        test.setName("test");
        chapters.add(test);

        LinkedList<SpaceMarine> marines = new LinkedList<>();

        CSVInputReader csvInputReader = new CSVInputReader();
        marines = csvInputReader.csvCollect(marines, chapters, filename);
        marines.sort(null);
        logger.info("Collection established - CommandExecutor.establishCollection()");
        return marines;
    }
}
