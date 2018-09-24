package com.company.dsii.whatsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.company.dsii.whatsapp.Models.Chat;
import com.company.dsii.whatsapp.Models.User;
import com.company.dsii.whatsapp.R;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Chat> chatList;
    private ArrayList<User> userList;

    public ChatAdapter(Context context, ArrayList<Chat> chatList, ArrayList<User> userList) {
        this.context = context;
        this.chatList = chatList;
        this.userList = userList;
    }


    @Override
    public int getCount() {
        return chatList.size();
    }

    @Override
    public Chat getItem(int i) {
        return chatList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public User findUserById(int userID){
        int size = userList.size();
        for (int i = 0; i < size; i++){
            if(userList.get(i).getId() == userID){
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public View getView(int position, View chatItem, ViewGroup viewGroup) {
        View chatBox = chatItem;
        if (chatBox == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            chatBox = inf.inflate(R.layout.chatroom_item, null);
        }
        Chat currentChat= chatList.get(position);
        User chattingUser = findUserById(currentChat.getChattingUserID());
        //Chat title
        TextView chatName = chatBox.findViewById(R.id.chatName);
        chatName.setText(chattingUser.getUsername());
        //Chat Date
        TextView chatDate = chatBox.findViewById(R.id.chatDate);
        chatDate.setText("3:00PM");
        //Chat last message
        TextView lastMessage = chatBox.findViewById(R.id.chatMessage);
        lastMessage.setText(currentChat.getMessagePreview());

        return chatBox;
    }


}
