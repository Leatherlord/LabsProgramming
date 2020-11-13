import static java.lang.Math.*;
import static java.lang.System.*;

public class Program{

    public static void main(String[] args) throws ExceptionStoryWentWrong {
        Daytime time = Daytime.DAY;
        Sniff sniff = new Sniff();
        Stranger tnv = new TofslaAndVifsla();
        if (sniff.lookAt(tnv)) {
            out.println("Снифф глянул на Странников, отметил с удовлетворением, что они меньше него," +
                    " подобрел и снисходительно принял их в Мумми-дом.");
        } else {
            throw new ExceptionStoryWentWrong("Тофсла и Вифсла оказались плохими ребятами," +
                    " и их не пустили в Мумми-дом.");
        }
        TofslaAndVifsla good = (TofslaAndVifsla) tnv;
        good.equals("Тофсла и Вифсла");
        MummyHouse house = new MummyHouse(good);
        if (good.cockTheNose() | !good.together()){
            throw new ExceptionStoryWentWrong();
        } else {
            out.println(good.toString() + " ни перед кем не задирали носа и почти все время " +
                    "бродили по долине рука об руку.");
        }
        Case tnvcase = new Case(good);
        while (time != Daytime.EVENING){
            time = setTime();
        }
        good.worry();
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
