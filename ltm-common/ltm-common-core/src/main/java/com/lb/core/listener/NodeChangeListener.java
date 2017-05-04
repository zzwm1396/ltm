package com.lb.core.listener;

import com.lb.core.node.Node;

import java.util.List;

/**
 * 节点变化监听接口
 * Created by libo on 2017/5/4.
 */
public interface NodeChangeListener {

    /**
     * 添加节点
     * @param nodes 节点列表
     */
    void addNodes(List<Node> nodes);

    /**
     * 移除节点
     * @param nodes 节点列表
     */
    void removeNodes(List<Node> nodes);
}
