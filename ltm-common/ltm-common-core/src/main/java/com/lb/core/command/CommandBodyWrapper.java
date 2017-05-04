package com.lb.core.command;

import com.lb.core.AppContext;
import com.lb.core.common.Config;

/**
 * 节点通信包装器
 * Created by libo on 2017/5/4.
 */
public class CommandBodyWrapper {

    private Config config;

    public CommandBodyWrapper(Config config){
        this.config = config;
    }

    public <T extends AbstractRemotingCommandBody> T wrapper(T commandBody){
        commandBody.setNodeGroup(config.getNodeGroup());
        commandBody.setNodeType(config.getNodeType());
        commandBody.setIdentity(config.getIdentity());
        return commandBody;
    }

    public static <T extends AbstractRemotingCommandBody> T wrapper(AppContext appContext, T commandBody){
        return appContext.getCommandBodyWrapper().wrapper(commandBody);
    }
}
