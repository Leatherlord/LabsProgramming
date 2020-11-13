import static java.lang.System.out;

public class Carpet implements Movable {
    String position = "Right";

    @Override
    public void carry(String name) {
        out.println(name + " утащили ковер!");
    }

    public void move() {
        this.position = "Wrong";
    }

    public void put() {
        if (this.position == "Wrong") {
            this.position = "Right";
        }
    }

    public void hide(String name) {
        move();
        getUnder(name);
        put();
    }

    private void getUnder(String name) {
        out.println(name + " спрятались под ковер.");
    }
}
