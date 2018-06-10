package group.bison.automation.executor.meshnet.common;

import group.bison.automation.common.exception.BusinessException;
import group.bison.thrift.automation.meshnet.InternalMessage;
import io.reactivex.functions.Function;

/**
 * Created by BSONG on 2018/6/7.
 */
public interface MessageProcessor {

    public boolean process(InternalMessage message) throws BusinessException;

    public boolean send(InternalMessage message, Boolean broadcast) throws BusinessException;

    public boolean sendAsync(InternalMessage message, Boolean broadcast, Function<InternalMessage, Boolean> func) throws BusinessException;
}
