package com.entity;

import java.io.Serializable;

/**
 * 用户类
 */
public class UserLogin implements Serializable {

    private int id;

    private String userName;

    private String passWord;
    private String role;

    private String realName;
    private Integer college;



    public UserLogin() {
    }

    public UserLogin(int id, String userName, String passWord,Integer college) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.college=college;
    }

    public Integer getCollege() {
        return college;
    }

    public void setCollege(Integer college) {
        this.college = college;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
