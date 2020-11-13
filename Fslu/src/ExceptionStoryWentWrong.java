public class ExceptionStoryWentWrong extends Exception {
    public ExceptionStoryWentWrong(String description){
        super(description);
    }
    public ExceptionStoryWentWrong(){
        super("В вашей истории что-то пошло не так...");
    }

}
