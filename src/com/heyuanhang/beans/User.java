package com.heyuanhang.beans;

import java.io.Serializable;

/**
 * @Author 何远航
 * @Date: 2021/6/2 17:27
 * @Version 1.8
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userID;
    private String userName;
    private String pwd;
    private Integer repeatUser;

    public Integer getRepeatUser() {
        return repeatUser;
    }

    public void setRepeatUser(Integer repeatUser) {
        this.repeatUser = repeatUser;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public User() {
    }

    public User(String userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
