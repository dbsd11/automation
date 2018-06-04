package group.bison.automation.executor.meshnet.message;

import java.util.Collections;
import java.util.List;

/**
 * Created by BSONG on 2018/6/4.
 */
public class InternalMessage {
    private String id;
    private Long timestamp;
    private String messageType;
    private String sender;
    private Object payload;
    private List<Integer> compression;

    public InternalMessage(String messageType, String sender, List<Integer> compression) {
        this.id = null;
        this.timestamp = System.currentTimeMillis();
        this.messageType = messageType;
        this.sender = sender;
        this.compression = compression == null ? Collections.emptyList() : compression;
    }

    public String getId() {
        return id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getSender() {
        return sender;
    }

    public Object getPayload() {
        return payload;
    }

    public List<Integer> getCompression() {
        return compression;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
