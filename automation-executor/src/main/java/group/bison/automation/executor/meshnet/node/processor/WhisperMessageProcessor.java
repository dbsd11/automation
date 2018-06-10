package group.bison.automation.executor.meshnet.node.processor;

import com.alibaba.fastjson.JSON;
import group.bison.automation.common.exception.BusinessException;
import group.bison.automation.executor.meshnet.common.MeshConstants;
import group.bison.automation.executor.meshnet.common.MessageType;
import group.bison.automation.executor.meshnet.node.NetNode;
import group.bison.automation.executor.meshnet.utils.NetUtil;
import group.bison.thrift.automation.meshnet.InternalMessage;

import java.util.List;

/**
 * Created by BSONG on 2018/6/7.
 */
public class WhisperMessageProcessor extends AbstractMessageProcessor {

    public WhisperMessageProcessor(NetNode netNode) {
        super(netNode);
    }

    @Override
    protected boolean handleMessage(InternalMessage message) throws BusinessException {
        if (MessageType.valueOf(message.getMessageType()) == MessageType.HANDSHAKE) {
            List<String> params = JSON.parseArray(new String(message.getBody()), String.class);
            String nodeId = params.get(0);
            String address = params.get(1);

            netNode.getMeshNetServiceManager().add(address);

            InternalMessage handshakeAckMessage = new InternalMessage(message.getId(), System.currentTimeMillis());
            handshakeAckMessage.setMessageType(MessageType.HANDSHAKE_ACK.name());
            handshakeAckMessage.setSender(netNode.getId());
            handshakeAckMessage.setReceiver(address);
            params.clear();
            params.add(netNode.getId());
            params.add(String.join(NetUtil.getLanIP(), ":", String.valueOf(MeshConstants.netPort)));
            params.addAll(netNode.getRouteManager().peers("local"));
            handshakeAckMessage.setBody(JSON.toJSONBytes(params));

            return sendAsync(handshakeAckMessage, false, message1 -> {
                if (MessageType.valueOf(message1.getMessageType()) != MessageType.ACK) {
                    throw new BusinessException("握手失败:" + new String(message1.getBody()));
                }
                netNode.getRouteManager().register(nodeId, address);
                return true;
            });
        }

        if (MessageType.valueOf(message.getMessageType()) == MessageType.HANDSHAKE_ACK) {
            List<String> params = JSON.parseArray(new String(message.getBody()), String.class);
            String nodeId = params.get(0);
            String address = params.get(1);

            netNode.getRouteManager().register(nodeId, address);

            InternalMessage ackMessage = new InternalMessage(message.getId(), System.currentTimeMillis());
            ackMessage.setMessageType(MessageType.ACK.name());
            ackMessage.setSender(netNode.getId());
            ackMessage.setReceiver(message.getSender());
            send(ackMessage, false);
            return true;
        }

        if (MessageType.valueOf(message.getMessageType()) == MessageType.PEERS) {

        }

        if (MessageType.valueOf(message.getMessageType()) == MessageType.NOTIFY) {

        }
        return false;
    }
}
