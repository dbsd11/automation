package group.bison.automation.executor.meshnet.common;

/**
 * Created by BSONG on 2018/6/7.
 */
public interface PingPongProcessor {

    public void ping(String sender, String receiver);

    public void pong(String sender, String receiver);
}
