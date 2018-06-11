package group.bison.automation.executor.meshnet.node;

import group.bison.automation.executor.meshnet.common.MeshConstants;
import group.bison.automation.executor.meshnet.common.MeshNetServiceManager;
import group.bison.automation.executor.meshnet.common.NodeStorageManager;
import group.bison.automation.executor.meshnet.common.RouteManager;
import group.bison.automation.executor.meshnet.node.manager.MemMeshNetServiceManager;
import group.bison.automation.executor.meshnet.node.manager.MemNodeStorageManager;
import group.bison.automation.executor.meshnet.node.manager.MemRouteManager;
import group.bison.automation.executor.meshnet.node.processor.BroadcastMessageProcessor;
import group.bison.automation.executor.meshnet.node.processor.GeneralPingPongProcessor;
import group.bison.automation.executor.meshnet.node.processor.WhisperMessageProcessor;
import group.bison.automation.executor.meshnet.utils.NetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by BSONG on 2018/6/7.
 */
public class NodeBootStrap {
    private static final Logger LOG = LoggerFactory.getLogger(NodeBootStrap.class);

    public static void start() {
        NetNode netNode = new NetNode();
        LOG.info("starting meshnet node with id:{} timestamp:{}", netNode.getId(), netNode.getTimestamp());

        RouteManager routeManager = new MemRouteManager();
        netNode.setRouteManager(routeManager);

        MeshNetServiceManager meshNetServiceManager = new MemMeshNetServiceManager();
        netNode.setMeshNetServiceManager(meshNetServiceManager);

        NodeStorageManager nodeStorageManager = new MemNodeStorageManager();
        netNode.setNodeStorageManager(nodeStorageManager);

        netNode.setMessageProcessorList(Arrays.asList(new WhisperMessageProcessor(netNode), new BroadcastMessageProcessor(netNode)));
        netNode.setPingPongProcessor(new GeneralPingPongProcessor(netNode));

        netNode.doStart();

        LocalNodeProxy.setLocalNode(netNode);
        LOG.info("successful started meshnet node on ip:{} port:{}", NetUtil.getLanIP(), MeshConstants.netPort);
    }

    public static void main(String[] args) {
        start();
    }
}
