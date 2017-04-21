package com.lb.core.executor.handler;

/**
 * 作业异常处理器
 *
 * Created by libo on 2017/4/21.
 */
public interface JobExceptionHandler {

    /**
     * 处理作业异常.
     *
     * @param jobName 作业名称
     * @param cause 异常原因
     */
    void handleException(String jobName, Throwable cause);
}
