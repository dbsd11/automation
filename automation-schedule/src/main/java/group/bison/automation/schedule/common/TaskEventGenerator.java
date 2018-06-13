package group.bison.automation.schedule.common;

import group.bison.automation.common.task.Task;

import java.util.stream.Stream;

/**
 * Created by BSONG on 2018/6/4.
 */
public interface TaskEventGenerator {

    public Stream generate(Task task);
}
