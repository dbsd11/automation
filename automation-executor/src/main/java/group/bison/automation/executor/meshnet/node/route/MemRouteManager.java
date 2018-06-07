package group.bison.automation.executor.meshnet.node.route;

import group.bison.automation.executor.meshnet.common.RouteManager;
import group.bison.thrift.automation.meshnet.MeshNetService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by BSONG on 2018/6/7.
 */
public class MemRouteManager implements RouteManager {
    private Map<String, MeshNetService.Iface> map = new ConcurrentHashMap<>();

    @Override
    public boolean contains(String node) {
        return map.containsKey(node);
    }

    @Override
    public void register(String node, MeshNetService.Iface meshNetService) {
        map.put(node, meshNetService);
    }

    @Override
    public void deRegister(String node) {
        map.remove(node);
    }

    @Override
    public MeshNetService.Iface getMeshNetService(String node) {
        return map.get(node);
    }

    @Override
    public List<MeshNetService.Iface> getMeshNetServiceList() {
        return new ArrayList<>(map.values());
    }
}
