import static java.lang.Math.random;

public class MummyMom extends MummyHouse.MummyHouseResident{
    private String name = "Мумми-Мама";
    @Override
    public void isAlive() throws ExceptionStoryWentWrong {
        if (random() < 0.05) {
            throw new ExceptionStoryWentWrong("Мумми-Мама мертва!" +
                    " История пошла не по тому сценарию...");
        }
    }
    @Override
    public String toString() {
        return this.name;
    }

}
