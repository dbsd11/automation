package group.bison.automation.core.dto;

public class AtLeastOnceEventDto<T> extends AbstractEventDto<T>{

    public AtLeastOnceEventDto(String name, String parentId, String taskId, Boolean orderly) {
        super(name, parentId, taskId, orderly);
    }
}
