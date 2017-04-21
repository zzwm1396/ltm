package com.lb.core.config;

import com.lb.core.api.JobType;

/**
 * 作业类型配置
 * Created by libo on 2017/4/21.
 */
public interface JobTypeConfig {
    /**
     * 获取作业类型.
     *
     * @return 作业类型
     */
    JobType getJobType();

    /**
     * 获取作业实现类名称.
     *
     * @return 作业实现类名称
     */
    String getJobClass();

    /**
     * 获取作业核心配置.
     *
     * @return 作业核心配置
     */
    JobCoreConfig getCoreConfig();
}
