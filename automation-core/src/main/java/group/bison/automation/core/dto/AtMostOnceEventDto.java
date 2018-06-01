package group.bison.automation.core.dto;

public class AtMostOnceEventDto<T> extends AbstractEventDto<T>{

    public AtMostOnceEventDto(String parentId, String taskId, Boolean orderly) {
        super(parentId, taskId, orderly);
    }
}
