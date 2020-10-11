import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class RazorLeaf extends PhysicalMove {
	
	public RazorLeaf(Type type, double pow, double acc, int priority, int hits) {
		super(type, pow, acc, priority, hits);
	}	
	
	public RazorLeaf() {
		super(Type.GRASS, 55, 0.95, 0, 25);
	}	
	
    protected double calcCriticalHit(final Pokemon pokemon, final Pokemon pokemon2) {
        if (pokemon.getStat(Stat.SPEED) * 1.25 / 512.0 > Math.random()) {
        	System.out.println("ÊĞÈÒÈ×ÅÑÊÈÉ ÓĞÎÍ!");
            return 2.0;
        }
        return 1.0;
    }
    
	protected java.lang.String describe(){
		return ("àòàêóåò, èñïîëüçóÿ Razor Leaf!");
	}
}
