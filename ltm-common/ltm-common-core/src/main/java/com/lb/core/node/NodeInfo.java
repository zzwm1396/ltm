package com.lb.core.node;

import com.lb.core.utils.NodeRegistryUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * 节点基础信息
 * Created by libo on 2017/4/28.
 */
@Getter
@Setter
public class NodeInfo {

    /**
     * 是否是master节点
     */
    private boolean master = false;

    /**
     * 是否是备用节点
     */
    private boolean follow = false;

    /**
     * 节点是否可用
     */
    private boolean available = true;

    /**
     * 集群名称
     */
    private String clusterName;

    /**
     * 节点IP
     */
    private String ip;

    /**
     * 节点端口
     */
    private Integer port;

    /**
     * 主机名称
     */
    private String hostName;

    /**
     * 节点创建时间
     */
    private Long createTime;

    /**
     * 节点工作线程数
     */
    private Integer threads;

    /**
     * 节点唯一标识
     */
    private String identity;

    /**
     * 节点字符串基础信息
     */
    private String fullString;

    @Override
    public int hashCode() {
        return identity != null ? identity.hashCode(): 0;
    }

    /**
     * 获取节点字符串信息
     * @return 节点字符串
     */
    public String toFullString(){
        if (fullString == null)
            fullString = NodeRegistryUtils.getFullPath(this);
        return fullString;
    }
}
