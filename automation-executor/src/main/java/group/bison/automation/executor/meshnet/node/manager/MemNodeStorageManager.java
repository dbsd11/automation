package group.bison.automation.executor.meshnet.node.manager;

import group.bison.automation.executor.meshnet.common.NodeStorageManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by BSONG on 2018/6/11.
 */
public class MemNodeStorageManager implements NodeStorageManager {

    private Map<String, Long> expireMap = new ConcurrentHashMap<>();

    private Map<String, String> kvMap = new ConcurrentHashMap<>();

    @Override
    public boolean contains(String key) {
        return retrieve(key) != null;
    }

    @Override
    public String store(String key, String value) {
        return store(key, value, -1l);
    }

    @Override
    public String store(String key, String value, Long expireSeconds) {
        String oldVT = kvMap.put(key, String.join(value, "|", String.valueOf(System.currentTimeMillis())));
        if (expireSeconds > 0) {
            expireMap.put(key, expireSeconds);
        }
        return oldVT == null ? null : oldVT.substring(0, oldVT.lastIndexOf('|'));
    }

    @Override
    public String remove(String key) {
        String oldVT = kvMap.remove(key);
        expireMap.remove(key);
        return oldVT == null ? null : oldVT.substring(0, oldVT.lastIndexOf('|'));
    }

    @Override
    public String retrieve(String key) {
        String vT = kvMap.containsKey(key) ? kvMap.get(key) : null;
        if (vT != null && expireMap.containsKey(key)) {
            Long timestamp = Long.valueOf(vT.substring(vT.lastIndexOf('|') + 1));
            if (System.currentTimeMillis() - timestamp > expireMap.get(key)) {
                kvMap.remove(key);
                expireMap.remove(key);
                vT = null;
            }
        }
        return vT == null ? null : vT.substring(0, vT.lastIndexOf('|'));
    }

    @Override
    public List<String> retrieve(List<String> keys) {
        List<String> valueList = new LinkedList<>();
        for (String key : keys) {
            valueList.add(retrieve(key));
        }
        return valueList;
    }

    @Override
    public Set<String> keys() {
        return kvMap.keySet();
    }
}
