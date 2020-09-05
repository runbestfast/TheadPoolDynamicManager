package com.wy.threadpool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class DynamicTheadManager {

    private static Map<String, DynamicThreadPool> dynamicThreadMap = new ConcurrentHashMap<>();

    private static DynamicTheadManager INSTANCE = new DynamicTheadManager();

    public static DynamicTheadManager getInstance() {
        return INSTANCE;
    }

    private DynamicTheadManager() {
    }


    // 注册
    public void register(DynamicThreadPool thread) {
        dynamicThreadMap.put(thread.getPoolName(), thread);
    }

    public DynamicThreadPool get(String poolName) {
        return dynamicThreadMap.get(poolName);
    }
}
