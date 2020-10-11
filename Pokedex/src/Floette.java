public class Floette extends Flabebe {

	public Floette(String name, int level) {
		super(name, level);
		super.setStats(4, 3, 3, 5, 6, 4);
		RazorLeaf RF = new RazorLeaf();
		super.addMove(RF);
	}

}
