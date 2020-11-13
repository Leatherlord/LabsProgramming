import static java.lang.Math.*;

public class Sniff implements LivingCreatures {
private int size = 100;

    public boolean lookAt(Stranger str) throws ExceptionStoryWentWrong{
        isAlive();
        if (str.getSize() < this.size) {
            return true;
        }
        else {
            return false;
        }
    }

    public void isAlive() throws ExceptionStoryWentWrong{
        if (random()<0.05){
            throw new ExceptionStoryWentWrong("Снифф мертв! История пошла не по тому сценарию...");
        }
    }
}
