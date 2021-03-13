package program.csvparse;

import additionals.Chapter;
import additionals.SpaceMarine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.out;


public class CSVInputReader {

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
                    out.println("Wrong data: not enough data in row " + rowN);
                    continue;
                }
                if (row.size() > 10) {
                    out.println("Wrong data: too much data in row " + rowN);
                    continue;
                }
                Date date = new Date();
                try {
                    date = new Date(Long.parseLong(row.get(1)));
                } catch (Exception e) {
                    out.println("Something wrong with given date in row " + rowN + ", it was set as today's date");
                }
                SpaceMarine marine;
                try {
                    for (SpaceMarine i : marines) {
                        if (i.getId() == Long.parseLong(row.get(0))) {
                            out.println("ID mustn't be identical - object from row " + rowN + " was not created");
                            row.set(0, "error");
                        }
                    }
                    marine = new SpaceMarine(Long.parseLong(row.get(0)), date);
                } catch (NumberFormatException e) {
                    out.println("Something wrong with given id in row " + rowN);
                    continue;
                }


                if (row.get(2).equals("")) {
                    out.println("Try to use a NAME, not a blank field in row " + rowN);
                    continue;
                }

                marine.setName(row.get(2));

                try {
                    Double.parseDouble(row.get(3));
                    Double.parseDouble(row.get(4));
                } catch (Exception e) {
                    out.println("Wrong coords in row " + rowN + ", try again");
                    continue;
                }

                marine.setCoordinates(Double.parseDouble(row.get(3)), Double.parseDouble(row.get(4)));

                try {
                    Double.parseDouble(row.get(5));
                } catch (Exception e) {
                    out.println("Wrong Health in row " + rowN + ", try again");
                    continue;
                }

                marine.setHealth(Double.parseDouble(row.get(5)));

                marine.setAchievements(row.get(6));

                if (marine.setCategory(row.get(7))) {
                    out.println("Exception in row " + rowN);
                    continue;
                }

                if (marine.setMeleeWeapon(row.get(8))) {
                    out.println("Exception in row " + rowN);
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
                        out.println("Wrong chapter in row " + rowN + ", it mustn't be null-ish");
                        continue;
                    } else {
                        chapter.setName(row.get(9));
                        chapter.setMarinesCount(1);
                    }
                    marine.setChapter(chapter);
                    chapters.add(chapter);
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
        return row;
    }
}
