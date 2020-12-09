import static java.lang.Math.random;
import static java.lang.System.out;

public class MummyHouse {

    public static abstract class MummyHouseResident implements ILive{}

    public class Carpet implements Movable {
        String position = "Right";

        @Override
        public void carry(String name) {
            out.println(name + " утащили ковер!");
        }

        public void move() {
            this.position = "Wrong";
        }

        public void put() {
            if (this.position.equals("Wrong")) {
                this.position = "Right";
            }
        }

        public void hide(String name) {
            move();
            getUnder(name);
            put();
        }

        private void getUnder(String name) {
            out.println(name + " спрятались под ковер.");
        }
    }

    public class Staircase {
        public void run(String name) throws ExceptionStoryWentWrong {
            if (toTripOver()) {
                throw new ExceptionStoryWentWrong("Бегать по лестницам " +
                        "нельзя - можно же и спотк..." + name + " споткнулись.");
            } else {
                out.println(name + " забегали по лестницам.");
            }
        }


        private boolean toTripOver() {
            return !(random() > 0.1);
        }
    }

    public class Commode implements Movable{

        @Override
        public void carry(String name) {
            throw new ExceptionImpossible();
        }

        @Override
        public void move() {
            out.println("Кто-то зачем-то сдвинул тяжеленный комод...");
        }

        @Override
        public void put() {
            throw new ExceptionImpossible("Никто не в состоянии поднять комод -" +
                    " как его умудрились поставить?! Ты как из дурки сбежал, программист?");
        }

        public void hide(String name){
            out.println(name + " спрятались в ящик комода.");
        }
    }
    public class Door{
        public String toString(){
            return "Дверь";
        }
    }
    public class Sofa implements Movable{
        @Override
        public void carry(String name) {
            throw new ExceptionImpossible();
        }
        @Override
        public void move() {
            out.println("Диван передвинут.");
        }
        @Override
        public void put() {
            throw new ExceptionImpossible("Научите ставить диван... " +
                    "который невозможно поднять?!");
        }
    }

    private int code = 1;

    public void access(ILive good){
        if (equals(good)) {
            this.code++;
        }
    }
    @Override
    public boolean equals(Object good) {
        String name = good.toString();
        out.println("Мумми-дом: " + hashCode() + " " + name + " - доступ разрешен.");
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (this.code * random() * 1707);
    }
}
