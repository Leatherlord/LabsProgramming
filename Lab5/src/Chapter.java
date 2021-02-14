/**
 * The Chapter class. It represents the chapter of a Space Marine object
 *
 * @see SpaceMarine
 */
public class Chapter {
    /**
     * The name field that contains the name of the chapter. Cannot take the null value and cannot be blank
     */
    private String name;
    /**
     * The counting field that contains the amount of marines in this chapter. Must be greater than 0, but less than 1000
     *
     * @see SpaceMarine
     */
    private long marinesCount;

    /**
     * Method for getting the name of this chapter
     *
     * @return the name of this chapter as String object
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method for setting the name of this chapter
     *
     * @param name the name of this chapter
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for setting the amount of marines in this chapter
     *
     * @param i the integer amount which will be set
     * @see SpaceMarine
     */
    public void setMarinesCount(int i) {
        this.marinesCount = i;
    }

    /**
     * Method for increasing the amount of marines by 1
     */
    public void addCount() {
        this.marinesCount++;
    }

}
