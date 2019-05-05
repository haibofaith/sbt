package com.haibo.base.entity;

import java.io.Serializable;

/**
 * @author:haibo.xiong
 * @date:2019/4/25
 * @description:
 */
public class User implements Serializable{
    private String userName;
    private Integer userId;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
}
