package group.bison.automation.core.dto;

import java.util.UUID;

public abstract class AbstractEventDto<T> {
    private String id;
    private Long timestamp;

    private String parentId;
    private String taskId;
    private Boolean orderly;
    private T source;

    public AbstractEventDto(String parentId, String taskId, Boolean orderly) {
        this.id = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
        this.parentId = parentId;
        this.taskId = taskId;
        this.orderly = orderly;
    }

    public String getId() {
        return id;
    }

    public String getParentId() {
        return parentId;
    }

    public String getTaskId() {
        return taskId;
    }

    public Boolean getOrderly() {
        return orderly;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }
}
