package com.lb.core.exception;

/**
 * RemotingCommand字段检查异常
 * Created by libo on 2017/5/4.
 */
public class RemotingCommandFieldCheckException extends Exception {

    private static final long serialVersionUID = 3856507891980067055L;

    public RemotingCommandFieldCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemotingCommandFieldCheckException(String message) {

        super(message);
    }
}
