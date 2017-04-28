package com.lb.core.node;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 抽象节点
 * Created by libo on 2017/4/28.
 */
@Slf4j
@Setter
@Getter
public class AbstractNode {

    /**
     * 启动节点
     */
    public final void start(){

    }

    /**
     * 停止节点
     */
    public final void stop(){

    }

    /**
     * 销毁节点
     */
    public final void destroy(){

    }
}
