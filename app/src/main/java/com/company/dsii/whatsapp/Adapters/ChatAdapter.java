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
        ViewHolder chatBox;
        if (chatItem == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            chatItem = inf.inflate(R.layout.chatroom_item, viewGroup,false);
        }
        chatBox = new ViewHolder(chatItem);
        chatBox.setChatInfo(chatList.get(position));
        chatBox.chatDate.setText("3:00PM");
        return chatItem;
    }

    private class ViewHolder {
        TextView chatName;
        TextView chatDate;
        TextView lastMessage;

        private ViewHolder(View view) {
            chatName = view.findViewById(R.id.chatName);
            chatDate = view.findViewById(R.id.chatDate);
            lastMessage = view.findViewById(R.id.chatMessage);
            chatDate.setText("3:00PM");
        }

        private void setChatInfo(Chat currentChat){
            User chattingUser;
            if(currentChat.getChattingUserID().size() == 1){
                chattingUser = findUserById(currentChat.getChattingUserID().get(0));
                chatName.setText(chattingUser.getUsername());
            }else{
                chatName.setText(currentChat.getTitle());
            }
            setLastMessageText(currentChat.getMessagePreview());
        }

        private void setLastMessageText(String text){
            lastMessage.setText(text);
        }

    }

}
