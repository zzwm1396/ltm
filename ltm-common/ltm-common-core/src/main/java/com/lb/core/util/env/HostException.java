package com.lb.core.util.env;

import java.io.IOException;

/**
 * 网络主机异常
 * Created by libo on 2017/4/21.
 */
public final class HostException extends RuntimeException{

    private static final long serialVersionUID = -7539113886574948699L;

    public HostException(final IOException cause){
        super(cause);
    }
}
