package com.company.dsii.whatsapp.Models;

import java.util.ArrayList;

public class User {

    int id; //ID of the user (To reference in machine2 users list)
    String username; //User name (Will be displayed in the chat)
    String status; //User status (Just a flavor text, will be displayed on the userlist)
    ArrayList<Integer> friends; // For database purposes, each user shall store its friends list
    ArrayList<Integer> chats; //For database purposes, each user shall store its chats (And will be used in the chatlist)

    public User(int id, String username, String status, ArrayList<Integer> friends, ArrayList<Integer> chats) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.friends = friends;
        this.chats = chats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Integer> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Integer> friends) {
        this.friends = friends;
    }

    public ArrayList<Integer> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Integer> chats) {
        this.chats = chats;
    }
}
