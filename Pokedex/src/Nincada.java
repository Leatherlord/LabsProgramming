import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Nincada extends Pokemon {

    public Nincada(String name, int level) {
        super(name, level);
        super.setStats(31, 45, 90, 30, 30, 40);
        super.setType(Type.BUG, Type.GROUND);
        Absorb absorbSkill = new Absorb();
        super.addMove(absorbSkill);
        Rest restSkill = new Rest();
        super.addMove(restSkill);
        SandAttack sandAttackSkill = new SandAttack();
        super.addMove(sandAttackSkill);
    }

}
