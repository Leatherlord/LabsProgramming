public class Florges extends Floette {

    public Florges(String name, int level) {
        super(name, level);
        super.setStats(78, 65, 68, 112, 154, 75);
        MagicalLeaf ML = new MagicalLeaf();
        super.addMove(ML);
    }

}
