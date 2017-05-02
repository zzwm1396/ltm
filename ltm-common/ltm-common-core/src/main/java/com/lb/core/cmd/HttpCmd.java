package com.lb.core.cmd;

import com.google.gson.Gson;
import com.lb.core.exception.HttpCmdException;
import com.lb.core.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by libo on 2017/5/2.
 */
public class HttpCmd<Resp extends HttpCmdResponse> extends HttpCmdRequest {

    final public Resp doGet(String url) throws IOException{
        Resp resp = null;
        String result = null;

        try{
            result = WebUtils.doGet(url, null);
        }catch (IOException e1){
            try {
                resp = (Resp) getResponseClass().newInstance();
            }catch (InstantiationException e){
                throw new HttpCmdException(e);
            }catch (IllegalAccessException e){
                throw  new HttpCmdException(e);
            }
        }

        if (StringUtils.isNotEmpty(result)){
            resp = (Resp) new Gson().fromJson(result, getResponseClass());
        }
        return resp;
    }

    public Resp doPost(String url, Map<String, String> params){
        Resp resp = null;
        String result = null;

        try{
            result = WebUtils.doPost(url, params, 3000, 30000);
        }catch (IOException e1){
            try{
                resp = (Resp) getResponseClass().newInstance();
                resp.setSuccess(false);
                resp.setMsg("POST ERROR: url=" + url + ", errorMsg = " + e1.getMessage());
            } catch (InstantiationException e){
                throw new HttpCmdException(e);
            } catch (IllegalAccessException e){
                throw new HttpCmdException(e);
            }
        }
        return resp;
    }

    protected Class<? extends HttpCmdResponse> getResponseClass() {
        return HttpCmdResponse.class;
    }
}
