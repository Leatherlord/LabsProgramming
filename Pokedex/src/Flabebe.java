import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Flabebe extends Pokemon {
	public Flabebe(String name, int level) {
		super(name, level);
		super.setStats(3, 3, 3, 4, 5, 3);
		super.setType(Type.FAIRY);
		MagicalLeaf ML = new MagicalLeaf();
		CalmMind CM = new CalmMind();
		super.setMove(ML, CM);
	}

}
// EvoLinda, InvoLinda