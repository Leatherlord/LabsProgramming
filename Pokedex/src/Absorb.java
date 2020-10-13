import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class Absorb extends SpecialMove {

    public Absorb(Type type, double pow, double acc, int priority, int hits) {
        super(type, pow, acc, priority, hits);
    }

    public Absorb(Type type, double pow, double acc) {
        super(type, pow, acc, 0, 25);
    }

    public Absorb() {
        super(Type.GRASS, 20, 1, 0, 25);
    }

    protected void applySelfDamage(final Pokemon pokemon, final double n) {
        pokemon.setMod(Stat.HP, (int) -(Math.ceil(n * 0.5)));
    }

    protected String describe() {
        return "атакует, используя Absorb!";
    }
}
