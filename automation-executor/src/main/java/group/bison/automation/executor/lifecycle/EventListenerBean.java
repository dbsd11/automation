package group.bison.automation.executor.lifecycle;

import group.bison.automation.common.dto.EventDto;

/**
 * Created by BSONG on 2018/6/13.
 */
public interface EventListenerBean {

    public void onEvent(EventDto eventDto);

    public void killEvent(EventDto eventDto);
}
