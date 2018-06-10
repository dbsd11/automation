package group.bison.automation.executor.meshnet.common;

import java.util.Set;

/**
 * Created by BSONG on 2018/6/7.
 */
public interface RouteManager {

    boolean contains(String node);

    void register(String node, String address);

    void unregister(String node);

    void update(String node, String address);

    Set<String> peers(String node);
}
