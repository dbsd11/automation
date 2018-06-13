package group.bison.automation.common.dto;

public class AtLeastOnceEventDto<T> extends EventDto<T> {

    public AtLeastOnceEventDto(String name, String parentId, String taskId, Boolean orderly) {
        super(name, parentId, taskId, orderly);
    }
}
