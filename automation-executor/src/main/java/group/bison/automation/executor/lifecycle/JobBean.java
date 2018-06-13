package group.bison.automation.executor.lifecycle;

import group.bison.automation.common.exception.BusinessException;

/**
 * Created by BSONG on 2018/6/3.
 */
public interface JobBean {

    public void onCreate() throws BusinessException;

    public void onLoad() throws BusinessException;

    public void onHandle() throws BusinessException;

    public void onFinish() throws BusinessException;

    public void onKill() throws BusinessException;
}
