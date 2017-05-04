package com.lb.core.common;

import com.lb.core.cluster.NodeType;
import com.lb.core.constant.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 节点配置信息
 * Created by libo on 2017/4/28.
 */
@Slf4j
@Getter
@Setter
public class Config implements Serializable{

    private static final long serialVersionUID = 3791435407946297966L;

    /**
     * 节点是否可用
     */
    private boolean available = true;

    /**
     * 应用节点组
     */
    private String nodeGroup;

    /**
     * 节点唯一标识
     */
    private String identity;

    /**
     * 工作线程数
     */
    private int workThreads;

    /**
     * 注册中心 地址
     */
    private String registryAddress;

    /**
     * 远程连接超时时间
     */
    private int invokeTimeoutMillis;

    /**
     * 监听接口
     */
    private int listenPort;

    /**
     * 节点ip
     */
    private String ip;

    /**
     * 缓存信息存储路径
     */
    private String dataPath;

    /**
     * 集群名字
     */
    private String clusterName;

    /**
     * 节点类型
     */
    private NodeType nodeType;

    /**
     * 参数信息
     */
    private final Map<String, String> parameters = new HashMap<>();


    // 内部使用，不参与序列化
    private volatile transient Map<String, Number> numbers;

    public String getParameter(String key) {
        return parameters.get(key);
    }

    public boolean getParameter(String key, boolean defaultValue) {
        String value = getParameter(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }

    public int getParameter(String key, int defaultValue) {
        Number n = getNumbers().get(key);
        if (n != null) {
            return n.intValue();
        }
        String value = getParameter(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        }
        int i = Integer.parseInt(value);
        getNumbers().put(key, i);
        return i;
    }

    public String[] getParameter(String key, String[] defaultValue) {
        String value = getParameter(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        }

        return Constants.COMMA_SPLIT_PATTERN.split(value);
    }

    public double getParameter(String key, double defaultValue) {
        Number n = getNumbers().get(key);
        if (n != null) {
            return n.doubleValue();
        }
        String value = getParameter(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        }
        double d = Double.parseDouble(value);
        getNumbers().put(key, d);
        return d;
    }

    public float getParameter(String key, float defaultValue) {
        Number n = getNumbers().get(key);
        if (n != null) {
            return n.floatValue();
        }
        String value = getParameter(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        }
        float f = Float.parseFloat(value);
        getNumbers().put(key, f);
        return f;
    }

    public long getParameter(String key, long defaultValue) {
        Number n = getNumbers().get(key);
        if (n != null) {
            return n.longValue();
        }
        String value = getParameter(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        }
        long l = Long.parseLong(value);
        getNumbers().put(key, l);
        return l;
    }

    public short getParameter(String key, short defaultValue) {
        Number n = getNumbers().get(key);
        if (n != null) {
            return n.shortValue();
        }
        String value = getParameter(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        }
        short s = Short.parseShort(value);
        getNumbers().put(key, s);
        return s;
    }

    public byte getParameter(String key, byte defaultValue) {
        Number n = getNumbers().get(key);
        if (n != null) {
            return n.byteValue();
        }
        String value = getParameter(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        }
        byte b = Byte.parseByte(value);
        getNumbers().put(key, b);
        return b;
    }

}



























