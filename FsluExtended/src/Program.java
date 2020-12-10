import static java.lang.Math.random;
import static java.lang.System.out;

public class Program {

    public static void main(String[] args) throws ExceptionStoryWentWrong {
        MummyHouse house = new MummyHouse();
        Sniff sniff = new Sniff();
        Hemule hemule = new Hemule();
        hemule.cook();
        sniff.cook();
        hemule.runTo(new Movable() {
            @Override
            public String toString() {
                return "Крышка";
            }

            @Override
            public void carry(String name) {
                out.println("Крышку забрали");
            }

            @Override
            public void move() {
                out.println("Крышку подвинули");
            }

            @Override
            public void put() {
                out.println("Крышку положили");
            }
        }, " и что-то приветливо крикнул");
        TofslaAndVifsla tnv = new TofslaAndVifsla("Тофсла и Вифсла");
        tnv.lookAt(hemule.toString());
        tnv.goToLivingroom();
        sniff.lookAt(tnv, house);
        Daytime time = Daytime.DAY;
        tnv.walkTheValley();
        Case cs = new Case(tnv);
        while (time != Daytime.EVENING) {
            time = setEvening();
        }
        MummyHouse.Staircase stc = house.new Staircase();
        MummyHouse.Carpet cpt = house.new Carpet();
        tnv.worryNRun(stc, cpt);
        tnv.getOnlyTofsla().showScary();
        MummyMom mom = new MummyMom();
        hemule.runTo(mom, " и что-то сказал");
        MummyHouse.Commode cmd = house.new Commode();
        MummyHouse.Commode.Locker lck = cmd.new Locker();
        cmd.hide(tnv.toString(), lck);
        MummyDad dad = new MummyDad();
        dad.goTo("в сарай", "за ружьем");
        while (time != Daytime.NIGHT) {
            time = setNight();
        }
        dad.worryAboutMorra();
        dad.goTo("на веранду");
        MummyHouse.Door door = house.new Door();
        MummyHouse.Sofa sofa = house.new Sofa();
        sofa.move(dad, door);
        dad.saySmth();
        lck.silent();
        lck.move(dad);
        tnv.sleep();
        if (Math.random() > 0.9) {
            cs.move();
            throw new ExceptionStoryWentWrong("Украли чемодан!!!");
        } else {
            out.println("Чемодан лежал рядом с " + tnv.toString());
        }
    }

    private static Daytime setEvening() {
        if (random() > 0.3) {
            out.println("Наступили сумерки.");
            return Daytime.EVENING;
        } else {
            out.println("Еще день.");
            return Daytime.DAY;
        }
    }

    private static Daytime setNight() throws ExceptionStoryWentWrong {
        if (random() > 0.3) {
            out.println("На дворе уже было по-августовски темно, сад окутали бархатисто-черные тени.");
            out.println("Что-то мрачно шумело в лесу.");
            class Fireflies implements ILive {
                private final String name = "Светлячки";

                public Fireflies() throws ExceptionStoryWentWrong {
                    isAlive();
                    flash();
                }

                private void flash() {
                    out.println("Мелькали со своими карманными фонариками " + this.name + ".");
                }

                @Override
                public void isAlive() throws ExceptionStoryWentWrong {
                    //Да, уж они-то живы, можете в этом не сомневаться
                }
            }
            new Fireflies();
            return Daytime.NIGHT;
        } else {
            out.println("Еще Сумерки.");
            return Daytime.EVENING;
        }
    }
}
