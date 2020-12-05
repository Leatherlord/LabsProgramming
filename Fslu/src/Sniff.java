import static java.lang.Math.random;
import static java.lang.System.out;

public class Sniff implements ILive {
private final int size = 100;

public Sniff(Stranger str) throws ExceptionStoryWentWrong{
    if (lookAt(str)) {
        out.println("Снифф глянул на Странников, отметил с удовлетворением, что они меньше него," +
                " подобрел и снисходительно принял их в Мумми-дом.");
    } else {
        throw new ExceptionStoryWentWrong(str.toString() + " оказались плохими ребятами," +
                " и их не пустили в Мумми-дом.");
    }
}
    private boolean lookAt(Stranger str) throws ExceptionStoryWentWrong{
        isAlive();
        return str.getSize() < this.size;
    }

    public void isAlive() throws ExceptionStoryWentWrong{
        if (random() < 0.05) {
            throw new ExceptionStoryWentWrong("Снифф мертв! История пошла не по тому сценарию...");
        }
    }
}
