import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Flabebe extends Pokemon {
    public Flabebe(String name, int level) {
        super(name, level);
        super.setStats(44, 38, 39, 61, 79, 42);
        super.setType(Type.FAIRY);
        MagicalLeaf ML = new MagicalLeaf();
        CalmMind CM = new CalmMind();
        super.setMove(ML, CM);
    }

}
// EvoLinda, InvoLinda