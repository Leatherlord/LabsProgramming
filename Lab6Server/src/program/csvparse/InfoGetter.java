package program.csvparse;

import additionals.Coordinates;
import additionals.SpaceMarine;

public class InfoGetter {

    public static String toString(SpaceMarine marine) {
        return "additionals.SpaceMarine{" +
                "id=" + marine.getId() +
                ",\nname='" + marine.getName() + '\'' +
                ",\ncoordinates=" + toString(marine.getCoords()) +
                ",\nhealth=" + marine.getHealth() +
                ",\nachievements='" + marine.getAchievements() + '\'' +
                ",\ncategory=" + marine.getCategory() +
                ",\nmeleeWeapon=" + marine.getMeleeWeapon() +
                ",\nchapter=" + marine.getChapter().getName() +
                ",\ncreationDate=" + marine.getCreationDate() +
                "}\n";
    }

    public static String toString(Coordinates coords) {
        return "additionals.Coordinates{" +
                "x=" + coords.getX() +
                ", y=" + coords.getY() +
                '}';
    }

}
