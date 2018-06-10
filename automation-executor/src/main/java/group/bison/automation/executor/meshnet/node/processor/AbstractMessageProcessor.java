package group.bison.automation.executor.meshnet.node.processor;

import group.bison.automation.common.exception.BusinessException;
import group.bison.automation.executor.meshnet.common.MeshConstants;
import group.bison.automation.executor.meshnet.common.MessageProcessor;
import group.bison.automation.executor.meshnet.node.NetNode;
import group.bison.automation.executor.meshnet.utils.NetUtil;
import group.bison.thrift.automation.meshnet.InternalMessage;
import group.bison.thrift.automation.meshnet.MeshNetService;
import io.reactivex.functions.Function;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by BSONG on 2018/6/7.
 */
public abstract class AbstractMessageProcessor implements MessageProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractMessageProcessor.class);

    protected NetNode netNode;
    private Map<String, Function> messageIdCallBackMap;

    public AbstractMessageProcessor(NetNode netNode) {
        this.netNode = netNode;
        this.messageIdCallBackMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean process(InternalMessage message) throws BusinessException {
        if (!StringUtils.isEmpty(message.getReceiver()) && !message.getReceiver().equals(netNode.getId()) && !message.getReceiver().equals(String.join(NetUtil.getLanIP(), ":", String.valueOf(MeshConstants.netPort)))) {
            return false;
        }

        boolean success = handleMessage(message);
        if (success && messageIdCallBackMap.containsKey(message.getId())) {
            Function callBack = messageIdCallBackMap.remove(message.getId());
            try {
                callBack.apply(message);
            } catch (Exception e) {
                throw new BusinessException(-1, "成功处理消息但是回调失败", e);
            }
        }
        return success;
    }

    protected abstract boolean handleMessage(InternalMessage message) throws BusinessException;

    @Override
    public boolean send(InternalMessage message, Boolean broadcast) throws BusinessException {
        LOG.info("send InternalMessage message broadcast:{} type:{} receiver:{} size:{}", broadcast, message.getMessageType(), message.getReceiver(), message.getBody().length);

        Boolean success = true;

        if (!broadcast && (netNode.getRouteManager().contains(message.getReceiver()) || netNode.getMeshNetServiceManager().contains(message.getReceiver()))) {
            try {
                String address = netNode.getRouteManager().contains(message.getReceiver()) ? netNode.getRouteManager().peers(message.getReceiver()).toArray(new String[0])[0] : message.getReceiver();
                netNode.getMeshNetServiceManager().get(address).whisper(message);
            } catch (TException e) {
                success = false;
            }
        } else {
            for (MeshNetService.Iface meshNetService : netNode.getMeshNetServiceManager().getAll()) {
                try {
                    meshNetService.broadcast(message);
                    success = true;
                } catch (TException e) {
                    success = false;
                }
            }
        }

        if (!success) {
            throw new BusinessException("发送消息失败");
        }

        return true;
    }

    @Override
    public boolean sendAsync(InternalMessage message, Boolean broadcast, Function<InternalMessage, Boolean> func) throws BusinessException {
        if (messageIdCallBackMap.containsKey(message.getId())) {
            throw new BusinessException("重复发送异步消息 id:" + message.getId());
        }

        boolean success = send(message, broadcast);
        if (message.getSender().equals(netNode.getId())) {
            messageIdCallBackMap.put(message.getId(), func);
        }
        return success;
    }
}
