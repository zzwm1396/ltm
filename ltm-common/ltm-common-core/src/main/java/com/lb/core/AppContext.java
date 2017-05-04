package com.lb.core;

import com.lb.core.cluster.MasterElector;
import com.lb.core.cluster.SubscribedNodeManager;
import com.lb.core.cmd.HttpCmdServer;
import com.lb.core.command.CommandBodyWrapper;
import com.lb.core.common.Config;
import com.lb.core.ec.EventCenter;
import com.lb.core.monitor.MonitorStatusReporter;
import com.lb.core.registry.RegistryStatMonitor;
import lombok.Getter;
import lombok.Setter;

/**
 * 程序上下文配置信息
 * Created by libo on 2017/4/28.
 */
@Setter
@Getter
public class AppContext {

    /**
     * 事件中心
     */
    private EventCenter eventCenter;

    /**
     * 节点配置信息
     */
    private Config config;

    /**
     * 节点管理
     */
    private SubscribedNodeManager subscribedNodeManager;

    /**
     * master选举者
     */
    private MasterElector masterElector;

    /**
     * 节点通信包装器
     */
    private CommandBodyWrapper commandBodyWrapper;

    /**
     * 监控中心
     */
    private MonitorStatusReporter monitorStatusReporter;

    /**
     * 注册中心
     */
    private RegistryStatMonitor registryStatMonitor;

    /**
     * 命令中心
     */
    private HttpCmdServer httpCmdServer;
}
