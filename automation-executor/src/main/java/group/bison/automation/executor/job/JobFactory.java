package group.bison.automation.executor.job;

import group.bison.automation.common.dto.EventDto;
import group.bison.automation.executor.lifecycle.JobBean;

/**
 * Created by BSONG on 2018/6/13.
 */
public interface JobFactory {

    public JobBean create(EventDto eventDto);
}
