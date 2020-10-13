import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class BraveBird extends PhysicalMove {

    public BraveBird(Type type, double pow, double acc, int priority, int hits) {
        super(type, pow, acc, priority, hits);
    }

    public BraveBird(Type type, double pow, double acc) {
        super(type, pow, acc, 0, 15);
    }

    public BraveBird() {
        super(Type.FLYING, 120, 1, 0, 15);
    }

    protected void applySelfDamage(final Pokemon pokemon, final double n) {
        pokemon.setMod(Stat.HP, (int) Math.round(n / 3));
    }

    protected String describe() {
        return "атакует, используя Brave Bird!";
    }

}
