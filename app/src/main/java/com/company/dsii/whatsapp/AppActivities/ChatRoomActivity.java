package com.company.dsii.whatsapp.AppActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;

import com.company.dsii.whatsapp.Adapters.MessagesAdapter;
import com.company.dsii.whatsapp.Models.Chat;
import com.company.dsii.whatsapp.Models.ChatContent;
import com.company.dsii.whatsapp.Models.User;
import com.company.dsii.whatsapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatRoomActivity extends AppCompatActivity {

    //Widgets
    private EditText messageBox;
    private ImageButton sendIcon;
    private Toolbar toolbar;
    private RecyclerView messagesRecycler;
    //Database, model and bundle
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference chatReference = db.getReference("Chats"); //Obtener el chat en concreto y su lista de usuarios
    private DatabaseReference usersReference = db.getReference("Users"); //Obtener los usuarios entre esos yo
    private User currentUser;
    private Chat currentChat;
    private Bundle data;
    //Variables
    private MessagesAdapter messagesAdapter;
    private ArrayList<ChatContent> messagesArray = new ArrayList<>();
    private ArrayList<User> chatMembers = new ArrayList<>();
    private int chatID;
    private String userNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        messageBox = findViewById(R.id.chat_message);
        sendIcon = findViewById(R.id.send_button);
        toolbar = findViewById(R.id.chat_toolbar);
        messagesRecycler = findViewById(R.id.chat_recycler);
        setSupportActionBar(toolbar);
        messagesRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        data = getIntent().getExtras();
        chatID = data.getInt("chatID");
        iniChatReference();
    }

    public boolean isUserAMember(int userID, ArrayList<Integer> membersID){
        int size = membersID.size();
        for(int i = 0; i < size; i++){
            if(userID == membersID.get(i)){
                return true;
            }
        }
        return false;
    }
    /*Messages message = dataSnapshot.getValue(Messages.class);
                if(message!=null){
        int last_visible_position = ((LinearLayoutManager)messages_view.getLayoutManager()).findLastVisibleItemPosition();
        int last_message_position = adapter.getItemCount()-1;
        Users u = obtainUser(currentUser.getUid());
        boolean isCurrentUserTheSender = message.getUsername().equals(u.getUsername());
        last50Messages.add(message);
        adapter.notifyDataSetChanged();
        if(wereAllMessagesLoaded){
            if(!isCurrentUserTheSender){
                playMessageSFX();
            }
            scroll(last_visible_position,last_message_position,isCurrentUserTheSender);
        }
    }*/

    public int findPositionOfChatInDoubleArray(int chatID){
        HashMap<String,ArrayList<Integer>> chatsToMessages = currentUser.getMessageIDs();
        int size = chatsToMessages.size();
        for(int i = 0; i < size; i++){
            //if(chatsToMessages.get(i))
        }
        return -1;
    }

    public void iniUserReference(){
        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //currentUser.getMessageIDs().get();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        usersReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User U = dataSnapshot.getValue(User.class);
                if(U.getNumber().equals(userNumber)){
                    currentUser = U;
                }else{
                    if(isUserAMember(U.getId(),currentChat.getChattingUserID())){
                        chatMembers.add(U);
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                User U = dataSnapshot.getValue(User.class);
                if(U.getNumber().equals(userNumber)){
                    currentUser = U;
                }else{
                    if(isUserAMember(U.getId(),currentChat.getChattingUserID())){
                        int size = chatMembers.size();
                        for(int i = 0; i < size; i++){
                            if(chatMembers.get(i).getId() == U.getId()){
                                chatMembers.set(i,U);
                            }
                        }
                    }
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void iniChatReference(){
        chatReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userNumber = data.getString("userNumber");
                iniUserReference();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        chatReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Chat C = dataSnapshot.getValue(Chat.class);
                if(C.getId() == chatID){
                    currentChat = C;
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


 /*   public void sendMessage(View view){
        String text = textField.getText().toString();
        if(!text.isEmpty() && !text.trim().isEmpty()){
            Messages.sendMessage(obtainUser(currentUser.getUid()).getUsername(),
                    text,
                    Messages.getDateFormatted(new Date()),CURRENT_CHAT_NAME,0);
        }
        textField.setText("");
    }*/

   /* public void scroll(int last_visible_position, int last_message_position, boolean isCurrentUserTheSender){
        if((last_visible_position != -1 && last_visible_position == last_message_position) || isCurrentUserTheSender){
            messages_view.smoothScrollToPosition(last50Messages.size()-1);
        }
    }*/


}
