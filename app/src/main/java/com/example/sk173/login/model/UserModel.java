package com.example.sk173.login.model;

/**
 * Created by sk173 on 2018-02-21.
 */

public class UserModel {

    public String userId;

    public UserModel(){
    }
    public UserModel(String userId){
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
