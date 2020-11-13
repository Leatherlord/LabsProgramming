import static java.lang.System.*;
import static java.lang.Math.*;

public class MummyHouse {
    private int code = 1;
    public MummyHouse(TofslaAndVifsla good){
        Access(good);
        this.code++;
    }

    private void Access(TofslaAndVifsla good){
        String name = good.toString();
        out.println("Мумми-дом: " + Integer.toString(hashCode()) + " " + name + " - доступ разрешен.");
    }

    @Override
    public int hashCode() {
        return (int)(this.code*random()*1707);
    }
}
