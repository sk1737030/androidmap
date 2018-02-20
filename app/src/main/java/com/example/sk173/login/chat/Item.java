package com.example.sk173.login.chat;

/**
 * Created by sk173 on 2018-02-20.
 */
public class Item {
    String chat_userId;
    String chat_comments;
    public String getChat_userId() {
        return chat_userId;
    }
    public String getChat_comments() {
        return chat_comments;
    }
    public Item(){

    }
    public Item(String chat_userId, String chat_comments) {
        this.chat_userId = chat_userId;
        this.chat_comments = chat_comments;
    }
}