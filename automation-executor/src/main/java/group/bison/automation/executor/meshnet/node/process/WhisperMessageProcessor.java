package group.bison.automation.executor.meshnet.node.process;

import group.bison.automation.common.exception.BusinessException;
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
    public boolean process(InternalMessage message) throws BusinessException {
        return false;
    }
}
