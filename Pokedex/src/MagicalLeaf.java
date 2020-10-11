import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class MagicalLeaf extends SpecialMove {
	
	public MagicalLeaf(Type type, double pow, double acc, int priority, int hits) {
		super(type, pow, acc, priority, hits);
	}
	
	public MagicalLeaf() {
		super(Type.GRASS, 60, 0, 0, 20);
	}
	
	protected boolean checkAccuracy(Pokemon att, Pokemon def) {
		return true;
	}
	
	protected java.lang.String describe(){
		return ("атакует, использу€ Magic Leaf!");
	}
}
