package com.wy.threadpool;

import lombok.Getter;

import java.util.concurrent.*;

@Getter
public class DynamicThreadPool {
    /**
     * 线程池
     */
    private ThreadPoolExecutor threadPoolExecutor;
    /**
     * 线程池名
     */
    private String poolName;

    private BlockingQueue<Runnable> blockingQueue;

    public DynamicThreadPool(String poolName, int coreSize, int maxSize, int queueSize) {
        this.blockingQueue = new LinkedBlockingQueue<Runnable>(queueSize);
        this.threadPoolExecutor = new org.apache.tomcat.util.threads.ThreadPoolExecutor(coreSize, maxSize,
                0L, TimeUnit.MILLISECONDS,
                blockingQueue,
                new DynamicThreadPoolFactory(poolName));
        this.poolName = poolName;
        // 注册到 manager
        DynamicTheadManager.getInstance().register(this);
    }

    public static DynamicThreadPool create(String poolName, int coreSize, int maxSize, int queueSize) {
        return new DynamicThreadPool(poolName, coreSize, maxSize, queueSize);
    }


    public void setCoreSize(int coreSize) {
        this.threadPoolExecutor.setCorePoolSize(coreSize);
    }

    public void setMaxSize(int maxSize) {
        this.threadPoolExecutor.setMaximumPoolSize(maxSize);
    }

    public void setQueueSize(int queueSize) {
        // todo: 可变数组长度
    }

}
