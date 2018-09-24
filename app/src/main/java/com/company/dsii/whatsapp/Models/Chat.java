package com.company.dsii.whatsapp.Models;

import java.util.ArrayList;

public class Chat {

    int id; //Chat ID (to reference in machine2 chats)
    int chattingUserID; //userID of the chat interaction
    int status; // 1 = active(unmuted), 0 = muted.
    ArrayList<Integer> messages; //Messages for this chat
    String messagePreview; //Last message sent or received

    public String getMessagePreview() {
        return messagePreview;
    }

    public void setMessagePreview(String messagePreview) {
        this.messagePreview = messagePreview;
    }

    public Chat(int id, int userID, int status, ArrayList<Integer> messages, String messagePreview) {
        this.id = id;
        this.chattingUserID = userID;
        this.status = status;
        this.messages = messages;
        this.messagePreview = messagePreview;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public int getChattingUserID() {
        return chattingUserID;
    }

    public void setChattingUserID(int chattingUserID) {
        this.chattingUserID = chattingUserID;
    }

    public void setStatus(int status) {

        this.status = status;
    }

    public ArrayList<Integer> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Integer> messages) {
        this.messages = messages;
    }
}
