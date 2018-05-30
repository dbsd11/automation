package group.bison.automation.core.event;

public class ExactlyOnceEvent<T> extends AbstractEvent<T>{

    public ExactlyOnceEvent(String parentId, String taskId) {
        super(parentId, taskId);
    }
}
