package com.lb.core.cluster;

import org.apache.commons.lang3.StringUtils;

/**
 * 节点类型
 * Created by libo on 2017/5/4.
 */
public enum NodeType {
    // 主节点
    MASTER,
    // 从节点
    FOLLOWER,
    // 任务执行节点
    TASKER;

    public static NodeType convert(String value){
        if (StringUtils.isEmpty(value))
            return null;
        return NodeType.valueOf(value);
    }
}
