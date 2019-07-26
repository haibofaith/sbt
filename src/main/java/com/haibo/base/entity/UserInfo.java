package com.haibo.base.entity;

import java.io.Serializable;

/**
 * @author:haibo.xiong
 * @date:2019/7/26
 * @description:
 */
public class UserInfo implements Serializable{
    private String userName;
    private Integer userId;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
