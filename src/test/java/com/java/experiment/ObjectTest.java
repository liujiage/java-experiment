package com.java.experiment;

import com.java.experiment.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class ObjectTest {

    @Test
    void test_user(){
        UserVO user = new UserVO("1","name1");
        String[] s = new String[]{
                user.getUserId(),
                user.getUserName()
        };
        user.setUserId(null);
        log.info("test result userId: {}, userName:{}", s[0],s[1]);
        Assertions.assertNotEquals(s[0], null);
    }
}
