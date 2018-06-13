package group.bison.automation.common.dto;

public class ExactlyOnceEventDto<T> extends EventDto<T> {

    public ExactlyOnceEventDto(String name, String parentId, String taskId, Boolean orderly) {
        super(name, parentId, taskId, orderly);
    }
}
