import ru.ifmo.se.pokemon.*;

public class Roost extends StatusMove {

    public Roost(Type type, double pow, double acc, int priority, int hits) {
        super(type, pow, acc, priority, hits);
    }

    public Roost(Type type, double pow, double acc) {
        super(type, pow, acc, 0, 10);
    }

    public Roost() {
        super(Type.FLYING, 0, 0, 0, 10);
    }

    protected void applySelfEffects(Pokemon p) {
        Effect e = new Effect().turns(1).stat(Stat.HP, (int) (p.getHP() * 1.5));
        p.addEffect(e);
    }

    protected boolean checkAccuracy(Pokemon att, Pokemon def) {
        return true;
    }

    protected String describe() {
        return "защищается, используя Roost!";
    }

}
