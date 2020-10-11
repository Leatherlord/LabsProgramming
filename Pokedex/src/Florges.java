public class Florges extends Floette {

	public Florges(String name, int level) {
		super(name, level);
		super.setStats(5, 4, 4, 7, 10, 5);
		MagicalLeaf ML = new MagicalLeaf();
		super.addMove(ML);
	}

}
