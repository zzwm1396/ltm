package com.lb.core.utils;

import com.lb.core.node.NodeInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 节点注册工具类
 * Created by libo on 2017/4/28.
 */
@Slf4j
public class NodeRegistryUtils {

    /**
     * 获取集群根目录
     * @param clusterName 集群名称
     * @return 集群根目录
     */
    public static String getRootPath(String clusterName){
        return "/LTM/" + clusterName + "/NODES";
    }

    /**
     * 根据路径配置节点
     * @param fullPath
     * @return
     */
    public static NodeInfo parse(String fullPath){
        NodeInfo nodeInfo = new NodeInfo();
        String[] nodeDir = fullPath.split("/");

        return nodeInfo;
    }

    /**
     * 根据节点获取配置路径信息
     * @param nodeInfo
     * @return
     */
    public static String getFullPath(NodeInfo nodeInfo){
        StringBuffer path = new StringBuffer();

        return path.toString();
    }

    /**
     * 获取注册的具体地址
     * @param registryAddress 注册字符串 如:zookeeper://172.0.0.1:8182,172.0.0.2:8182,172.0.0.3:8182,
     * @return 去掉标题的具体地址 如：172.0.0.1:8182,172.0.0.2:8182,172.0.0.3:8182
     */
    public static String getRealRegistryAddress(String registryAddress){
        if (StringUtils.isEmpty(registryAddress))
            throw new IllegalArgumentException("registryAddress is null! ");
        if (registryAddress.startsWith("zookeeper://"))
            return registryAddress.replace("zookeeper://","");

        throw new IllegalArgumentException("Illegal registry protocol");
    }
}




















