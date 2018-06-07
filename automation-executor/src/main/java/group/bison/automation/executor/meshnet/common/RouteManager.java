package group.bison.automation.executor.meshnet.common;

import group.bison.thrift.automation.meshnet.MeshNetService;

import java.util.List;

/**
 * Created by BSONG on 2018/6/7.
 */
public interface RouteManager {

    boolean contains(String node);

    void register(String node, MeshNetService.Iface meshNetService);

    void deRegister(String node);

    MeshNetService.Iface getMeshNetService(String node);

    List<MeshNetService.Iface> getMeshNetServiceList();
}
