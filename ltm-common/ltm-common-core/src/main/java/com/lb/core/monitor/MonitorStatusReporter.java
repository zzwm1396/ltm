package com.lb.core.monitor;

/**
 * 监控中心接口
 * Created by libo on 2017/5/4.
 */
public interface MonitorStatusReporter {

    /**
     * 启动
     */
    void start();

    /**
     * 停止
     */
    void stop();
}
