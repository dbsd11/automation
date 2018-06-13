package group.bison.automation.executor;

import com.alibaba.fastjson.JSON;
import group.bison.automation.common.dto.EventDto;
import group.bison.automation.executor.event.ExecutorListener;
import group.bison.automation.executor.meshnet.node.NodeBootStrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Created by BSONG on 2018/6/13.
 */
public class ExecutorRunner {
    private static final Logger LOG = LoggerFactory.getLogger(ExecutorRunner.class);

    private static ExecutorListener executorListener = null;

    public static void main(String[] args) throws InterruptedException {
        NodeBootStrap.main(args);

        executorListener = new ExecutorListener();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                if (!scanner.hasNext()) {
                    continue;
                }

                String method = scanner.next();
                String event = scanner.next();
                if (method.equalsIgnoreCase("new")) {
                    EventDto eventDto = JSON.parseObject(event, EventDto.class);
                    executorListener.onEvent(eventDto);
                }

                if (method.equalsIgnoreCase("kill")) {
                    EventDto eventDto = JSON.parseObject(event, EventDto.class);
                    executorListener.killEvent(eventDto);
                }
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            } finally {
                synchronized (scanner) {
                    scanner.wait(100);
                }
            }
        }
    }
}
