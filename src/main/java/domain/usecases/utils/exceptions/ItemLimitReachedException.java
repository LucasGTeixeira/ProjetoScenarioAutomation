package domain.usecases.utils.exceptions;

public class ItemLimitReachedException extends RuntimeException{
    public ItemLimitReachedException(String message){
        super(message);
    }
}
