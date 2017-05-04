package com.lb.core.exception;

/**
 * Created by libo on 2017/5/4.
 */
public class LtmRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 2570178070868054124L;

    public LtmRuntimeException(String message) {
        super(message);
    }

    public LtmRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public LtmRuntimeException(Throwable cause) {
        super(cause);
    }
}
