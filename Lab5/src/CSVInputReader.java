import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.out;

public class CSVInputReader {
    public LinkedList<SpaceMarine> csvCollector(LinkedList<SpaceMarine> marines, LinkedList<Chapter> chapters, String file) {
        String line;

        try {
            Scanner scanner = new Scanner(new File(file));

            while ((scanner.hasNextLine())) {
                line = scanner.nextLine();
                String[] row = line.split(",");
                if (row.length != 8) {
                    out.println("Wrong data, too much or not enough data");
                    System.exit(1);
                }
                SpaceMarine marine = new SpaceMarine();

                if (row[0].equals("")) {
                    System.out.println("Try to use a NAME, not a blank field");
                    System.exit(1);
                }

                marine.setName(row[0]);

                try {
                    Double.parseDouble(row[1]);
                    Double.parseDouble(row[2]);
                } catch (Exception e) {
                    System.out.println("Wrong coords, try again");
                    System.exit(1);
                }

                marine.setCoordinates(Double.parseDouble(row[1]), Double.parseDouble(row[2]));

                try {
                    Double.parseDouble(row[3]);
                } catch (Exception e) {
                    System.out.println("Wrong Health, try again");
                    System.exit(1);
                }

                marine.setHealth(Double.parseDouble(row[3]));

                marine.setAchievements(row[4]);

                if (marine.setCategory(row[5])) {
                    System.exit(1);
                }

                if (marine.setMeleeWeapon(row[6])) {
                    System.exit(1);
                }


                for (Chapter i : chapters) {
                    if (i.getName().equals(row[7])) {
                        i.addCount();
                        marine.setChapter(i);
                    }
                }
                if (marine.isNotChapter()) {
                    Chapter chpt = new Chapter();
                    chpt.setName(row[7]);
                    chpt.setMarinesCount(1);
                    marine.setChapter(chpt);
                    chapters.add(chpt);
                }
                marines.add(marine);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            out.println("Could not find 'Data.csv', check your environment variable 'TEMP' to be equal\n" +
                    "to path to 'Data.csv' or check its position");
        }
        return marines;
    }
}
