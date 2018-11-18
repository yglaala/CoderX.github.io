package com.bmsoft.canteensystem.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonUtils {

    public static Map<String,Object> json2Map(String jsonStr){

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        //json对象转Map
        return (Map<String,Object>)jsonObject;
    }
    public Map<String, Object> object2Map(Object object){
        JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
        Set<Map.Entry<String,Object>> entrySet = jsonObject.entrySet();
        Map<String, Object> map=new HashMap<String,Object>();
        for (Map.Entry<String, Object> entry : entrySet) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }
}
