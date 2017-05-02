package com.lb.core.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by libo on 2017/5/2.
 */
public class NamedThreadFactory  implements ThreadFactory{

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
        this.prefix = prefix;
        this.daemon = daemon;
        SecurityManager securityManager = System.getSecurityManager();
        group = securityManager == null ? Thread.currentThread().getThreadGroup() : securityManager.getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = prefix + threadNum.getAndIncrement();
        Thread ret = new Thread(group, r, name, 0);
        ret.setDaemon(daemon);
        return ret;
    }

    public ThreadGroup getThreadGroup(){
        return group;
    }
}






















