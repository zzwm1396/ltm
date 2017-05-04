package com.lb.core.factory;

import com.lb.core.common.Config;
import com.lb.core.exception.LtmRuntimeException;
import com.lb.core.node.Node;
import com.lb.core.support.SystemClock;
import com.lb.core.utils.NetUtils;

/**
 * 节点工厂
 * Created by libo on 2017/5/4.
 */
public class NodeFactory {

    public static <T extends Node> T create(Class<T> clazz){
        try {
            return clazz.newInstance();
        }catch (Exception e){
            throw new LtmRuntimeException("Create Node error: clazz=" + clazz, e);
        }
    }

    public static void build(Node node, Config config){
        node.setCreateTime(SystemClock.now());
        node.setIp(config.getIp());
        node.setGroup(config.getNodeGroup());
        node.setThreads(config.getWorkThreads());
        node.setHostName(NetUtils.getLocalHostName());
        node.setIdentity(config.getIdentity());
        node.setClusterName(config.getClusterName());
    }
}
