package group.bison.automation.executor.meshnet.node;

import group.bison.automation.executor.meshnet.common.MeshNetServiceManager;
import group.bison.automation.executor.meshnet.node.iface.MeshNetServiceImpl;
import group.bison.automation.executor.meshnet.common.MessageProcessor;
import group.bison.automation.executor.meshnet.common.PingPongProcessor;
import group.bison.automation.executor.meshnet.common.RouteManager;
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
    private List<MessageProcessor> messageProcessorList;
    private PingPongProcessor pingPongProcessor;
    private MeshNetServiceImpl localMeshNetService;

    public NetNode() {
        this.id = IDGenerator.getNodeId();
        this.timestamp = System.currentTimeMillis();
    }

    public void doStart() {
        localMeshNetService = new MeshNetServiceImpl();
        localMeshNetService.setMessageProcessorList(messageProcessorList);
        localMeshNetService.setPingPongProcessor(pingPongProcessor);

        Runtime.getRuntime().addShutdownHook(new Thread(this::shutDown));
    }

    public void shutDown() {
        localMeshNetService.stop();
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

    public void setRouteManager(RouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public MeshNetServiceManager getMeshNetServiceManager() {
        return meshNetServiceManager;
    }

    public void setMeshNetServiceManager(MeshNetServiceManager meshNetServiceManager) {
        this.meshNetServiceManager = meshNetServiceManager;
    }

    public List<MessageProcessor> getMessageProcessorList() {
        return messageProcessorList;
    }

    public void setMessageProcessorList(List<MessageProcessor> messageProcessorList) {
        this.messageProcessorList = messageProcessorList;
    }

    public PingPongProcessor getPingPongProcessor() {
        return pingPongProcessor;
    }

    public void setPingPongProcessor(PingPongProcessor pingPongProcessor) {
        this.pingPongProcessor = pingPongProcessor;
    }
}
