package group.bison.automation.executor.meshnet.node.manager;

import group.bison.automation.executor.meshnet.common.RouteManager;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by BSONG on 2018/6/7.
 */
public class MemRouteManager implements RouteManager {
    private Map<String, String> addressMap = new ConcurrentHashMap<>();

    @Override
    public boolean contains(String node) {
        return addressMap.values().contains(node);
    }

    @Override
    public void register(String node, String address) {
        addressMap.put(address, node);
    }

    @Override
    public void unregister(String node) {
        Iterator<Map.Entry<String, String>> addressEntryIt = addressMap.entrySet().iterator();

        while (addressEntryIt.hasNext()) {
            Map.Entry<String, String> addressEntry = addressEntryIt.next();
            if (addressEntry.getValue().equals(node)) {
                addressEntryIt.remove();
            }
        }
    }

    @Override
    public void update(String node, String address) {
        unregister(node);
        register(node, address);
    }

    @Override
    public Set<String> peers(String node) {
        Iterator<Map.Entry<String, String>> addressEntryIt = addressMap.entrySet().iterator();
        while (addressEntryIt.hasNext()) {
            Map.Entry<String, String> addressEntry = addressEntryIt.next();
            if (addressEntry.getValue().equals(node)) {
                return Collections.singleton(addressEntry.getKey());
            }
        }
        return addressMap.keySet();
    }

}
