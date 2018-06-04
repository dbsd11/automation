package group.bison.automation.common.dto;

public class ExactlyOnceEventDto<T> extends AbstractEventDto<T> {

    public ExactlyOnceEventDto(String name, String parentId, String taskId, Boolean orderly) {
        super(name, parentId, taskId, orderly);
    }
}
