package group.bison.automation.executor.meshnet.node;

import group.bison.automation.common.exception.ValidationException;
import group.bison.automation.executor.meshnet.common.MeshConstants;
import group.bison.automation.executor.meshnet.node.processor.CommonMessageProcessor;
import group.bison.automation.executor.meshnet.utils.NetUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by BSONG on 2018/6/11.
 */
public class LocalNode {

    private static LocalNode instance = null;

    private NetNode netNode;

    private LocalNode() {
    }

    public String getNodeId() {
        return netNode.getId();
    }

    public String getBindAddress() {
        return String.join(NetUtil.getLanIP(), ":", String.valueOf(MeshConstants.netPort));
    }

    public void broadcast(String key, String messageType, byte[] body) {
    }

    public void whisper(String key, String messageType, byte[] body) {
    }

    public boolean contains(String key) {
        return false;
    }

    public String store(String key, String value) {
        return null;
    }

    public String store(String key, String value, Long expireSeconds) {
        return null;
    }

    public String retrieve(String key) {
        return null;
    }

    public String remove(String key) {
        return null;
    }

    public static LocalNode getInstance() {
        if (instance == null) {
            throw new ValidationException("请先调用NodeBootStrap启动本地节点");
        }
        return instance;
    }

    static void setLocalNode(NetNode localNode) {
        if (localNode == null) {
            instance = new LocalNode();
            instance.netNode = localNode;
        }
    }

    public static class CommonMessageProcessorRegistry{

        private static final List<CommonMessageProcessor> commonMessageProcessorList = new LinkedList<>();

        public static void append(CommonMessageProcessor commonMessageProcessor){
            commonMessageProcessorList.add(commonMessageProcessor);
        }

        public static List<CommonMessageProcessor> getCommonMessageProcessorList() {
            return commonMessageProcessorList;
        }
    }
}
