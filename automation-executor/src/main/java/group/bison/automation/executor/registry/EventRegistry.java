package group.bison.automation.executor.registry;

/**
 * Created by BSONG on 2018/6/3.
 */
public interface EventRegistry {

    public void registry(Object event);

    public void deRegistry(Object event);
}
