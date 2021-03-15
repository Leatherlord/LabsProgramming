package program.csvparse;

import additionals.Chapter;
import additionals.SpaceMarine;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;


public class CSVInputReader {
    private final Logger logger = (Logger) LoggerFactory.getLogger(CSVInputReader.class);
    public LinkedList<SpaceMarine> csvCollect(LinkedList<SpaceMarine> marines, LinkedList<Chapter> chapters, String file) {
        String line;
        int rowN = 0;
        try {
            Scanner scanner = new Scanner(new File(file));

            while ((scanner.hasNextLine())) {
                rowN++;
                line = scanner.nextLine();
                ArrayList<String> row = lineReader(line);
                if (row.size() < 10) {
                    logger.warn("Wrong data: not enough data in row " + rowN + " - CSVInputReader.csvCollect()");
                    continue;
                }
                if (row.size() > 10) {
                    logger.warn("Wrong data: too much data in row " + rowN + " - CSVInputReader.csvCollect()");
                    continue;
                }
                Date date = new Date();
                try {
                    date = new Date(Long.parseLong(row.get(1)));
                } catch (Exception e) {
                    logger.warn("Something wrong with given date in row " + rowN + ", it was set as today's date - CSVInputReader.csvCollect()");
                }
                SpaceMarine marine;
                try {
                    for (SpaceMarine i : marines) {
                        if (i.getId() == Long.parseLong(row.get(0))) {
                            logger.warn("ID mustn't be identical - object from row " + rowN + " was not created - CSVInputReader.csvCollect()");
                            row.set(0, "error");
                        }
                    }
                    marine = new SpaceMarine(Long.parseLong(row.get(0)), date);
                } catch (NumberFormatException e) {
                    logger.warn("Something wrong with given id in row " + rowN + " - CSVInputReader.csvCollect()");
                    continue;
                }


                if (row.get(2).equals("")) {
                    logger.warn("Blank field instead of name in row " + rowN + " - CSVInputReader.csvCollect()");
                    continue;
                }

                marine.setName(row.get(2));

                try {
                    Double.parseDouble(row.get(3));
                    Double.parseDouble(row.get(4));
                } catch (Exception e) {
                    logger.warn("Wrong coords in row " + rowN + " - CSVInputReader.csvCollect()");
                    continue;
                }
                if (Double.parseDouble(row.get(3)) <= -488) {
                    logger.warn("Wrong 'x' Coords in row " + rowN + " - CSVInputReader.csvCollect()");
                    continue;
                }
                marine.setCoordinates(Double.parseDouble(row.get(3)), Double.parseDouble(row.get(4)));

                try {
                    Double.parseDouble(row.get(5));
                } catch (Exception e) {
                    logger.warn("Wrong Health in row " + rowN + " - CSVInputReader.csvCollect()");
                    continue;
                }
                if (Double.parseDouble(row.get(5)) <= 0) {
                    logger.warn("Health must be greater than 0! Row " + rowN + " - CSVInputReader.csvCollect()");
                    continue;
                }
                marine.setHealth(Double.parseDouble(row.get(5)));

                marine.setAchievements(row.get(6));

                if (marine.setCategory(row.get(7))) {
                    logger.warn("Wrong category in row " + rowN + " - CSVInputReader.csvCollect()");
                    continue;
                }

                if (marine.setMeleeWeapon(row.get(8))) {
                    logger.warn("Wrong weapon in row " + rowN + " - CSVInputReader.csvCollect()");
                    continue;
                }


                for (Chapter i : chapters) {
                    if (i.getName().equals(row.get(9))) {
                        i.addCount();
                        marine.setChapter(i);
                    }
                }
                if (marine.isNotChapter()) {
                    Chapter chapter = new Chapter();
                    if (row.get(9).equals("")) {
                        logger.warn("Wrong chapter in row " + rowN + ", it mustn't be null-ish - CSVInputReader.csvCollect()");
                        continue;
                    } else {
                        chapter.setName(row.get(9));
                        chapter.setMarinesCount(1);
                    }
                    marine.setChapter(chapter);
                    chapters.add(chapter);
                }
                logger.debug("New marine established - CSVInputReader.csvCollect(): " + marine.toString());
                marines.add(marine);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            logger.warn("Could not find the data-file - CSVInputReader.csvCollect()");
        }
        return marines;
    }


    private ArrayList<String> lineReader(String line) {
        boolean screen = false;
        String[] symbols = line.split("");
        ArrayList<String> row = new ArrayList<>();
        row.add("");
        for (String ch : symbols) {
            if (screen) {
                row.set(row.size() - 1, row.get(row.size() - 1) + ch);
                screen = false;
                continue;
            }
            if (ch.equals("\\")) {
                screen = true;
                continue;
            }
            if (ch.equals(",")) {
                row.add("");
                continue;
            }
            row.set(row.size() - 1, row.get(row.size() - 1) + ch);
        }
        logger.debug("New marine read - CSVInputReader.lineReader()" + row.toString());
        return row;
    }
}
