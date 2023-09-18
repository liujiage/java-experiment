package com.java.experiment;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
public class ExceptionTest {

    @Test
    void test_try_exception(){
        try{
            String s = null;
        }
        catch(NullPointerException npe){
            log.error("NullPointerException error cause:{} error message:{} ",
                    npe.getCause(), npe.getMessage(), npe);
        }
        catch(Exception e){
            log.error("Exception error cause:{} error message:{} ",
                    e.getCause(), e.getMessage(), e);
        }finally {
            log.info("finally");
        }
    }
}
