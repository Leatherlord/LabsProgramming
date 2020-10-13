import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Skarmory extends Pokemon {
    public Skarmory(String name, int lvl) {
        super(name, lvl);
        super.setStats(65, 80, 140, 40, 70, 70);
        super.setType(Type.STEEL, Type.FLYING);
        BraveBird BB = new BraveBird();
        AirCutter AC = new AirCutter();
        RockTomb RT = new RockTomb();
        NightSlash NS = new NightSlash();
        super.setMove(BB, AC, RT, NS);
    }
}
