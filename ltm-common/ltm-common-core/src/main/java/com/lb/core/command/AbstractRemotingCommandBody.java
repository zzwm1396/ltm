package com.lb.core.command;

import com.lb.core.cluster.NodeType;
import com.lb.core.exception.RemotingCommandFieldCheckException;
import com.lb.core.remoting.RemotingCommandBody;
import com.lb.core.support.SystemClock;
import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象的传输信息
 * Created by libo on 2017/5/4.
 */
@Getter
@Setter
public class AbstractRemotingCommandBody implements RemotingCommandBody {

    private static final long serialVersionUID = 8516899197480084243L;

    /**
     * 节点组
     */
    @NotNull
    private String nodeGroup;

    /**
     * 节点类型
     */
    @NotNull
    private NodeType nodeType;

    /**
     * 当前节点唯一标识
     */
    @NotNull
    private String identity;

    private Long timestamp = SystemClock.now();

    /**
     * 额外参数
     */
    private Map<String, Object> extParams;

    public void putExtParam(String key, Object obj) {
        if (this.extParams == null) {
            this.extParams = new HashMap<String, Object>();
        }
        this.extParams.put(key, obj);
    }

    @Override
    public void checkFields() throws RemotingCommandFieldCheckException {

    }
}
