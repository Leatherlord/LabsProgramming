public class ExceptionImpossible extends RuntimeException {
    public ExceptionImpossible(){
        super("Произашло что-то невозможное настолько, что для этого пришлось писать новый Exception!");
    }

    public ExceptionImpossible(String str){
        super(str);
    }
}
