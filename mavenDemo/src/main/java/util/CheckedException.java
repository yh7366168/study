package util;

/**
 * 检查性异常，checked exception，受编译器检查，不tryCatch或者throws处理编译报错
 * */
public class CheckedException extends Exception {

    public CheckedException(){
        super();
    }

    public CheckedException(String message){
        super(message);
    }

    public CheckedException(String message, Throwable cause){
        super(message, cause);
    }

    public CheckedException(Throwable cause){
        super(cause);
    }

}
