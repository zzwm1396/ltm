package com.lb.core.executor.handler.impl;

import com.lb.core.executor.handler.ExecutorServiceHandler;
import com.lb.core.util.concurrent.ExecutorServiceObject;

import java.util.concurrent.ExecutorService;

/**
 * 默认线程池服务处理器
 * Created by libo on 2017/4/21.
 */
public final class DefaultExecutorServiceHandler implements ExecutorServiceHandler {

    @Override
    public ExecutorService createExecutorService(String jobName) {
        return new ExecutorServiceObject("inner-job-" + jobName,
                Runtime.getRuntime().availableProcessors()*2).createExecutorService();
    }
}
