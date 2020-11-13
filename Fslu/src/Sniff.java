import static java.lang.Math.random;

public class Sniff implements LivingCreatures {
private final int size = 100;

    public boolean lookAt(Stranger str) throws ExceptionStoryWentWrong{
        isAlive();
        return str.getSize() < this.size;
    }

    public void isAlive() throws ExceptionStoryWentWrong{
        if (random() < 0.05) {
            throw new ExceptionStoryWentWrong("Снифф мертв! История пошла не по тому сценарию...");
        }
    }
}
