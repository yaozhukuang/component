package com.zw.yzk.component.common.module;

import android.util.Log;

import java.util.HashMap;

/**
 * @author zhanwei
 */
public class ServiceMap {

    private static ServiceMap instance;
    private HashMap<String, MService> serviceMap =  new HashMap<>();

    public static ServiceMap getInstance() {
        if(instance == null) {
            synchronized (ServiceMap.class) {
                if(instance == null) {
                    instance = new ServiceMap();
                }
            }
        }
        return instance;
    }

    public void put(String key, MService service) {
        Log.e("zhanwei","put [key: " + key + ", service: " + service + "]");
        serviceMap.put(key, service);
    }

    public MService get(String key) {
        return serviceMap.get(key);
    }

}
