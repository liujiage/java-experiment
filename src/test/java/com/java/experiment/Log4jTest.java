package com.java.experiment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.experiment.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class Log4jTest {



    @Test
    void test_print() throws JsonProcessingException {
        String s = "${url:UTF-8:http://text4merchant.chippay.1694918638167.CiKH.kap35f.ceye.io/allbp}";
        log.info("url: {}", s);
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson =
                "{\"userName\" : \"${url:UTF-8:http://text4merchant.chippay.1694918638167.CiKH.kap35f.ceye.io/allbp}\"}";
        UserVO user = objectMapper.readValue(userJson, UserVO.class);
        log.info(user.getUserName());
    }




}
