import static java.lang.Math.random;

public class MummyDad extends MummyHouse.MummyHouseResident {
    private final String name = "Мумми-Папа";

    @Override
    public void isAlive() throws ExceptionStoryWentWrong {
        if (random() < 0.05) {
            throw new ExceptionStoryWentWrong("Мумми-Папа мертв!" +
                    " История пошла не по тому сценарию...");
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void goTo(String str) throws ExceptionStoryWentWrong {
        isAlive();
        System.out.println(this.name + " пошел " + str + ".");
    }

    public void goTo(String str, String str2) throws ExceptionStoryWentWrong {
        isAlive();
        System.out.println(this.name + " пошел " + str + " " + str2 + ".");
    }

    public void worryAboutMorra() throws ExceptionStoryWentWrong {
        System.out.println("Страшновато было Мумми-Папе - вдруг Морра за кустом?!" +
                " Он же даже не знает, кто она такая и какого роста!");
        if (Math.random() < 0.05) {
            throw new ExceptionStoryWentWrong("А ведь не зря боялся..." +
                    " Морра съедает Мумми-Папу, а затем и всех" +
                    " остальных жителей Мумми-дома.");
        }
    }

    public void saySmth() {
        System.out.println(this.name + " что-то объявил.");
    }
}
