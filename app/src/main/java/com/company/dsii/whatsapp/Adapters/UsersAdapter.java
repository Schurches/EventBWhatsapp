package com.company.dsii.whatsapp.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        UserHolder userHolder;
        if (userItem== null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            userItem = inf.inflate(R.layout.user_item, null);
        }
        userHolder = new UserHolder(userItem);
        userHolder.setUserInfo(userList.get(position));
        userHolder.changeSelectedState();
        return userItem;
    }

    public class UserHolder {
        TextView userName;
        TextView userNumber;
        LinearLayout userItemBackground;
        boolean isSelected;

        public UserHolder(View view) {
            userName = view.findViewById(R.id.userName);
            userNumber = view.findViewById(R.id.userStatus);
            userItemBackground = view.findViewById(R.id.userBackground);
            isSelected = false;
        }

        private void setUserInfo(User currentUser){
            userName.setText(currentUser.getUsername());
            userNumber.setText(currentUser.getNumber());
        }

        public void changeSelectedState(){
            if(isSelected){
                userItemBackground.setBackgroundColor(ContextCompat.getColor(context,R.color.colorDivider));
            }else{
                userItemBackground.setBackgroundColor(ContextCompat.getColor(context,R.color.colorTextIcons));
            }

        }

    }


}
