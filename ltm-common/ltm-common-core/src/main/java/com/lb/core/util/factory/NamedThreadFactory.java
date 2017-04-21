package com.lb.core.util.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂类，创建带名称的线程
 * Created by libo on 2017/4/21.
 */
public class NamedThreadFactory implements ThreadFactory{

    private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

    private final AtomicInteger threadNum = new AtomicInteger(1);

    private final String prefix;

    private final boolean daemon;

    private final ThreadGroup group;

    public NamedThreadFactory(){
        this("pool-" + POOL_SEQ.getAndIncrement(), false);
    }

    public NamedThreadFactory(String prefix){
        this(prefix, false);
    }

    public NamedThreadFactory(String prefix, boolean daemon){
        this.prefix = prefix + "-thread-";
        this.daemon = daemon;
        SecurityManager s = System.getSecurityManager();
        group = s == null ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = prefix + threadNum.getAndIncrement();
        Thread ret = new Thread(group, r, name, 0);
        ret.setDaemon(daemon);
        return ret;
    }
}
