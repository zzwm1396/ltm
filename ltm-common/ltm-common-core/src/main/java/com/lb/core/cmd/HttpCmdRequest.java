package com.lb.core.cmd;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by libo on 2017/5/2.
 */
@Setter
@Getter
public class HttpCmdRequest {
    private String command;

    private String nodeIdentity;

    private Map<String, String> params;

    public String getPram(String key){
        if (params != null)
            return params.get(key);
        return null;
    }

    public String getParam(String key, String defaultValue){
        if (params != null){
            String value = params.get(key);
            if (StringUtils.isEmpty(value)){
                return defaultValue;
            }
            return value;
        }
        return null;
    }

    public void addParam(String key, String value){
        if (params == null){
            params = new HashMap<>();
        }
        params.put(key,value);
    }

}
