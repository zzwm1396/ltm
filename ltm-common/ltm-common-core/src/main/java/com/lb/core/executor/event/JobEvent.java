package com.lb.core.executor.event;

/**
 * 作业事件接口
 * Created by libo on 2017/4/21.
 */
public interface JobEvent {

    /**
     * 获取作业名称
     * @return 作业名称
     */
    String getJobname();
}
