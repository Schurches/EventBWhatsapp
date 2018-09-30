package com.company.dsii.whatsapp.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.company.dsii.whatsapp.Models.ChatContent;
import com.company.dsii.whatsapp.Models.User;
import com.company.dsii.whatsapp.R;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ChatContent> chat_messages;
    private ArrayList<User> user_list;
    private User currentUser;
    private Context context;

    public MessagesAdapter(User currentUser, ArrayList<ChatContent> messages_list, ArrayList<User> user_list, Context app_context){
        this.chat_messages = messages_list;
        this.user_list = user_list;
        this.context = app_context;
        this.currentUser = currentUser;
        setHasStableIds(true);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder message_body = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View messageBox = inflater.inflate(R.layout.message_item,parent,false);
        message_body = new MessagesHolder(messageBox);
        return message_body;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MessagesHolder messagesHolder = (MessagesHolder) holder;
        User user = findUserById(chat_messages.get(position).getSender());
        if(user == null){ //Si es nulo es porque lo envie yo
            messagesHolder.getLayoutMessage().setHorizontalGravity(Gravity.END);
            messagesHolder.getLayoutMessage().setBackgroundTintList(
                    ColorStateList.valueOf(
                            ContextCompat.getColor(context,R.color.colorAccent)));
            messagesHolder.loadMessage(chat_messages.get(position),currentUser);
        }else{
            messagesHolder.getLayoutMessage().setBackgroundTintList(
                    ColorStateList.valueOf(
                            ContextCompat.getColor(context,R.color.colorDivider)));
            messagesHolder.loadMessage(chat_messages.get(position),user);
        }

    }

    @Override
    public int getItemCount() {
        return chat_messages.size();
    }

    public User findUserById(int ID){
        int size = user_list.size();
        for(int i = 0; i < size; i++){
            if(user_list.get(i).getId() == ID){
                return user_list.get(i);
            }
        }
        return null;
    }

    public class MessagesHolder extends RecyclerView.ViewHolder{

        private LinearLayout LayoutMessage;
        private TextView SentMessage;
        private TextView UserMessage;

        public MessagesHolder(View itemView) {
            super(itemView);
            this.LayoutMessage = itemView.findViewById(R.id.message_layout);
            this.UserMessage = itemView.findViewById(R.id.message_sender);
            this.SentMessage = itemView.findViewById(R.id.message_content);
        }

        public LinearLayout getLayoutMessage() {
            return LayoutMessage;
        }

        public void setLayoutMessage(LinearLayout layoutMessage) {
            LayoutMessage = layoutMessage;
        }

        public TextView getSentMessage() {
            return SentMessage;
        }

        public void setSentMessage(TextView sentMessage) {
            SentMessage = sentMessage;
        }

        public TextView getUserMessage() {
            return UserMessage;
        }

        public void setUserMessage(TextView userMessage) {
            UserMessage = userMessage;
        }

        public void loadMessage(ChatContent message, User U){
            this.UserMessage.setText(U.getUsername());
            this.SentMessage.setText(message.getContent());
        }
    }
}
