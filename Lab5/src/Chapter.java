public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long marinesCount; //Значение поля должно быть больше 0, Максимальное значение поля: 1000

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarinesCount(int i) {
        this.marinesCount = i;
    }

    public void addCount() {
        this.marinesCount++;
    }

}
