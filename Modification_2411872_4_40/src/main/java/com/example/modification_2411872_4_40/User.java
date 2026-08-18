package com.example.modification_2411872_4_40;

import java.io.Serializable;

public abstract class User implements Serializable {
    protected final int userId;
    protected String userName;
    protected String gender;

    public User(int userId, String userName, String gender) {
        this.userId = userId;
        this.userName = userName;
        this.gender = gender;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
