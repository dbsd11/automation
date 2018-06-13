package group.bison.automation.common.dto;

public class AtMostOnceEventDto<T> extends EventDto<T> {

    public AtMostOnceEventDto(String name, String parentId, String taskId, Boolean orderly) {
        super(name, parentId, taskId, orderly);
    }
}
