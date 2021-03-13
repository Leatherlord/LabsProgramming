package additionals;

import java.io.Serializable;

/**
 * The additionals.Coordinates class. It stores the information about Marine's coordinates
 *
 * @see SpaceMarine
 */
public class Coordinates implements Serializable {
    /**
     * The Y-coordinate. It cannot take the null value
     */
    private final Double y;
    /**
     * The X-coordinate. It cannot be under -448 and cannot take the null value
     */
    private Double x;

    /**
     * Constructor for instantiating a new additionals.Coordinates.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Coordinates(Double x, Double y) {
        if (x <= -488) {
            System.out.println("Wrong 'x' Coords, check the input and try again");
            System.exit(1);
        } else {
            this.x = x;
        }
        this.y = y;
    }

    /**
     * Method for getting the X-coordinate value as String
     *
     * @return the x-coordinate
     */
    public String getX() {
        return this.x.toString();
    }

    /**
     * Method for getting the Y-coordinate value as String
     *
     * @return the y-coordinate
     */
    public String getY() {
        return this.y.toString();
    }

}
