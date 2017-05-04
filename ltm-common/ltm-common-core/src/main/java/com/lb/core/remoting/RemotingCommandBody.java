package com.lb.core.remoting;

import com.lb.core.exception.RemotingCommandFieldCheckException;

import java.io.Serializable;

/**
 * RemotingCommand中自定义字段反射对象的公共接口
 * Created by libo on 2017/5/4.
 */
public interface RemotingCommandBody extends Serializable{
    void checkFields() throws RemotingCommandFieldCheckException;
}
