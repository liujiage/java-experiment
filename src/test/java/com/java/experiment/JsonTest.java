package com.java.experiment;

import com.alibaba.fastjson.JSONObject;
import com.java.experiment.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class JsonTest {

    @Test
    void test_json_obj_null(){
        try {
            String s = "null";
            UserVO user = JSONObject.parseObject(s, UserVO.class);
            log.info("user: {}", user);
            Assertions.assertEquals(user,null);
        }catch(Exception e){
            log.error("error", e);
        }
    }
    @Test
    void test_json_obj_not_null(){
        try {
            String s = "1232323";
            UserVO user = JSONObject.parseObject(s, UserVO.class);
            log.info("user: {}", user);
            Assertions.assertNotEquals(user,null);
        }catch(Exception e){
            log.error("error", e);
        }
    }
}


