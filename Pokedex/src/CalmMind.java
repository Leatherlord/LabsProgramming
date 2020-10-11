import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class CalmMind extends StatusMove {

	public CalmMind(Type type, double pow, double acc, int priority, int hits) {
		super(type, pow, acc, priority, hits);
	}
	
	public CalmMind() {
		super(Type.PSYCHIC, 0, 0, 0, 20);
	}
	
	protected java.lang.String describe(){
		return ("защищаетс€, использу€ Calm Mind!");
	}
	
	protected boolean checkAccuracy(Pokemon att, Pokemon def) {
		return true;
	}
	
	protected void applySelfEffects(Pokemon p) {
		p.setMod(Stat.SPECIAL_ATTACK, 1);
		p.setMod(Stat.SPECIAL_DEFENSE, 1);
	}
}
