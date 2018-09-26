package com.company.dsii.whatsapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User implements Parcelable {

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

    protected User(Parcel in) {
        id = in.readInt();
        username = in.readString();
        status = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public int[] ArrayToInteger(ArrayList<Integer> mArraylist) {
        int size = mArraylist.size();
        int[] list = new int[size];
        for(int i = 0; i < size; i++){
            list[i] = mArraylist.get(i);
        }
        return list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(username);
        parcel.writeString(status);
        parcel.writeIntArray(ArrayToInteger(friends));
        parcel.writeIntArray(ArrayToInteger(chats));
    }
}
