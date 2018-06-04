package group.bison.automation.executor.meshnet.protocol;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by BSONG on 2018/6/4.
 */
public abstract class BaseConnection {
    private String id;
    private Long timeStamp;
    private byte[] buffer;
    private List<Integer> compression;
    private Object lastSent;
    private Integer expected;
    private Boolean active;
    private Socket socket;
    private Boolean outGoing;
    private Object server;

    public BaseConnection(Socket socket, Object server, Boolean outGoing) {
        this.id = null;
        this.timeStamp = System.currentTimeMillis();
        this.buffer = new byte[0];
        this.compression = new LinkedList<>();
        this.expected = 4;
        this.active = false;
        this.socket = socket;
        this.server = server;
        this.outGoing = outGoing;
    }

    public String getId() {
        return id;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public List<Integer> getCompression() {
        return compression;
    }

    public Object getLastSent() {
        return lastSent;
    }

    public Integer getExpected() {
        return expected;
    }

    public Boolean getActive() {
        return active;
    }

    public Socket getSocket() {
        return socket;
    }

    public Boolean getOutGoing() {
        return outGoing;
    }

    public Object getServer() {
        return server;
    }
}
