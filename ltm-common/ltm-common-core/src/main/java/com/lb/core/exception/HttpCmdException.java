package com.lb.core.exception;

/**
 * Created by libo on 2017/5/2.
 */
public class HttpCmdException extends RuntimeException{

    private static final long serialVersionUID = -746990980163539052L;

    public HttpCmdException(){
        super();
    }

    public HttpCmdException(String message){
        super(message);
    }

    public HttpCmdException(String message, Throwable throwable){
        super(message,throwable);
    }

    public HttpCmdException(Throwable throwable){
        super(throwable);
    }
}
