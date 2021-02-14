/**
 * The Information Getter class. It is meant to get an information from other classes
 * and represent it in a way that user will easily understand
 *
 * @see SpaceMarine
 * @see Coordinates
 */
public class InfoGetter {
    /**
     * Method for representation of information about Space Marine object
     *
     * @param marine the Space Marine object
     * @return the String object with all of that info
     */
    public static String toString(SpaceMarine marine){
        return "SpaceMarine{" +
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

    /**
     * Method for representation of information about Coordinates object
     *
     * @param coords the Coordinates object
     * @return the String object with all of that info
     */
    public static String toString(Coordinates coords){
        return "Coordinates{" +
                "x=" + coords.getX() +
                ", y=" + coords.getY() +
                '}';
    }

}
