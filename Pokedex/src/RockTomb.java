import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;


public class RockTomb extends PhysicalMove {

    public RockTomb() {
        super(Type.ROCK, 60, 0.95, 0, 15);
    }

    public RockTomb(Type type, double pow, double acc, int priority, int hits) {
        super(type, pow, acc, priority, hits);
    }

    protected java.lang.String describe() {
        return ("атакует, используя Rock Tomb!");
    }

    protected void applyAllyEffects(Pokemon p) {
        p.setMod(Stat.SPEED, -1);
    }

}
