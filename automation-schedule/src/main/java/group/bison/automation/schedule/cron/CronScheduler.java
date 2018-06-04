package group.bison.automation.schedule.cron;

import com.lmax.disruptor.dsl.Disruptor;
import group.bison.automation.core.dto.AbstractEventDto;

public class CronScheduler {
    private Disruptor<AbstractEventDto> disruptor;

    public Disruptor<AbstractEventDto> getDisruptor() {
        return disruptor;
    }

    public void setDisruptor(Disruptor<AbstractEventDto> disruptor) {
        this.disruptor = disruptor;
    }
}
