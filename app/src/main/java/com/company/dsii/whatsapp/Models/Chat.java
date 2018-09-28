package com.company.dsii.whatsapp.Models;

import java.util.ArrayList;

public class Chat {

    int id; //Chat ID (to reference in machine2 chats)
    ArrayList<Integer> chattingUserID; //userID of the chat interaction
    String title; //Chat title
    String messagePreview; //Last message sent or received


    public void setMessagePreview(String messagePreview) {
        this.messagePreview = messagePreview;
    }

    public Chat(){

    }

    public Chat(int id, ArrayList<Integer> userID, String title, String messagePreview) {
        this.id = id;
        this.chattingUserID = userID;
        this.title = title;
        this.messagePreview = messagePreview;

    }

    public String getMessagePreview() {
        return messagePreview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getChattingUserID() {
        return chattingUserID;
    }

    public void setChattingUserID(ArrayList<Integer> chattingUserID) {
        this.chattingUserID = chattingUserID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
