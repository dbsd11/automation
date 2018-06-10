package group.bison.automation.executor.meshnet.node.process;

import group.bison.automation.common.exception.BusinessException;
import group.bison.automation.executor.meshnet.common.MessageType;
import group.bison.automation.executor.meshnet.node.NetNode;
import group.bison.thrift.automation.meshnet.InternalMessage;

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

        }

        if (MessageType.valueOf(message.getMessageType()) == MessageType.PEERS) {

        }

        if (MessageType.valueOf(message.getMessageType()) == MessageType.NOTIFY) {

        }

        if (MessageType.valueOf(message.getMessageType()) == MessageType.RENEGOTIATE) {

        }

        if (MessageType.valueOf(message.getMessageType()) == MessageType.RESEND) {

        }
        return false;
    }
}
