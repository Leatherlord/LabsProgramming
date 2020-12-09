import static java.lang.System.out;

public class Case implements Movable {
    public Case(ILive good) throws ExceptionStoryWentWrong {
        if (!good.toString().equalsIgnoreCase(
                "Тофсла и Вифсла")) {
            throw new ExceptionStoryWentWrong(
                    "Чемодан украли!");
        } else {
            out.println("Чемодан: владелец подтвержден.");
            carry(good.toString());
        }
    }

    public void carry(String name) {
        out.println(name + " таскают чемодан с собой.");
    }

    public void move() {
        out.println("Чемодан сдвинулся с места");
    }

    public void put() {
        out.println("Чемодан поставили");
    }

}
