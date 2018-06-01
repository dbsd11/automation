package group.bison.automation.core.dto;

public class AtLeastOnceEventDto<T> extends AbstractEventDto<T>{

    public AtLeastOnceEventDto(String parentId, String taskId, Boolean orderly) {
        super(parentId, taskId, orderly);
    }
}
