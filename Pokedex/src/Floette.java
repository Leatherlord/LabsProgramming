public class Floette extends Flabebe {

    public Floette(String name, int level) {
        super(name, level);
        super.setStats(54, 45, 47, 75, 98, 52);
        RazorLeaf RF = new RazorLeaf();
        super.addMove(RF);
    }

}
