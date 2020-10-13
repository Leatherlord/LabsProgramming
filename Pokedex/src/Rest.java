import ru.ifmo.se.pokemon.*;

public class Rest extends StatusMove {

    public Rest(Type type, double pow, double acc, int priority, int hits) {
        super(type, pow, acc, priority, hits);
    }

    public Rest(Type type, double pow, double acc) {
        super(type, pow, acc, 0, 10);
    }

    public Rest() {
        super(Type.PSYCHIC, 0, 0, 0, 10);
    }

    protected void applySelfEffects(Pokemon p) {
        p.restore();
        Effect e = new Effect().turns(2).condition(Status.SLEEP);
        p.addEffect(e);
    }

    protected boolean checkAccuracy(Pokemon att, Pokemon def) {
        return true;
    }

    protected String describe() {
        return "защищается, используя Rest!";
    }

}
