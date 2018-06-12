package group.bison.automation.executor.meshnet.node;

import group.bison.automation.executor.meshnet.common.MeshNetServiceManager;
import group.bison.automation.executor.meshnet.common.MessageProcessor;
import group.bison.automation.executor.meshnet.common.NodeStorageManager;
import group.bison.automation.executor.meshnet.common.PingPongProcessor;
import group.bison.automation.executor.meshnet.common.RouteManager;
import group.bison.automation.executor.meshnet.node.iface.MeshNetServiceImpl;
import group.bison.automation.executor.meshnet.utils.IDGenerator;

import java.util.List;

/**
 * Created by BSONG on 2018/6/7.
 */
public class NetNode {
    private String id;
    private Long timestamp;
    private RouteManager routeManager;
    private MeshNetServiceManager meshNetServiceManager;
    private NodeStorageManager nodeStorageManager;
    private List<MessageProcessor> messageProcessorList;
    private PingPongProcessor pingPongProcessor;
    private MeshNetServiceImpl localMeshNetService;

    NetNode() {
        this.id = IDGenerator.getNodeId();
        this.timestamp = System.currentTimeMillis();
    }

    void doStart() {
        localMeshNetService = new MeshNetServiceImpl();
        localMeshNetService.setMessageProcessorList(messageProcessorList);
        localMeshNetService.setPingPongProcessor(pingPongProcessor);

        Runtime.getRuntime().addShutdownHook(new Thread(this::shutDown));
    }

    void shutDown() {
        localMeshNetService.stop();
    }

    void setRouteManager(RouteManager routeManager) {
        this.routeManager = routeManager;
    }

    void setMeshNetServiceManager(MeshNetServiceManager meshNetServiceManager) {
        this.meshNetServiceManager = meshNetServiceManager;
    }

    void setNodeStorageManager(NodeStorageManager nodeStorageManager) {
        this.nodeStorageManager = nodeStorageManager;
    }

    void setMessageProcessorList(List<MessageProcessor> messageProcessorList) {
        this.messageProcessorList = messageProcessorList;
    }

    void setPingPongProcessor(PingPongProcessor pingPongProcessor) {
        this.pingPongProcessor = pingPongProcessor;
    }

    public String getId() {
        return id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public RouteManager getRouteManager() {
        return routeManager;
    }

    public MeshNetServiceManager getMeshNetServiceManager() {
        return meshNetServiceManager;
    }

    public NodeStorageManager getNodeStorageManager() {
        return nodeStorageManager;
    }

    public List<MessageProcessor> getMessageProcessorList() {
        return messageProcessorList;
    }

    public PingPongProcessor getPingPongProcessor() {
        return pingPongProcessor;
    }
}
