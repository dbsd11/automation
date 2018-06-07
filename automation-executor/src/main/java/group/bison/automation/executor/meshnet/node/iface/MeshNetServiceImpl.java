package group.bison.automation.executor.meshnet.node.iface;

import group.bison.automation.executor.meshnet.common.MessageProcessor;
import group.bison.automation.executor.meshnet.common.PingPongProcessor;
import group.bison.thrift.automation.meshnet.InternalMessage;
import group.bison.thrift.automation.meshnet.MeshNetService;
import org.apache.thrift.TException;
import org.apache.thrift.server.TServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by BSONG on 2018/6/7.
 */
public class MeshNetServiceImpl implements MeshNetService.Iface {
    private static final Logger LOG = LoggerFactory.getLogger(MeshNetServiceImpl.class);

    private List<MessageProcessor> messageProcessorList;
    private PingPongProcessor pingPongProcessor;
    private TServer server;

    public MeshNetServiceImpl() {

    }

    @Override
    public void whisper(InternalMessage message) throws TException {
        LOG.info("handle whisper message size:{}", message.getBody().length);

        for (MessageProcessor messageProcessor : messageProcessorList) {
            try {
                if (messageProcessor.process(message)) {
                    break;
                }
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void broadcast(InternalMessage message) throws TException {
        LOG.info("handle broadcast message size:{}", message.getBody().length);

        for (MessageProcessor messageProcessor : messageProcessorList) {
            try {
                if (messageProcessor.process(message)) {
                    break;
                }
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void ping(String sender, String receiver) throws TException {
        LOG.info("handle ping sender:{} receiver:{}", sender, receiver);

        pingPongProcessor.ping(sender, receiver);
    }

    @Override
    public void pong(String sender, String receiver) throws TException {
        LOG.info("handle pong sender:{} receiver:{}", sender, receiver);

        pingPongProcessor.pong(sender, receiver);
    }

    public void stop() {
        server.stop();
    }

    public List<MessageProcessor> getMessageProcessorList() {
        return messageProcessorList;
    }

    public void setMessageProcessorList(List<MessageProcessor> messageProcessorList) {
        this.messageProcessorList = messageProcessorList;
    }

    public PingPongProcessor getPingPongProcessor() {
        return pingPongProcessor;
    }

    public void setPingPongProcessor(PingPongProcessor pingPongProcessor) {
        this.pingPongProcessor = pingPongProcessor;
    }
}
