package domain.usecases.utils.exceptions;

public class CanalLimitReachedException extends RuntimeException{
    public CanalLimitReachedException(String message){
        super(message);
    }
}
