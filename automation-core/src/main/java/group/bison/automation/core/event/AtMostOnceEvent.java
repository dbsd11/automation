package group.bison.automation.core.event;

public class AtMostOnceEvent<T> extends AbstractEvent<T>{

    public AtMostOnceEvent(String parentId, String taskId) {
        super(parentId, taskId);
    }
}
