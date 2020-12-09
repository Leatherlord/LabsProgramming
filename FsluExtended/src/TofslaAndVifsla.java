import static java.lang.Math.random;
import static java.lang.System.out;

public class TofslaAndVifsla extends Stranger implements IRun{
    private final int size = 25;
    private String name = "Незнакомцы";

    public TofslaAndVifsla(String name){
        setName(name);
    }

    public TofslaAndVifsla(){}

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

    public void walkTheValley() throws ExceptionStoryWentWrong{
        if (cockTheNose() | !together()) {
            throw new ExceptionStoryWentWrong();
        } else {
            out.println(toString() + " ни перед кем не задирали носа и почти все время " +
                    "бродили по долине рука об руку.");
        }
    }
    public void worryNRun(MummyHouse.Staircase stc, MummyHouse.Carpet cpt) throws ExceptionStoryWentWrong {
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

    public void lookAt(String name) throws ExceptionStoryWentWrong {
        isAlive();
        out.println(this.name + " высунули головы из картошки и посмотрели на " + name + ".");
    }

    public void goToLivingroom() throws ExceptionStoryWentWrong {
        isAlive();
        out.println(this.name + " поднялись в гостиную.");
    }

    public void showScary(){
        out.println(this.name + " вытаращили глаза, оскалили зубы и напыжились, как только могли.");
    }

    public TofslaAndVifsla getOnlyTofsla(){
        class Tofsla extends TofslaAndVifsla{
            private String name = "Тофсла";
            private int size = 12;

            public void showScary(){
                out.println(this.name + " вытаращил глаза, оскалил зубы и напыжился, как только мог.");
            }
            public int getSize() throws ExceptionStoryWentWrong {
                isAlive();
                return this.size;
            }

            private boolean cockTheNose() throws ExceptionStoryWentWrong {
                isAlive();
                return false;
            }

            private boolean together() throws ExceptionStoryWentWrong {
                isAlive();
                return false;
            }

            public void walkTheValley() throws ExceptionStoryWentWrong{
                if (cockTheNose() | !together()) {
                    throw new ExceptionStoryWentWrong("Тофсла ушел от Вифслы бродить по долине в одиночку...");
                } else {
                    out.println(toString() + " ни перед кем не задирал носа и почти все время " +
                            "бродил по долине рука об руку с Вифслой.");
                }
            }
            public void worryNRun(MummyHouse.Staircase stc, MummyHouse.Carpet cpt) throws ExceptionStoryWentWrong {
                isAlive();
                out.println(this.name + " забеспокоился!");
                throw new ExceptionStoryWentWrong("И в страхе убежал от Вифслы, оставив его одного...");
            }

            public void isAlive() throws ExceptionStoryWentWrong {
                if (random() < 0.01) {
                    throw new ExceptionStoryWentWrong(this.name + " мертв! " +
                            "История пошла не по тому сценарию...");
                }
            }

            public void lookAt(String name) throws ExceptionStoryWentWrong {
                isAlive();
                out.println(this.name + " высунул голову из картошки и посмотрел на " + name + ".");
            }

            public void goToLivingroom() throws ExceptionStoryWentWrong {
                isAlive();
                out.println(this.name + " поднялся в гостиную.");
                throw new ExceptionStoryWentWrong("Но Вифсла остался там же...");
            }
            public void runTo(Object obj) throws ExceptionStoryWentWrong {
                isAlive();
                System.out.println(this.name + " прибежал к " + obj.toString() + ".");
                throw new ExceptionStoryWentWrong("Но Вифсла остался там же...");
            }
            public void runTo(Object obj, String str) throws ExceptionStoryWentWrong {
                isAlive();
                System.out.println(this.name + " прибежал к " + obj.toString() + str + ".");
                throw new ExceptionStoryWentWrong("Но Вифсла остался там же...");
            }
            public void run() {
                out.println("Тофсла бегает из стороны в сторону.");
            }
        }
        Tofsla t = new Tofsla();
        return t;
    }

    @Override
    public void runTo(Object obj) throws ExceptionStoryWentWrong {
        isAlive();
        System.out.println(this.name + " прибежали к " + obj.toString() + ".");
    }
    public void runTo(Object obj, String str) throws ExceptionStoryWentWrong {
        isAlive();
        System.out.println(this.name + " прибежали к " + obj.toString() + str + ".");
    }
    @Override
    public void run() {
        out.println("Тофсла и Вифсла бегают.");
    }
}
