package com.java.experiment.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO implements Serializable {

    private String userId;
    private String userName;

    @Override
    public String toString(){
        return "userId: "+this.userId+",userName: "+this.userName;
    }

}
