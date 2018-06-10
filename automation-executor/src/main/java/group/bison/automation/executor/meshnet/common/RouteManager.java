package group.bison.automation.executor.meshnet.common;

import group.bison.thrift.automation.meshnet.MeshNetService;

import java.util.List;
import java.util.Set;

/**
 * Created by BSONG on 2018/6/7.
 */
public interface RouteManager {

    boolean contains(String node);

    void register(String node, MeshNetService.Iface meshNetService, String address);

    void unregister(String node);

    void update(String node, MeshNetService.Iface meshNetService, String address);

    Set<String> peers(String node);

    MeshNetService.Iface getMeshNetService(String node);

    List<MeshNetService.Iface> getMeshNetServiceList();
}
