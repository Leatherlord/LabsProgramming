import static java.lang.Math.*;
import static java.lang.System.*;

public class Staircase {
    public void run(String name) throws ExceptionStoryWentWrong {
        if (toTripOver()){
            throw new ExceptionStoryWentWrong("Бегать по лестницам нельзя - можно же и спотк..." +
                    name + " споткнулись.");
        } else {
            out.println(name + " забегали по лестницам.");
        }
    }


    private boolean toTripOver(){
        if (random()>0.1){
            return false;
        } else{
            return true;
        }
    }
}
