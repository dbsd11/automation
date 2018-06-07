package group.bison.automation.common.exception;

/**
 * Created by BSONG on 2018/6/4.
 */
public class ValidationException extends RuntimeException {

    private Integer code;

    public ValidationException(Integer code, String message) {
        this(code, message, null);
    }

    public ValidationException(Integer code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
