package group.bison.automation.executor.event;

import group.bison.automation.common.dto.EventDto;
import group.bison.automation.common.exception.BusinessException;
import group.bison.automation.common.task.Task;
import group.bison.automation.executor.job.JobFactory;
import group.bison.automation.executor.lifecycle.EventListenerBean;
import group.bison.automation.executor.lifecycle.JobBean;
import group.bison.automation.executor.plugin.PluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Vector;

/**
 * Created by BSONG on 2018/6/13.
 */
public class ExecutorListener implements EventListenerBean {
    private static final Logger LOG = LoggerFactory.getLogger(ExecutorListener.class);

    @Override
    public void onEvent(EventDto eventDto) {
        try {
            String taskId = eventDto.getTaskId();
            Task task = null;
            String plugin = task.getPlugin();
            JobBean jobBean = null;
            PluginManager.PluginClassLoader pluginClassLoader = PluginManager.load(plugin);
            Field field = ClassLoader.class.getDeclaredField("classes");
            field.setAccessible(true);
            Vector<Class> classes = (Vector) field.get(pluginClassLoader);
            for (Class cls : classes) {
                if (cls.getAnnotatedInterfaces()[0].getType().getTypeName().contains("JobFactory")) {
                    jobBean = ((JobFactory) cls.newInstance()).create(eventDto);
                    break;
                }
            }
            if (jobBean == null) {
                throw new BusinessException("找不到JobFactory实现, plugin:{}" + task.getPlugin());
            }

//        jobBean.onCreate();
//
//        jobBean.onLoad();
//
//        jobBean.onHandle();
//
//        jobBean.onFinish();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void killEvent(EventDto eventDto) {
        try {
            String taskId = eventDto.getTaskId();
            Task task = null;
            String plugin = task.getPlugin();
            JobBean jobBean = null;

            PluginManager.PluginClassLoader pluginClassLoader = PluginManager.load(plugin);
            Field field = ClassLoader.class.getDeclaredField("classes");
            field.setAccessible(true);
            Vector<Class> classes = (Vector) field.get(pluginClassLoader);
            for (Class cls : classes) {
                if (cls.getAnnotatedInterfaces()[0].getType().getTypeName().contains("JobFactory")) {
                    jobBean = ((JobFactory) cls.newInstance()).create(eventDto);
                    break;
                }
            }
            if (jobBean == null) {
                throw new BusinessException("找不到JobFactory实现, plugin:{}" + task.getPlugin());
            }

//        jobBean.onKill();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
