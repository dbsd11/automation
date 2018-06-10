package group.bison.automation.executor.meshnet.node.processor;

import com.alibaba.fastjson.JSON;
import group.bison.automation.common.exception.BusinessException;
import group.bison.automation.executor.meshnet.common.MessageType;
import group.bison.automation.executor.meshnet.node.NetNode;
import group.bison.automation.executor.meshnet.utils.IDGenerator;
import group.bison.thrift.automation.meshnet.InternalMessage;

/**
 * Created by BSONG on 2018/6/7.
 */
public class BroadcastMessageProcessor extends AbstractMessageProcessor {

    public BroadcastMessageProcessor(NetNode netNode) {
        super(netNode);
    }

    @Override
    protected boolean handleMessage(InternalMessage message) throws BusinessException {
        if (MessageType.valueOf(message.getMessageType()) == MessageType.REQUEST) {
            String nodeId = new String(message.getBody());
            if (nodeId.equalsIgnoreCase("*")) {
                InternalMessage responseMessage = new InternalMessage(message.getId(), System.currentTimeMillis());
                responseMessage.setMessageType(MessageType.RESPONSE.name());
                responseMessage.setSender(netNode.getId());
                responseMessage.setReceiver(message.getSender());
                responseMessage.setBody(JSON.toJSONBytes(netNode.getRouteManager().peers("local")));
                return send(responseMessage, true);
            } else {
                if (netNode.getRouteManager().contains(nodeId)) {
                    InternalMessage responseMessage = new InternalMessage(message.getId(), System.currentTimeMillis());
                    responseMessage.setMessageType(MessageType.RESPONSE.name());
                    responseMessage.setSender(netNode.getId());
                    responseMessage.setReceiver(message.getSender());
                    responseMessage.setBody(JSON.toJSONBytes(netNode.getRouteManager().peers(nodeId)));
                    return send(responseMessage, true);
                }

                //如果没找到则询问其他节点
                InternalMessage requestMessage2 = new InternalMessage(message);
                requestMessage2.setId(IDGenerator.getRandomId());
                requestMessage2.setTimestamp(System.currentTimeMillis());
                requestMessage2.setSender(netNode.getId());
                requestMessage2.setReceiver(null);
                return sendAsync(message, true, message1 -> {
                    InternalMessage responseMessage = new InternalMessage(message1.getId(), System.currentTimeMillis());
                    responseMessage.setMessageType(MessageType.RESPONSE.name());
                    responseMessage.setSender(netNode.getId());
                    responseMessage.setReceiver(message1.getSender());
                    responseMessage.setBody(JSON.toJSONBytes(netNode.getRouteManager().peers(nodeId)));
                    return send(responseMessage, true);
                });
            }
        }

        if (MessageType.valueOf(message.getMessageType()) == MessageType.RESPONSE) {
            return true;
        }
        return false;
    }
}
