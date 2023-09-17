package com.java.experiment;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public class RateLimitTest {

    private final ConcurrentMap<String, RateLimiter> limitIp = new ConcurrentHashMap<String, RateLimiter>();

    private final RateLimiterConfig config = RateLimiterConfig.custom().limitForPeriod(2)
            .limitRefreshPeriod(Duration.ofMinutes(60)).timeoutDuration(Duration.ofMillis(1)) .build();

    public synchronized RateLimiter getLimiter(String key){
        //try to get the limiter from local cache
        if(!limitIp.containsKey(key)){
            RateLimiter rateLimiter = RateLimiterRegistry.of(this.config).rateLimiter(key);
            limitIp.put(key, rateLimiter);
        }
        return limitIp.get(key);
    }

    @Test
    void test_resilience4j_2() throws Exception{
        RateLimitTest limiter = new RateLimitTest();
        for(int j=0; j< 100; j++) {
            for (int i = 0; i < 2; i++) {
                String key = "key" + i;
                new Thread(new MyRunnable(limiter.getLimiter(key))).start();
            }
        }
        Thread.currentThread().join();
    }

    @Test
    void test_resilience4j() throws Exception{
        RateLimitTest limiter = new RateLimitTest();
        for(int j=0; j< 100; j++) {
            for (int i = 0; i < 2; i++) {
                String key = "key" + i;
                new Thread() {
                    final RateLimiter rateLimiter = limiter.getLimiter(key);
                    public void run() {
                        if (rateLimiter.acquirePermission()) {
                            log.info("key: {} ==========ok got a token ===========", rateLimiter.getName());
                            //limiter.setLimiter(key, rateLimiter);
                        } else {
                            log.info("key: {} not get a token", rateLimiter.getName());
                            //break;
                        }
                    }
                }.start();
            }
        }
        Thread.currentThread().join();
    }
}

@Slf4j
class MyRunnable implements Runnable {

    private final RateLimiter limiter;
    public MyRunnable(RateLimiter key) {
        this.limiter = key;
    }
    @Override
    public void run() {
        //try {
            //while (true) {
                if (limiter.acquirePermission()) {
                    log.info("key: {} ==========ok got a token ===========", limiter.getName());
                } else {
                    log.info("key: {} not get a token", limiter.getName());
                    //break;
                }
                //Thread.sleep(100);
            //}
        //}catch (InterruptedException e) {
          //  throw new RuntimeException(e);
        //}
    }
}