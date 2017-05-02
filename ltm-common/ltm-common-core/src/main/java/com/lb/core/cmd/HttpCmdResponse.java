package com.lb.core.cmd;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by libo on 2017/5/2.
 */
@Getter
@Setter
public class HttpCmdResponse implements Serializable{

    private static final long serialVersionUID = 1412520597594181789L;

    private boolean success = false;

    private String msg;

    private String code;

    private String obj;

    public static HttpCmdResponse newResponse(boolean success, String msg){
        HttpCmdResponse response = new HttpCmdResponse();
        response.setSuccess(success);
        response.setMsg(msg);
        return response;
    }

}
