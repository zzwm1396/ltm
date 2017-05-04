package com.lb.core.listener;

import com.lb.core.node.Node;

/**
 * Master节点变化监听器
 * Created by libo on 2017/5/4.
 */
public interface MasterChangeListener {

    /**
     * 节点变化监听
     * @param master master节点
     * @param isMaster 当前节点是否是master节点
     */
    void change(Node master, boolean isMaster);
}
