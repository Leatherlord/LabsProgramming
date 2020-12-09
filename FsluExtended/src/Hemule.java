import static java.lang.Math.random;

public class Hemule extends MummyHouse.MummyHouseResident implements ICook, IRun {
    private final String name = "Хемуль";

    public void runTo(Object obj) throws ExceptionStoryWentWrong{
        isAlive();
        System.out.println(this.name + " прибежал к " + obj.toString() + ".");
    }

    @Override
    public void run() {
        System.out.println("Хемуль бегает.");
    }

    public void runTo(Object obj, String str) throws ExceptionStoryWentWrong{
        isAlive();
        System.out.println(this.name + " прибежал к " + obj.toString() + str + ".");
    }
    @Override
    public void cook() throws ExceptionStoryWentWrong {
        isAlive();
        if (Math.random()>0.07){
            System.out.println(this.name + " толок кардамон для сладкого пирога со Сниффом.");
        } else {
            throw new ExceptionStoryWentWrong("Слишком много кардамона...");
        }
    }

    @Override
    public void isAlive() throws ExceptionStoryWentWrong {
        if (random() < 0.05) {
            throw new ExceptionStoryWentWrong("Хемуль мертв!" +
                    " История пошла не по тому сценарию...");
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
