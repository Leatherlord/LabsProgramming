package additionals;

import program.csvparse.CSVInputReader;

import java.util.Date;

/**
 * The Space Marine class - that exact class that is meant to fill the collection with its objects
 */
public class SpaceMarine implements Comparable<SpaceMarine> {
    /**
     * The field that contains the unique id of Space Marine as long number. Is is greater than 0 and it generates automatically
     */
    private final long id;
    /**
     * The java.util.Date object that represents the date of initialisation of Space Marine. It mustn't be equal to null and it generates automatically
     */
    private final java.util.Date creationDate;
    /**
     * The field that contains the name of Space Marine as String object. It mustn't be equal to null and it mustn't be empty
     */
    private String name = null;
    /**
     * The additionals.Coordinates object that represents the coordinates of Space Marine. It mustn't be equal to null
     */
    private Coordinates coordinates;
    /**
     * The field that contains the health of Space Marine as Double. It mustn't be equal to null and it must be greater than 0
     */
    private Double health = null;
    /**
     * The field that contains the achievements of Space Marine as String object
     */
    private String achievements = null;
    /**
     * The Astartes Category object that represents the category of Space Marine
     */
    private AstartesCategory category = null;
    /**
     * The Melee Weapon enum constant that represents the melee weapon used by Space Marine. It mustn't be equal to null
     */
    private MeleeWeapon meleeWeapon;
    /**
     * The additionals.Chapter object that represents the chapter of Space Marine. It mustn't be equal to null
     */
    private Chapter chapter = null;

    /**
     * Constructor for instantiating a Space Marine. It takes an id and date for marines which already existed in data-file
     *
     * @param id   the id of existing marine
     * @param date the creation date of existing marine
     * @see CSVInputReader
     */
    public SpaceMarine(long id, Date date) {
        this.id = id;
        this.creationDate = date;
    }

    /**
     * Method for getting the creation date of an object as an object of java.util.Date
     *
     * @return the creation date of an object
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Method for getting the id of Space Marine as long number
     *
     * @return the id of Space Marine
     */
    public long getId() {
        return id;
    }

    /**
     * Method for getting the astartes category of Space Marine as an object of Astartes Category
     *
     * @return the category of Space Marine
     * @see AstartesCategory
     */
    public AstartesCategory getCategory() {
        return this.category;
    }

    /**
     * Method for getting the melee weapon of Space Marine as an enum constant of Melee Weapon
     *
     * @return the melee weapon of Space Marine
     * @see MeleeWeapon
     */
    public MeleeWeapon getMeleeWeapon() {
        return this.meleeWeapon;
    }

    /**
     * Method for getting the chapter of Space Marine as a additionals.Chapter object
     *
     * @return the chapter of Space Marine
     * @see Chapter
     */
    public Chapter getChapter() {
        return this.chapter;
    }

    /**
     * Method for setting the chapter of Space Marine
     *
     * @param i the chapter of Space Marine as additionals.Chapter object
     * @see Chapter
     */
    public void setChapter(Chapter i) {
        this.chapter = i;
    }

    /**
     * Method for getting the health of Space Marine as a String object
     *
     * @return the health of Space Marine
     */
    public String getHealth() {
        return this.health.toString();
    }

    /**
     * Method for setting the amount of Space Marine's health
     *
     * @param health the Space Marine's health as Double
     */
    public void setHealth(Double health) {
        if (health <= 0) {
            System.out.println("Health must be greater than 0! Try to fix your inputs");
            System.exit(1);
        }
        this.health = health;
    }

    /**
     * Method for getting the name of Space Marine as a String object
     *
     * @return the name of Space Marine
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method for setting the name of Space Marine's health. Returns boolean value: true if the given name is unacceptable, false if the name is set
     *
     * @param name the name of Space Marine as String
     * @return boolean value
     */
    public boolean setName(String name) {
        if (name.equals("")) {
            System.out.println("Name must exist! Try to fix inputs");
            return true;
        }
        this.name = name;
        return false;
    }

    /**
     * Method for setting the coordinates of Space Marine. It takes 2 double values of x and y and creates a additionals.Coordinates object based on them
     *
     * @param x the x-coordinate of Space Marine
     * @param y the y-coordinate of Space Marine
     * @see Coordinates
     */
    public void setCoordinates(double x, double y) {
        this.coordinates = new Coordinates(x, y);
    }

    /**
     * Method for getting the coordinates of Space Marine as a additionals.Coordinates object
     *
     * @return the coordinates of Space Marine
     * @see Coordinates
     */
    public Coordinates getCoords() {
        return this.coordinates;
    }

    /**
     * Method for getting the achievements of Space Marine as a String object
     *
     * @return the achievements of Space Marine
     */
    public String getAchievements() {
        return this.achievements;
    }

    /**
     * Method for setting the achievements of Space Marine
     *
     * @param str the achievements of Space Marine as String
     */
    public void setAchievements(String str) {
        this.achievements = str;
    }

    /**
     * Method for setting the astartes category of Space Marine. Returns boolean value: true if category is unacceptable, false if category is set
     *
     * @param str the Astartes Category of Space Marine as String
     * @return boolean value
     * @see AstartesCategory
     */
    public boolean setCategory(String str) {
        if ("INCEPTOR".equals(str)) {
            this.category = AstartesCategory.INCEPTOR;
        } else if ("SUPPRESSOR".equals(str)) {
            this.category = AstartesCategory.SUPPRESSOR;
        } else if ("TACTICAL".equals(str)) {
            this.category = AstartesCategory.TACTICAL;
        } else if (!((str.equals("")) || (str.equals("null")))) {
            System.out.println("Wrong Category, try again");
            return true;
        }
        return false;
    }

    /**
     * Method for setting the melee weapon of Space Marine. Returns boolean value: true if weapon is unacceptable, false if weapon is set
     *
     * @param str the Melee Weapon of Space Marine as String
     * @return boolean value
     * @see MeleeWeapon
     */
    public boolean setMeleeWeapon(String str) {
        switch (str) {
            case "CHAIN_SWORD":
                this.meleeWeapon = MeleeWeapon.CHAIN_SWORD;
                break;
            case "MANREAPER":
                this.meleeWeapon = MeleeWeapon.MANREAPER;
                break;
            case "LIGHTING_CLAW":
                this.meleeWeapon = MeleeWeapon.LIGHTING_CLAW;
                break;
            case "POWER_FIST":
                this.meleeWeapon = MeleeWeapon.POWER_FIST;
                break;
            default:
                System.out.println("Wrong Weapon, try again");
                return true;
        }
        return false;
    }

    /**
     * Method for checking if Space Marine is in null-ish chapter
     *
     * @return boolean value
     * @see Chapter
     */
    public boolean isNotChapter() {
        return (this.chapter == null);
    }

    @Override
    public int compareTo(SpaceMarine o) {
        return this.name.compareTo(o.getName());
    }
}
