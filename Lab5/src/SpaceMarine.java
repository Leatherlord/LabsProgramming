import java.util.Date;

public class SpaceMarine implements Comparable<SpaceMarine>, Cloneable {

    private final long id = (long) (Math.random() * 10 * Math.random() * 10 * Math.random() * 10 * Math.random() * 10) ^ 2; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final java.util.Date creationDate = new java.util.Date(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Double health = null; //Поле может быть null, Значение поля должно быть больше 0
    private String achievements = null; //Поле может быть null
    private AstartesCategory category = null; //Поле может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter = null; //Поле не может быть null

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public long getId() {
        return id;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public AstartesCategory getCategory() {
        return this.category;
    }

    public MeleeWeapon getMeleeWeapon() {
        return this.meleeWeapon;
    }

    public Chapter getChapter() {
        return this.chapter;
    }

    public String getHealth() {
        return this.health.toString();
    }

    public void setHealth(Double health) {
        if (health <= 0) {
            System.out.println("Health must be below 0! Try to fix your inputs");
            System.exit(1);
        }
        this.health = health;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name.equals("")) {
            System.out.println("Name must exist! Try to fix inputs");
            System.exit(1);
        }
        this.name = name;
    }

    public void setCoordinates(double x, double y) {
        this.coordinates = new Coordinates(x, y);
    }

    public Coordinates getCoords() {
        return this.coordinates;
    }



    public String getAchievements() {
        return this.achievements;
    }

    public void setAchievements(String str) {
        this.achievements = str;
    }

    public boolean setCategory(String str) {
        if ("INCEPTOR".equals(str)) {
            this.category = AstartesCategory.INCEPTOR;
        } else if ("SUPPRESSOR".equals(str)) {
            this.category = AstartesCategory.SUPPRESSOR;
        } else if ("TACTICAL".equals(str)) {
            this.category = AstartesCategory.TACTICAL;
        } else if (!((str.equals(""))||(str.equals("null")))){
            System.out.println("Wrong Category, try again");
            return true;
        }
        return false;
    }

    public boolean setMeleeWeapon(String str) {
        switch (str) {
            case "CHAIN_SWORD" -> this.meleeWeapon = MeleeWeapon.CHAIN_SWORD;
            case "MANREAPER" -> this.meleeWeapon = MeleeWeapon.MANREAPER;
            case "LIGHTING_CLAW" -> this.meleeWeapon = MeleeWeapon.LIGHTING_CLAW;
            case "POWER_FIST" -> this.meleeWeapon = MeleeWeapon.POWER_FIST;
            default -> {
                System.out.println("Wrong Weapon, try again");
                return true;
            }
        }
        return false;
    }

    public boolean isNotChapter() {
        return (this.chapter == null);
    }

    public void setChapter(Chapter i) {
        this.chapter = i;
    }

    @Override
    public int compareTo(SpaceMarine o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "SpaceMarine{" +
                "id=" + id +
                ",\nname='" + name + '\'' +
                ",\ncoordinates=" + coordinates.toString() +
                ",\nhealth=" + health +
                ",\nachievements='" + achievements + '\'' +
                ",\ncategory=" + category +
                ",\nmeleeWeapon=" + meleeWeapon +
                ",\nchapter=" + chapter +
                "}\n";
    }
}
