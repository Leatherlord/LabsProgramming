import static java.lang.Math.random;
import static java.lang.System.out;

public class Sniff extends MummyHouse.MummyHouseResident implements ICook {
    private final int size = 100;
    private final String name = "Снифф";

    public Sniff() {
    }

    public void lookAt(Stranger str, MummyHouse house) throws ExceptionStoryWentWrong {
        isAlive();
        if (str.getSize() < this.size) {
            out.println("Снифф глянул на Странников, отметил с" +
                    " удовлетворением, что они меньше него," +
                    " подобрел и снисходительно принял их в Мумми-дом.");
            house.access(str);
        } else {
            throw new ExceptionStoryWentWrong(str.toString() +
                    " оказались плохими ребятами," +
                    " и их не пустили в Мумми-дом.");

        }
    }

    public void isAlive() throws ExceptionStoryWentWrong {
        if (random() < 0.05) {
            throw new ExceptionStoryWentWrong("Снифф мертв!" +
                    " История пошла не по тому сценарию...");
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void cook() throws ExceptionStoryWentWrong {
        isAlive();
        if (!throwUpHead() && !toFlushUp()) {
            throw new ExceptionStoryWentWrong("Снифф не воспылал к готовке...");
        } else {
            out.println("Снифф весь вспыхнул и вскинул голову.");
        }
    }

    private boolean throwUpHead() {
        return random() > 0.1;
    }

    private boolean toFlushUp() {
        return random() > 0.1;
    }
}
