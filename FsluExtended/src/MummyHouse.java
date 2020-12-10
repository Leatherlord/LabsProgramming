import static java.lang.Math.random;
import static java.lang.System.out;

public class MummyHouse {

    private int code = 1;

    public void access(ILive good) {
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

    static abstract class MummyHouseResident implements ILive {
    }

    class Carpet implements Movable {
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

    class Staircase {
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

    class Commode implements Movable {
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

        public void hide(String name, Locker lck) {
            out.println(name + " спрятались в " + lck.toString() + ".");
        }

        class Locker implements Movable {
            @Override
            public String toString() {
                return "Ящик комода";
            }

            public void silent() {
                out.println("Как бы то ни было СТРАННО, ящик безмолвствовал.");
            }

            @Override
            public void carry(String name) {
                out.println("Кто-то унес ящик комода.");
            }

            @Override
            public void move() {
                out.println("Кто-то открыл ящик комода.");
            }

            public void move(ILive prsn) {
                out.println(prsn.toString() + " открыл ящик комода.");
            }

            @Override
            public void put() {
                out.println("Кто-то вернул ящик комода на место.");
            }
        }
    }

    class Door {
        public String toString() {
            return "Дверь";
        }
    }

    class Sofa implements Movable {
        @Override
        public void carry(String name) {
            throw new ExceptionImpossible();
        }

        @Override
        public void move() {
            out.println("Диван передвинут.");
        }

        public void move(ILive pers, Object obj) {
            out.println(pers.toString() + " передвинул диван к " + obj.toString() + ".");
        }

        @Override
        public void put() {
            throw new ExceptionImpossible("Научите ставить диван... " +
                    "который невозможно поднять?!");
        }
    }
}
