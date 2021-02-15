import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * The CSV Input Reader class. It is meant to read the information from CSV data-file and parse it to fill the collection with pre-described objects
 */
public class CSVInputReader {
    /**
     * Method for collecting data and establishing collection with pre-given stats of its elements. Creates a new Scanner, looks through input, collecting the information, and creates objects
     *
     * @param marines  the collection which we work with
     * @param chapters the chapters collection where all the chapters are stored
     * @param file     the file where the collection is stored
     * @return the collection of elements filled with data from data-file
     */
    public LinkedList<SpaceMarine> csvCollector(LinkedList<SpaceMarine> marines, LinkedList<Chapter> chapters, String file) {
        String line;

        try {
            Scanner scanner = new Scanner(new File(file));

            while ((scanner.hasNextLine())) {
                line = scanner.nextLine();
                String[] row = line.split(",");
                if (row.length != 10) {
                    out.println("Wrong data, too much or not enough data");
                    System.exit(1);
                }
                SpaceMarine marine = new SpaceMarine();

                try {
                    marine = new SpaceMarine(Long.parseLong(row[0]));
                } catch (NumberFormatException e) {
                    out.println("Something wrong with given id, it was created automatically");
                }

                if (row[2].equals("")) {
                    System.out.println("Try to use a NAME, not a blank field");
                    System.exit(1);
                }

                marine.setName(row[2]);

                try {
                    Double.parseDouble(row[3]);
                    Double.parseDouble(row[4]);
                } catch (Exception e) {
                    System.out.println("Wrong coords, try again");
                    System.exit(1);
                }

                marine.setCoordinates(Double.parseDouble(row[3]), Double.parseDouble(row[4]));

                try {
                    Double.parseDouble(row[5]);
                } catch (Exception e) {
                    System.out.println("Wrong Health, try again");
                    System.exit(1);
                }

                marine.setHealth(Double.parseDouble(row[5]));

                marine.setAchievements(row[6]);

                if (marine.setCategory(row[7])) {
                    System.exit(1);
                }

                if (marine.setMeleeWeapon(row[8])) {
                    System.exit(1);
                }


                for (Chapter i : chapters) {
                    if (i.getName().equals(row[9])) {
                        i.addCount();
                        marine.setChapter(i);
                    }
                }
                if (marine.isNotChapter()) {
                    Chapter chpt = new Chapter();
                    if (row[9].equals("")) {
                        chpt = null;
                    } else {
                        chpt.setName(row[9]);
                        chpt.setMarinesCount(1);
                    }
                    marine.setChapter(chpt);
                    chapters.add(chpt);
                }
                marines.add(marine);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            out.println("Could not find the data-file, check your environment variable 'TEMP' to be equal\n" +
                    "to path to the data-file or check its position");
        }
        return marines;
    }
}
