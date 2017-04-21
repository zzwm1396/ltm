package com.lb.core.util.env;

import com.lb.core.util.factory.NamedThreadFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 高并发场景下System.currentTimeMillis()的性能问题的优化
 * Created by libo on 2017/4/21.
 */
public final class SystemClock {

    private final long period;

    private final AtomicLong now;

    private SystemClock(long period){
        this.period = period;
        now = new AtomicLong(System.currentTimeMillis());
        scheduleClockUpdating();
    }

    private static class InstanceHolder{
        public static final SystemClock INSTANCE = new SystemClock(1);
    }

    private static SystemClock instance(){
        return InstanceHolder.INSTANCE;
    }

    private void scheduleClockUpdating(){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(
               new NamedThreadFactory("System Clock", true)
        );
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                now.set(System.currentTimeMillis());
            }
        }, period, period, TimeUnit.MILLISECONDS);
    }

    private long currentTimeMillis(){
        return now.get();
    }

    public static long now(){
        return instance().currentTimeMillis();
    }

    public static void main(String[] args) {
        System.out.println(SystemClock.now());
    }

}
