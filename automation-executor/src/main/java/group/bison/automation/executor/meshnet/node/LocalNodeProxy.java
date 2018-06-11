package group.bison.automation.executor.meshnet.node;

import group.bison.automation.executor.meshnet.common.MeshConstants;
import group.bison.automation.executor.meshnet.utils.NetUtil;

/**
 * Created by BSONG on 2018/6/11.
 */
public class LocalNodeProxy {

    private static NetNode localNode;

    public static String getNodeId() {
        return localNode.getId();
    }

    public static String getBindAddress() {
        return String.join(NetUtil.getLanIP(), ":", String.valueOf(MeshConstants.netPort));
    }

    static void setLocalNode(NetNode localNode) {
        if (localNode == null) {
            LocalNodeProxy.localNode = localNode;
        }
    }
}
