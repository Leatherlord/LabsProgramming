import ru.ifmo.se.pokemon.Battle;

public class Attempt {

    public static void main(String[] args) {
        Battle b = new Battle();
        Florges involinda = new Florges("InvoLinda", 1);
        Flabebe linda = new Flabebe("Linda", 22);
        Floette evolinda = new Floette("EvoLinda", 25);

        Skarmory skar = new Skarmory("Skar", 53);
        Nincada cada = new Nincada("Cada", 21);
        Ninjask jask = new Ninjask("Jask", 23);

        b.addAlly(linda);
        b.addAlly(evolinda);
        b.addAlly(involinda);

        b.addFoe(skar);
        b.addFoe(cada);
        b.addFoe(jask);
        b.go();
    }

}
//Flabele - lvl 22
//Floete - lvl 25
//Florges - lvl 1
//Skarmory - lvl 53
//Nincada - lvl 21
//Ninjask - lvl 23