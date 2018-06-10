package group.bison.automation.executor.meshnet.node.route;

import group.bison.automation.executor.meshnet.common.RouteManager;
import group.bison.thrift.automation.meshnet.MeshNetService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by BSONG on 2018/6/7.
 */
public class MemRouteManager implements RouteManager {
    private Map<String, String> addressMap = new ConcurrentHashMap<>();
    private Map<MeshNetService.Iface, String> meshNetServiceMap = new ConcurrentHashMap<>();

    @Override
    public boolean contains(String node) {
        return addressMap.values().contains(node);
    }

    @Override
    public void register(String node, MeshNetService.Iface meshNetService, String address) {
        addressMap.put(address, node);
        meshNetServiceMap.put(meshNetService, node);
    }

    @Override
    public void unregister(String node) {
        Iterator<Map.Entry<String, String>> addressEntryIt = addressMap.entrySet().iterator();
        Iterator<Map.Entry<MeshNetService.Iface, String>> meshNetServicesEntryIt = meshNetServiceMap.entrySet().iterator();

        while (addressEntryIt.hasNext()) {
            Map.Entry<String, String> addressEntry = addressEntryIt.next();
            if (addressEntry.getValue().equals(node)) {
                addressEntryIt.remove();
            }
        }
        while (meshNetServicesEntryIt.hasNext()) {
            Map.Entry<MeshNetService.Iface, String> meshNetServicesEntry = meshNetServicesEntryIt.next();
            if (meshNetServicesEntry.getValue().equals(node)) {
                meshNetServicesEntryIt.remove();
            }
        }
    }

    @Override
    public void update(String node, MeshNetService.Iface meshNetService, String address) {
        unregister(node);
        register(node, meshNetService, address);
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

    @Override
    public MeshNetService.Iface getMeshNetService(String node) {
        Iterator<Map.Entry<MeshNetService.Iface, String>> meshNetServicesEntryIt = meshNetServiceMap.entrySet().iterator();
        while (meshNetServicesEntryIt.hasNext()) {
            Map.Entry<MeshNetService.Iface, String> meshNetServicesEntry = meshNetServicesEntryIt.next();
            if (meshNetServicesEntry.getValue().equals(node)) {
                return meshNetServicesEntry.getKey();
            }
        }
        return null;
    }

    @Override
    public List<MeshNetService.Iface> getMeshNetServiceList() {
        return new ArrayList<>(meshNetServiceMap.keySet());
    }
}
