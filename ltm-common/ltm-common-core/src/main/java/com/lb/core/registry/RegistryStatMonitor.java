package com.lb.core.registry;

import com.lb.core.AppContext;
import com.lb.core.constant.EcTopic;
import com.lb.core.ec.EventInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 注册中心状态监控
 * Created by libo on 2017/5/4.
 */
@Slf4j
public class RegistryStatMonitor {

    private AppContext appContext;

    private AtomicBoolean available = new AtomicBoolean(false);

    public RegistryStatMonitor(AppContext appContext) {
        this.appContext = appContext;
    }

    public void setAvailable(boolean available) {
        this.available.set(available);

        if (log.isInfoEnabled())
            log.info("Registry {}", available ? "available" : "unavailable");

        appContext.getEventCenter().publishAsync(new EventInfo(available ? EcTopic.REGISTRY_AVAILABLE : EcTopic.REGISTRY_UN_AVAILABLE));
    }

    public boolean isAvailable() {
        return this.available.get();
    }


}
