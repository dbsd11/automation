package group.bison.automation.executor.lifesycle;

/**
 * Created by BSONG on 2018/6/3.
 */
public interface EventBean {

    public void onCreate();

    public void onLoad();

    public void onHandle();

    public void onFinish();

    public void onKill();
}
