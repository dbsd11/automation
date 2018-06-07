package group.bison.automation.executor.meshnet.node.process;

import group.bison.automation.common.exception.BusinessException;
import group.bison.automation.executor.meshnet.common.MessageProcessor;
import group.bison.automation.executor.meshnet.node.NetNode;
import group.bison.thrift.automation.meshnet.InternalMessage;
import group.bison.thrift.automation.meshnet.MeshNetService;
import org.apache.thrift.TException;

/**
 * Created by BSONG on 2018/6/7.
 */
public abstract class AbstractMessageProcessor implements MessageProcessor {
    private NetNode netNode;

    public AbstractMessageProcessor(NetNode netNode) {
        this.netNode = netNode;
    }

    @Override
    public boolean send(InternalMessage message) throws BusinessException {
        Boolean success = true;

        if (netNode.getRouteManager().contains(message.getReceiver())) {
            try {
                netNode.getRouteManager().getMeshNetService(message.getReceiver()).whisper(message);
            } catch (TException e) {
                success = false;
            }
        } else {
            for (MeshNetService.Iface meshNetService : netNode.getRouteManager().getMeshNetServiceList()) {
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
}
