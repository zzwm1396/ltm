package com.lb.core.executor.handler.impl;

import com.lb.core.executor.handler.JobExceptionHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认作业异常处理器
 * Created by libo on 2017/4/21.
 */
@Slf4j
public class DefaultJobExceptionHandler implements JobExceptionHandler {
    @Override
    public void handleException(String jobName, Throwable cause) {
        log.error(String.format("Job '%s' exception occur in job processing", jobName), cause);
    }
}
