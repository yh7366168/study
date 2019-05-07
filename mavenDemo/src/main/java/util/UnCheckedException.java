package util;

/**
 * 非检查性异常，
 * */
public class UnCheckedException extends RuntimeException{

    public UnCheckedException(){
        super();
    }

    public UnCheckedException(String message){
        super(message);
    }

    public UnCheckedException(String message, Throwable cause){
        super(message, cause);
    }

    public UnCheckedException(Throwable cause){
        super(cause);
    }
}
