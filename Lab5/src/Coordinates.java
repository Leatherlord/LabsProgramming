public class Coordinates {

    private Double x; //Значение поля должно быть больше -448, Поле не может быть null
    private Double y; //Поле не может быть null

    public Coordinates(Double x, Double y) {
        if ((x <= -488) || (x == null)) {
            System.out.println("Wrong 'x' Coords, check the input and try again");
            System.exit(1);
        } else {
            this.x = x;
        }
        if (y == null) {
            System.out.println("Wrong 'y' Coords, check the input and try again");
            System.exit(1);
        } else {
            this.y = y;
        }
    }

    public String getX() {
        return this.x.toString();
    }

    public String getY() {
        return this.y.toString();
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
