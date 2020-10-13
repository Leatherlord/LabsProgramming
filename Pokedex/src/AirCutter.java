import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class AirCutter extends SpecialMove {

    public AirCutter() {
        super(Type.FLYING, 60, 0.95, 0, 25);
    }

    public AirCutter(Type type, double pow, double acc, int priority, int hits) {
        super(type, pow, acc, priority, hits);
    }

    protected java.lang.String describe() {
        return ("атакует, используя Air Cutter!");
    }

    protected double calcCriticalHit(final Pokemon pokemon, final Pokemon pokemon2) {
        if (pokemon.getStat(Stat.SPEED) / 512.0 > Math.random()) {
            System.out.println("НЫЫЫЫАААААААААААА!");
            return 5.0;
        }
        return 1.0;
    }

}
