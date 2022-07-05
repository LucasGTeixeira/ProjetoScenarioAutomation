package domain.usecases.utils.exceptions;

public class LuminariasLimitReachedException extends RuntimeException{
    public LuminariasLimitReachedException(String message){
        super(message);
    }
}
