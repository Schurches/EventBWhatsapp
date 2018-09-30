package com.company.dsii.whatsapp.AppActivities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.company.dsii.whatsapp.Adapters.MessagesAdapter;
import com.company.dsii.whatsapp.Models.Chat;
import com.company.dsii.whatsapp.Models.ChatContent;
import com.company.dsii.whatsapp.Models.User;
import com.company.dsii.whatsapp.R;
import com.company.dsii.whatsapp.Util.SparseIDs;
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
    private DatabaseReference messagesReference = db.getReference("Messages"); //Obtener los mensajes
    private DatabaseReference sparseReference = db.getReference("Util"); //Obtenet lista de IDs
    private User currentUser;
    private Chat currentChat;
    private Bundle data;
    //Variables
    private MessagesAdapter messagesAdapter;
    private ArrayList<ChatContent> chatMessages = new ArrayList<>();
    private ArrayList<ChatContent> allMessages = new ArrayList<>();
    private ArrayList<User> chatMembers = new ArrayList<>();
    private int chatID;
    private String userNumber;
    private boolean firstLoad = true;

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
        userNumber = data.getString("userNumber");
        iniChatReference();
        sendIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contentText = messageBox.getText().toString();
                if(contentText.length() != 0){
                    int ID = SparseIDs.findAnAvailableID();
                    sparseReference.setValue(SparseIDs.getIsIDAvailable());
                    ChatContent Ch = new ChatContent(ID,currentUser.getId(),chatID,contentText,"3:00");
                    messagesReference = db.getReference("Messages/"+chatID);
                    String key = messagesReference.push().getKey();
                    messagesReference.child(key).setValue(Ch);
                    currentUser.getMessageIDs().get(chatID+"").add(ID);
                    usersReference.child(currentUser.getNumber()).setValue(currentUser);
                    int size = chatMembers.size();
                    for(int i = 0; i < size; i++){
                        usersReference = db.getReference("Users");
                        User u = chatMembers.get(i);
                        u.getMessageIDs().get(chatID+"").add(ID);
                        usersReference.child(u.getNumber()).setValue(u);
                    }
                    messageBox.setText("");
                }else{
                    Snackbar.make(view,"Escribe algo",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
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

    public void iniMessagesArray(){
        ArrayList<Integer> allChatMessages = currentUser.getMessageIDs().get(chatID+"");
        int size = allChatMessages.size();
        for(int i = 1; i < size; i++){
            chatMessages.add(findMessageByID(allChatMessages.get(i)));
        }
    }

    public void iniMessagesReference(){
        messagesReference = messagesReference.child(chatID+"");
        messagesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //iniMessagesArray();
                firstLoad = false;
                messagesAdapter = new MessagesAdapter(currentUser,chatMessages,chatMembers,getApplicationContext());
                messagesRecycler.setAdapter(messagesAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        messagesReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatContent chat = dataSnapshot.getValue(ChatContent.class);
                allMessages.add(chat);
                if(chat.getSender() == currentUser.getId() || isUserAMember(chat.getSender(),currentChat.getChattingUserID())){
                    chatMessages.add(chat);
                }
                if(!firstLoad){
                    messagesAdapter.notifyDataSetChanged();
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

    public ChatContent findMessageByID(int messageID){
        int size = allMessages.size();
        for(int i = 0; i < size; i++){
            if(allMessages.get(i).getContentId() == messageID){
                return allMessages.get(i);
            }
        }
        return null;
    }


    public void iniUserReference(){
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
        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                iniMessagesReference();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void iniChatReference(){
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
        chatReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                iniUserReference();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }




}
