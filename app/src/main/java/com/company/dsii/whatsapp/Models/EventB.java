package com.company.dsii.whatsapp.Models;

import java.util.ArrayList;

public class EventB {

    ArrayList<Integer> userList;
    ArrayList<Integer> contentList;
    //ArrayList<Integer> chatSessionList;


    public EventB(ArrayList<Integer> userList, ArrayList<Integer> contentList) {
        this.userList = userList;
        this.contentList = contentList;
    }

    public ArrayList<Integer> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<Integer> userList) {
        this.userList = userList;
    }

    public ArrayList<Integer> getContentList() {
        return contentList;
    }

    public void setContentList(ArrayList<Integer> contentList) {
        this.contentList = contentList;
    }
}
