package com.company.dsii.whatsapp.Models;

public class ChatContent {

    private int contentId; //ChatContent ID (Referenced in
    private int sender;
    private int chatID;
    private String content;
    private String date;

    public ChatContent(int contentId, int sender, int chatID, String content, String date) {
        this.contentId = contentId;
        this.sender = sender;
        this.chatID = chatID;
        this.content = content;
        this.date = date;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getChatID() {
        return chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
