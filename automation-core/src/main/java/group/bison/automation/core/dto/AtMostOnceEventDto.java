package group.bison.automation.core.dto;

public class AtMostOnceEventDto<T> extends AbstractEventDto<T>{

    public AtMostOnceEventDto(String name, String parentId, String taskId, Boolean orderly) {
        super(name, parentId, taskId, orderly);
    }
}
