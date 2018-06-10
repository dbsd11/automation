package group.bison.automation.executor.meshnet.common;

import group.bison.thrift.automation.meshnet.MeshNetService;

import java.util.Set;

/**
 * Created by BSONG on 2018/6/10.
 */
public interface MeshNetServiceManager {

    boolean contains(String address);

    void add(String address);

    void remove(String address);

    public MeshNetService.Iface get(String address);

    public Set<MeshNetService.Iface> getAll();
}
