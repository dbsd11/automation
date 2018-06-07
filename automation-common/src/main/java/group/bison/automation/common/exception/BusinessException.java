package group.bison.automation.common.exception;

/**
 * Created by BSONG on 2018/6/4.
 */
public class BusinessException extends RuntimeException {

    private Integer code;

    public BusinessException(String message) {
        this(-1, message, null);
    }

    public BusinessException(Integer code, String message) {
        this(code, message, null);
    }

    public BusinessException(Integer code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
