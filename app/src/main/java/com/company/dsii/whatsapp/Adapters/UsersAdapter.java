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

public class UsersAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<User> userList;

    public UsersAdapter(Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList = userList;
    }


    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public User getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View userItem, ViewGroup viewGroup) {
        View selectedUser = userItem;
        if (selectedUser == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            selectedUser = inf.inflate(R.layout.user_item, null);
        }
        User currentUser= userList.get(position);
        //Chat title
        TextView chatName = selectedUser.findViewById(R.id.userName);
        chatName.setText(currentUser.getUsername());
        //Chat Date
        TextView chatDate = selectedUser.findViewById(R.id.userStatus);
        chatDate.setText(currentUser.getStatus());
        //Chat last message
        TextView lastMessage = selectedUser.findViewById(R.id.userState);
        //lastMessage.setText(currentChat.getMessagePreview());

        return selectedUser;
    }

}
