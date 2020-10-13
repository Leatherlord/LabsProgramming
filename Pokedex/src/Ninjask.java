import ru.ifmo.se.pokemon.Type;

public class Ninjask extends Nincada {

    public Ninjask(String name, int level) {
        super(name, level);
        super.setStats(61, 90, 45, 50, 50, 160);
        super.setType(Type.BUG, Type.FLYING);
        Roost roostSkill = new Roost();
        super.addMove(roostSkill);
    }

}
