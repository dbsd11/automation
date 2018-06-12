package group.bison.automation.executor.meshnet.common;

import group.bison.automation.common.exception.BusinessException;
import group.bison.thrift.automation.meshnet.InternalMessage;

/**
 * Created by BSONG on 2018/6/7.
 */
public interface MessageProcessor {

    public boolean process(InternalMessage message) throws BusinessException;
}
