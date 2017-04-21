package com.lb.core.executor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * 分片上下文集合
 * Created by libo on 2017/4/21.
 */
@RequiredArgsConstructor
@Getter
@ToString
public class ShardingContexts implements Serializable{

    private static final long serialVersionUID = 6170818239995733086L;

    /**
     * 作业任务ID
     */
    private final String taskId;

    /**
     * 作业名称
     */
    private final String jobName;

    /**
     * 分片总数
     */
    private final int shardingTotalCount;

    /**
     * 作业自定义参数
     * 可配置多个相同的作业，但是用不同的参数作为不同的调度实例
     */
    private final String jobParameter;

    /**
     * 分配与本作业实例的分片项和参数的map
     */
    private final Map<Integer, String> shardingItemParameters;

    /**
     * 作业事件采样统计数
     */
    private int jobEventSamplingCount;

    /**
     * 当前作业事件采样统计数
     */
    @Setter
    private int currentJobEventSamplingCount;

    /**
     * 是否允许发送作业事件
     */
    @Setter
    private boolean allowSendJobEvent = true;

    public ShardingContexts(final String taskId, final String jobName, final int shardingTotalCount,
                            final String jobParameter, final Map<Integer, String> shardingItemParameters,
                            final int jobEventSamplingCount){
        this.taskId = taskId;
        this.jobName = jobName;
        this.shardingTotalCount = shardingTotalCount;
        this.jobParameter = jobParameter;
        this.shardingItemParameters = shardingItemParameters;
        this.jobEventSamplingCount = jobEventSamplingCount;
    }

}
