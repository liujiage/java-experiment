package com.java.experiment;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class BigDataTypeTest {



    @Test
    void test_amount(){
        BigDecimal withdrawValue = BigDecimal.valueOf(-100);
        BigDecimal userAssets = BigDecimal.valueOf(100);
        boolean withdrawValueBigger = withdrawValue.compareTo(userAssets.subtract(withdrawValue)) > 0;
        log.info("result: {} ", userAssets.subtract(withdrawValue));
        log.info("result: {} ", withdrawValueBigger);
    }
}
