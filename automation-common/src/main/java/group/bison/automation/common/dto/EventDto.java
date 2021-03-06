package group.bison.automation.common.dto;

import java.util.UUID;

public abstract class EventDto<T> {
    private String id;
    private String name;
    private Long timestamp;

    private String parentId;
    private String taskId;
    private Boolean orderly;
    private T source;

    public EventDto(String name, String parentId, String taskId, Boolean orderly) {
        this.id = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
        this.name = name;
        this.parentId = parentId;
        this.taskId = taskId;
        this.orderly = orderly;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getTimestamp() {
        return timestamp;
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

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }
}
