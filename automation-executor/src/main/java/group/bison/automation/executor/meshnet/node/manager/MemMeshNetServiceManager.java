package group.bison.automation.executor.meshnet.node.manager;

import group.bison.automation.executor.meshnet.common.MeshNetServiceManager;
import group.bison.thrift.automation.meshnet.MeshNetService;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by BSONG on 2018/6/10.
 */
public class MemMeshNetServiceManager implements MeshNetServiceManager {

    private Map<String, MeshNetService.Iface> meshNetServiceMap = new ConcurrentHashMap<>();

    @Override
    public boolean contains(String address) {
        return meshNetServiceMap.keySet().contains(address);
    }

    @Override
    public void add(String address) {
        if (contains(address)) {
            return;
        }
        MeshNetService.Iface meshNetService = null;
        meshNetServiceMap.put(address, meshNetService);
    }

    @Override
    public void remove(String address) {
        meshNetServiceMap.remove(address);
    }

    @Override
    public MeshNetService.Iface get(String address) {
        if (!meshNetServiceMap.containsKey(address)) {
            MeshNetService.Iface meshNetService = null;
            return meshNetService;
        }

        return meshNetServiceMap.get(address);
    }

    @Override
    public Set<MeshNetService.Iface> getAll() {
        return new HashSet<>(meshNetServiceMap.values());
    }
}
