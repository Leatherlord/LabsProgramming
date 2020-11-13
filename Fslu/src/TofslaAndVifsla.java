import static java.lang.Math.random;
import static java.lang.System.out;

public class TofslaAndVifsla extends Stranger implements LivingCreatures {
    private final int size = 25;
    private String name = "Незнакомцы";

    @Override
    public int getSize() throws ExceptionStoryWentWrong {
        isAlive();
        return this.size;
    }

    @Override
    public void equals(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public boolean cockTheNose() throws ExceptionStoryWentWrong {
        isAlive();
        return false;
    }

    public boolean together() throws ExceptionStoryWentWrong {
        isAlive();
        return random() > 0.1;
    }

    public void worry() throws ExceptionStoryWentWrong {
        Staircase stc = new Staircase();
        Carpet cpt = new Carpet();
        isAlive();
        out.println(this.name + " забеспокоились!");
        stc.run(this.name);
        cpt.hide(this.name);
    }

    public void isAlive() throws ExceptionStoryWentWrong {
        if (random() < 0.01) {
            throw new ExceptionStoryWentWrong(this.name + " мертвы! " +
                    "История пошла не по тому сценарию...");
        }
    }
}
