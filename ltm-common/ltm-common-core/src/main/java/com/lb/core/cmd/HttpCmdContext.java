package com.lb.core.cmd;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * httpCmd 上下文
 * Created by libo on 2017/5/2.
 */
public class HttpCmdContext {

    private ReentrantLock lock = new ReentrantLock();

    private final Map<String/* 节点标识*/, Map<String/*cmd标识*/, HttpCmdProc>>
            NODE_PROCESSOR_MAP = new HashMap<>();

    public void addCmdProcessor(HttpCmdProc proc) {
        if (proc == null)
            throw new IllegalArgumentException("proc can not be null");

        String identity = proc.nodeIdentity();

        if (StringUtils.isEmpty(identity))
            throw new IllegalArgumentException("nodeIdentity can't empty");

        String command = proc.getCommand();
        if (StringUtils.isEmpty(command))
            throw new IllegalArgumentException("command can't empty");

        Map<String, HttpCmdProc> cmdProcessorMap = NODE_PROCESSOR_MAP.get(identity);

        if (cmdProcessorMap == null) {
            try {
                lock.lock();

                cmdProcessorMap = NODE_PROCESSOR_MAP.get(identity);
                if (cmdProcessorMap == null) {
                    cmdProcessorMap = new ConcurrentHashMap<String, HttpCmdProc>();
                    NODE_PROCESSOR_MAP.put(identity, cmdProcessorMap);
                }
            } finally {
                lock.unlock();
            }
        }
        cmdProcessorMap.put(identity, proc);
    }

    public HttpCmdProc getCmdProcessor(String nodeIdentity, String command) {
        if (StringUtils.isEmpty(nodeIdentity))
            throw new IllegalArgumentException("nodeIdentity can't be empty");

        Map<String, HttpCmdProc> cmdProcessorMap = NODE_PROCESSOR_MAP.get(nodeIdentity);
        if (cmdProcessorMap == null)
            return null;

        return cmdProcessorMap.get(command);
    }
}























