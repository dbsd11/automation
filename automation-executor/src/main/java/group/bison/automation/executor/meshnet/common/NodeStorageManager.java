package group.bison.automation.executor.meshnet.common;

import java.util.List;
import java.util.Set;

/**
 * Created by BSONG on 2018/6/11.
 */
public interface NodeStorageManager {

    boolean contains(String key);

    void add(String key);

    String store(String key, String value);

    String store(String key, String value, Long expireSeconds);

    String remove(String key);

    String retrieve(String key);

    List<String> retrieve(List<String> keys);

    Set<String> keys();
}
