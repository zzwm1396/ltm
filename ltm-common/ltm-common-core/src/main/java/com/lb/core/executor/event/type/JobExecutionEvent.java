package com.lb.core.executor.event.type;

import com.lb.core.executor.event.JobEvent;

import java.util.UUID;

/**
 * 作业执行事件
 * Created by libo on 2017/4/21.
 */
public class JobExecutionEvent implements JobEvent{
    private String id = UUID.randomUUID().toString();


    @Override
    public String getJobname() {
        return null;
    }
}
