package group.bison.automation.core.event;

public class AtLeastOnceEvent<T> extends AbstractEvent<T>{

    public AtLeastOnceEvent(String parentId, String taskId) {
        super(parentId, taskId);
    }
}
