package group.bison.automation.core.dto;

public class ExactlyOnceEventDto<T> extends AbstractEventDto<T> {

    public ExactlyOnceEventDto(String parentId, String taskId, Boolean orderly) {
        super(parentId, taskId, orderly);
    }
}
