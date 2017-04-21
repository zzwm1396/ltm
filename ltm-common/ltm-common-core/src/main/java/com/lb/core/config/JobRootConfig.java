package com.lb.core.config;

/**
 * 作业配置根接口
 * Created by libo on 2017/4/21.
 */
public interface JobRootConfig {
    /**
     * 获取作业类型配置.
     *
     * @return 作业类型配置
     */
    JobTypeConfig getTypeConfig();
}
