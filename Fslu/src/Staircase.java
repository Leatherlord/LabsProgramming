import static java.lang.Math.random;
import static java.lang.System.out;

public class Staircase {
    public void run(String name) throws ExceptionStoryWentWrong {
        if (toTripOver()) {
            throw new ExceptionStoryWentWrong("Бегать по лестницам нельзя - можно же и спотк..." +
                    name + " споткнулись.");
        } else {
            out.println(name + " забегали по лестницам.");
        }
    }


    private boolean toTripOver() {
        return !(random() > 0.1);
    }
}
