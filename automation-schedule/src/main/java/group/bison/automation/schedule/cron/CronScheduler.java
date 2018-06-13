package group.bison.automation.schedule.cron;

import com.lmax.disruptor.dsl.Disruptor;
import group.bison.automation.common.dto.EventDto;

public class CronScheduler {
    private Disruptor<EventDto> disruptor;

    public Disruptor<EventDto> getDisruptor() {
        return disruptor;
    }

    public void setDisruptor(Disruptor<EventDto> disruptor) {
        this.disruptor = disruptor;
    }
}
