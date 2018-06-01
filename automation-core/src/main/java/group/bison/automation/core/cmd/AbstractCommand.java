package group.bison.automation.core.cmd;

public abstract class AbstractCommand {

    private Object param;
    private String nonce;
    private Long timestamp;

    public AbstractCommand(String nonce, Long timestamp) {
        this.nonce = nonce;
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }
}
