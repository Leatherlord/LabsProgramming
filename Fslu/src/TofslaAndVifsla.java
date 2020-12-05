import static java.lang.Math.random;
import static java.lang.System.out;

public class TofslaAndVifsla extends Stranger{
    private final int size = 25;
    private String name = "Незнакомцы";

    @Override
    public int getSize() throws ExceptionStoryWentWrong {
        isAlive();
        return this.size;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    private boolean cockTheNose() throws ExceptionStoryWentWrong {
        isAlive();
        return false;
    }

    private boolean together() throws ExceptionStoryWentWrong {
        isAlive();
        return random() > 0.1;
    }

    public void check() throws ExceptionStoryWentWrong{
        if (cockTheNose() | !together()) {
            throw new ExceptionStoryWentWrong();
        } else {
            out.println(toString() + " ни перед кем не задирали носа и почти все время " +
                    "бродили по долине рука об руку.");
        }
    }
    public void worry(Staircase stc, Carpet cpt) throws ExceptionStoryWentWrong {
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
