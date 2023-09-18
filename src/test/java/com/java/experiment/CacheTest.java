package com.java.experiment;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.java.experiment.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class CacheTest {

    final ReentrantLock lock = new ReentrantLock();
    final Cache<String, UserVO> cache = Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .softValues()
            .initialCapacity(100)
            .maximumSize(10000)
            .build();
    @Test
    void test_init() throws Exception{
        //got a lock
        boolean isLockAcquired = lock.tryLock(1, TimeUnit.SECONDS);
        if(!isLockAcquired){
            log.info("Not get a lock, do nothing!");
            return;
        }
        try {
            UserVO user = cache.getIfPresent("1");
            log.info(String.valueOf(user));
            cache.put("1", new UserVO("1", "name1"));
            while (true) {
                user = cache.getIfPresent("1");
                if (cache.getIfPresent("1") == null) {
                    log.info("the value is null");
                    break;
                } else {
                    log.info("the value is {} ", toString(user));
                }
                Thread.sleep(1000);
            }
        }catch(Exception e){
            log.error("error", e);
        }finally{
            lock.unlock();
        }
    }


    private String toString(Object obj){
        return obj == null ? "" : obj.toString();
    }

}
