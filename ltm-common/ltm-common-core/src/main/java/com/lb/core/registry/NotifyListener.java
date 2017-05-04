package com.lb.core.registry;

import com.lb.core.node.Node;

import java.util.List;

/**
 * 监听者
 * Created by libo on 2017/5/4.
 */
public interface NotifyListener {
    /**
     * 发送通知
     * @param event 通知事件
     * @param nodes 节点集合
     */
    void notify(NotifyEvent event, List<Node> nodes);
}
