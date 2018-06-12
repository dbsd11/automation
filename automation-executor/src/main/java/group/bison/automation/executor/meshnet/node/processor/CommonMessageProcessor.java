package group.bison.automation.executor.meshnet.node.processor;

import group.bison.automation.common.exception.BusinessException;
import group.bison.automation.executor.meshnet.common.MessageProcessor;
import group.bison.thrift.automation.meshnet.InternalMessage;

/**
 * Created by BSONG on 2018/6/12.
 */
public abstract class CommonMessageProcessor implements MessageProcessor {

    public final boolean process(InternalMessage message) throws BusinessException {
        return handleMessage(message.getMessageType(), message.getBody());
    }

    public abstract boolean handleMessage(String messageType, byte[] body) throws BusinessException;
}
