package group.bison.automation.executor.meshnet.node.processor;

import com.alibaba.fastjson.JSON;
import group.bison.automation.common.exception.BusinessException;
import group.bison.automation.executor.meshnet.common.MessageType;
import group.bison.automation.executor.meshnet.node.LocalNodeProxy;
import group.bison.automation.executor.meshnet.node.NetNode;
import group.bison.automation.executor.meshnet.utils.IDGenerator;
import group.bison.thrift.automation.meshnet.InternalMessage;

import java.util.Arrays;
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
            params.add(LocalNodeProxy.getBindAddress());
            params.addAll(netNode.getRouteManager().peers("local"));
            handshakeAckMessage.setBody(JSON.toJSONBytes(params));

            return sendAsync(handshakeAckMessage, false, message1 -> {
                if (MessageType.valueOf(message1.getMessageType()) != MessageType.ACK) {
                    throw new BusinessException("握手失败:" + new String(message1.getBody()));
                }

                netNode.getRouteManager().register(nodeId, address);

                InternalMessage notifyMessage = new InternalMessage(IDGenerator.getRandomId(), System.currentTimeMillis());
                notifyMessage.setMessageType(MessageType.NOTIFY.name());
                notifyMessage.setSender(netNode.getId());
                notifyMessage.setReceiver(nodeId);
                notifyMessage.setBody(JSON.toJSONBytes(netNode.getNodeStorageManager().keys()));
                send(notifyMessage, false);
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
            List<String> params = JSON.parseArray(new String(message.getBody()), String.class);
            List<String> peers = params.subList(0, params.size());

            for (String peer : peers) {
                if (netNode.getRouteManager().isPeer(IDGenerator.getNodeId(peer))) {
                    if (!netNode.getMeshNetServiceManager().contains(peer)) {
                        netNode.getMeshNetServiceManager().add(peer);
                    }

                    InternalMessage handShakeMessage = new InternalMessage(IDGenerator.getRandomId(), System.currentTimeMillis());
                    handShakeMessage.setMessageType(MessageType.HANDSHAKE.name());
                    handShakeMessage.setSender(netNode.getId());
                    handShakeMessage.setReceiver(peer);
                    handShakeMessage.setBody(JSON.toJSONBytes(Arrays.asList(netNode.getId(), LocalNodeProxy.getBindAddress())));
                    send(handShakeMessage, false);
                }
            }
        }

        if (MessageType.valueOf(message.getMessageType()) == MessageType.NOTIFY) {
            //todo 同步节点存储信息
        }

        if (MessageType.valueOf(message.getMessageType()) == MessageType.STORE) {

        }

        if (MessageType.valueOf(message.getMessageType()) == MessageType.RETRIEVE) {

        }

        if (MessageType.valueOf(message.getMessageType()) == MessageType.RETRIEVED) {

        }

        if (MessageType.valueOf(message.getMessageType()) == MessageType.DELTA) {

        }
        return false;
    }
}
