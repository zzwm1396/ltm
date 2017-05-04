package com.lb.core.constant;

/**
 * Created by libo on 2017/5/4.
 */
public enum EcTopic {
    /**
     * 工作线程变化
     */
    WORK_THREAD_CHANGE("WORK_THREAD_CHANGE"),

    /**
     * 节点启动
     */
    NODE_ENABLE("NODE_ENABLE"),

    /**
     * 节点禁用
     */
    NODE_DISABLE("NODE_DISABLE"),

    /**
     * master节点不可用
     */
    MASTER_DISABLE("MASTER_DISABLE"),

    /**
     * master节点改变
     */
    MASTER_CHANGED("MASTER_CHANGED"),

    /**
     * 添加节点
     */
    NODE_ADD("NODE_ADD"),

    /**
     * 移除节点
     */
    NODE_REMOVE("NODE_REMOVE"),

    /**
     * 注册中心可用
     */
    REGISTRY_AVAILABLE("REGISTRY_AVAILABLE"),

    /**
     * 注册中心不可用
     */
    REGISTRY_UN_AVAILABLE("REGISTRY_UN_AVAILABLE"),

    /**
     * 停止节点
     */
    NODE_SHUT_DOWN("NODE_SHUT_DOWN")

    ;

    private String value;

    EcTopic(String value) {
        this.value = value;
    }
}
