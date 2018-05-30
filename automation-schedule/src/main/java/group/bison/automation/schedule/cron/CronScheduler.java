package group.bison.automation.schedule.cron;

import com.lmax.disruptor.dsl.Disruptor;
import group.bison.automation.core.event.CronEvent;

public class CronScheduler {
    private Disruptor<CronEvent> disruptor;

    public Disruptor<CronEvent> getDisruptor() {
        return disruptor;
    }

    public void setDisruptor(Disruptor<CronEvent> disruptor) {
        this.disruptor = disruptor;
    }
}
