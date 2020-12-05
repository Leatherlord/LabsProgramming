import static java.lang.Math.random;
import static java.lang.System.out;

public class Program {

    public static void main(String[] args) throws ExceptionStoryWentWrong {
        Daytime time = Daytime.DAY;
        Stranger tnv = new TofslaAndVifsla();
        Sniff sniff = new Sniff(tnv);
        TofslaAndVifsla good = (TofslaAndVifsla) tnv;
        good.setName("Тофсла и Вифсла");
        new MummyHouse(good);
        good.check();
        new Case(good);
        while (time != Daytime.EVENING) {
            time = setTime();
        }
        Staircase stc = new Staircase();
        Carpet cpt = new Carpet();
        good.worry(stc, cpt);
    }

    private static Daytime setTime() {
        if (random() > 0.3) {
            out.println("Наступили сумерки.");
            return Daytime.EVENING;
        } else {
            out.println("Еще день.");
            return Daytime.DAY;
        }
    }

}
