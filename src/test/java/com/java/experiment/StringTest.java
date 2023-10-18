package com.java.experiment;

import com.java.experiment.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


@Slf4j
public class StringTest {


    @Test
    void test_contain(){
        //String value = "{[OrderVo(companyId=null, companyUserId=null, userId=null, kyc=null, username=null, areaCode=null, phone=null, email=null, orderType=null, idCardType=null, idCardNum=null, payCardNo=null, payCardBank=null, payCardBranch=null, orderPayChannel=null, orderNo=2020082998425664_16950217147276, companyOrderNum=null, coinSign=null, payCoinSign=null, coinAmount=null, unitPrice=null, total=null, displayUnitPrice=null, fee=null, mode=null, traderId=null, traderUserId=null, traderPaymentId=null, orderTime=null, syncUrl=null, asyncUrl=null, sign=null, paymentPassword=null, code=null, orderScenario=null, whetherNeedCancelBatch=null, whetherNeedCancelOrder=null, oldOrderNo=null, sourceType=null, callMode=null, ifsc=null, upi=null, accountType=null, beneficiary=null, signType=null, submitMode=null, baseImage=null), org.apache.catalina.connector.RequestFacade@3eb83a8f]}";
        String value2 = "OrderVo(companyId=null, companyUserId=null, userId=null, kyc=null, username=null, areaCode=null, phone=null, email=null, orderType=null, idCardType=null, idCardNum=null, payCardNo=null, payCardBank=null, payCardBranch=null, orderPayChannel=null, orderNo=2020082998425664_16950217147276, companyOrderNum=null, coinSign=null, payCoinSign=null, coinAmount=null, unitPrice=null, total=null, displayUnitPrice=null, fee=null, mode=null, traderId=null, traderUserId=null, traderPaymentId=null, orderTime=null, syncUrl=null, asyncUrl=null, sign=null, paymentPassword=null, code=null, orderScenario=null, whetherNeedCancelBatch=null, whetherNeedCancelOrder=null, oldOrderNo=null, sourceType=null, callMode=null, ifsc=null, upi=null, accountType=null, beneficiary=null, signType=null, submitMode=null, baseImage=null)";
        log.info("test: {}", "baseImage".equals(value2));
        if(value2.length() > 5){
            value2 = value2.substring(0, 5).concat(" |alert: the parameter is too long, cut it from 5 length! ");
            log.info("test2: {}", value2);
        }
    }

    @Test
    void test_string(){
        Object[] users = new Object[]{new UserVO("1", "name1")};
        log.info(Arrays.toString(users));
    }


    @Test
    void test_string_split(){
        String s = "RECHARGE_DETAIL_2020071631378034_1697613181109";
        String orderNo = s.substring(s.lastIndexOf("_") + 1);
        log.info(orderNo);

    }

}
