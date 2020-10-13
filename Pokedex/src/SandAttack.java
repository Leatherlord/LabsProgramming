import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class SandAttack extends StatusMove {

    public SandAttack(Type type, double pow, double acc, int priority, int hits) {
        super(type, pow, acc, priority, hits);
    }

    public SandAttack(Type type, double pow, double acc) {
        super(type, pow, acc, 0, 15);
    }

    public SandAttack() {
        super(Type.GROUND, 0, 1, 0, 15);
    }

    protected void applyOppEffects(Pokemon p) {
        p.setMod(Stat.ACCURACY, -1);
    }

    protected String describe() {
        return "атакует, используя Sand Attack!";
    }

}
