import ru.ifmo.se.pokemon.Battle;

public class Attempt {

	public static void main(String[] args) {
		Battle b = new Battle();
		Florges linda = new Florges("Linda", 22);
		Flabebe londa = new Flabebe("Londa", 22);
		Flabebe landa = new Flabebe("Landa", 22);
		System.out.println (londa.getTypes());
		Floette evolinda = new Floette("EvoLinda", 25);
		Floette evolonda = new Floette("EvoLonda", 25);
		Floette evolanda = new Floette("EvoLanda", 25);

		b.addAlly(linda);
		b.addAlly(londa);
		b.addAlly(landa);

		b.addFoe(evolinda);
		b.addFoe(evolonda);
		b.addFoe(evolanda);
		b.go();
	}
	
}
//Flabele - lvl 22
//Floete - lvl 25