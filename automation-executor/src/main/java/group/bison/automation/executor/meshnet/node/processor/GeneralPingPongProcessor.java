package group.bison.automation.executor.meshnet.node.processor;

import group.bison.automation.executor.meshnet.common.PingPongProcessor;
import group.bison.automation.executor.meshnet.node.NetNode;

/**
 * Created by BSONG on 2018/6/7.
 */
public final class GeneralPingPongProcessor implements PingPongProcessor {
    private NetNode netNode;

    public GeneralPingPongProcessor(NetNode netNode) {
        this.netNode = netNode;
    }

    @Override
    public void ping(String sender, String receiver) {

    }

    @Override
    public void pong(String sender, String receiver) {

    }
}
