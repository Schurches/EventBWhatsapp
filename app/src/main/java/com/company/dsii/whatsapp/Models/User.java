package com.company.dsii.whatsapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class User {

    int id; //ID of the user (To reference in machine2 users list)
    String username; //User name (Will be displayed in the chat)
    String number; //User phone number (Necessary for adding users)
    ArrayList<Integer> contacts; // Each user shall store its own list of users
    ArrayList<Integer> chatIDs;
//    ArrayList<ArrayList<Integer>> messageIDs;
    HashMap<String, ArrayList<Integer>> messageIDs;
//    ArrayList<HashMap<String,ArrayList<Integer>>> chats; //For delete_conent (self), each user shall store its chats and messages

    public User(){

    }

    public User(int id, String username, String number, ArrayList<Integer> contacts, ArrayList<Integer> chatIDs, HashMap<String,ArrayList<Integer>> messageIDs) {
        this.id = id;
        this.username = username;
        this.number = number;
        this.contacts = contacts;
        this.chatIDs = chatIDs;
        this.messageIDs = messageIDs;
    }

    public HashMap<String, ArrayList<Integer>> getMessageIDs() {
        return messageIDs;
    }

    public void setMessageIDs(HashMap<String, ArrayList<Integer>> messages) {
        this.messageIDs = messages;
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

    public ArrayList<Integer> getContacts() {
        return contacts;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setContacts(ArrayList<Integer> contacts) {
        this.contacts = contacts;
    }

    public int[] ArrayToInteger(ArrayList<Integer> mArraylist) {
        int size = mArraylist.size();
        int[] list = new int[size];
        for(int i = 0; i < size; i++){
            list[i] = mArraylist.get(i);
        }
        return list;
    }

    public ArrayList<Integer> getChatIDs() {
        return chatIDs;
    }

    public void setChatIDs(ArrayList<Integer> chatIDs) {
        this.chatIDs = chatIDs;
    }

}
