package com.company.dsii.whatsapp.AppActivities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.company.dsii.whatsapp.Adapters.ChatAdapter;
import com.company.dsii.whatsapp.Models.Chat;
import com.company.dsii.whatsapp.Models.User;
import com.company.dsii.whatsapp.R;
import com.company.dsii.whatsapp.Util.SparseIDs;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatListActivity extends AppCompatActivity {

    //Widgets
    private FloatingActionButton optionFAB;
    private FloatingActionButton chatFAB;
    private ListView chatList;
    private ChatAdapter chatListAdapter;
    //Constants
    private final int INITCODE= 1000;
    private final int MUTECODE = 1001;
    private final int DELETECODE = 1002;
    private final int LOWER_ID = -100;
    private final int UPPER_ID = 100;
    private int DEVICE_WIDTH;
    private int DEVICE_HEIGHT;
    private int floatingBaseSize;
    private int miniFloatingBaseSize;
    private boolean isChatListLoaded = false;
    private boolean isUserListLoaded = false;
    private String currentUserRoute;
    //Database connections
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference chatlistReference = db.getReference("Chats");
    private DatabaseReference userListReference = db.getReference("Users");
    //Arrays and variables
    private User currentUser;
    private boolean wereOptionsOpen = false;
    private final ArrayList<Chat> chatListing = new ArrayList<>();
    private final ArrayList<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        //Instantiate components
        setContentView(R.layout.activity_chat_list);
        Toolbar toolbar = findViewById(R.id.chats_toolbar_menu);
        chatList = findViewById(R.id.chatList);
        optionFAB = findViewById(R.id.chatListOptions);
        chatFAB = findViewById(R.id.startChatOption);
        //Pivot values
        DEVICE_WIDTH = getResources().getDisplayMetrics().widthPixels;
        DEVICE_HEIGHT = getResources().getDisplayMetrics().heightPixels;
        floatingBaseSize = optionFAB.getWidth();
        miniFloatingBaseSize = floatingBaseSize/4;
        currentUserRoute = getIntent().getExtras().getString("current_user_route");
        //init functions
        setSupportActionBar(toolbar);
        iniFloatingActionButtons();
        DatabaseReference currentUserReference = db.getReference(currentUserRoute);
        currentUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentUser = dataSnapshot.getValue(User.class);
                iniDatabaseGathering();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //Adapter
    }

    public void setAdapter(boolean isChatListLoaded, boolean isUserListLoaded){
        if(isChatListLoaded & isUserListLoaded){
            chatListAdapter = new ChatAdapter(this, chatListing, userList);
            chatList.setAdapter(chatListAdapter);
            iniChatlistListener();
        }
    }

    public boolean doesChatBelongToUser(int ID1, ArrayList<Integer> chatIDlist){
        int size = chatIDlist.size();
        for(int i = 0; i < size; i++){
            if(ID1 == chatIDlist.get(i) && ID1 != currentUser.getId()){
                return true;
            }
        }
        return false;
    }

    public void iniDatabaseGathering(){
        chatlistReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                isChatListLoaded = true;
                setAdapter(isChatListLoaded,isUserListLoaded);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        chatlistReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Chat chat_i = dataSnapshot.getValue(Chat.class);
                if(doesChatBelongToUser(chat_i.getId(),currentUser.getChatIDs())){
                    chatListing.add(chat_i);
                    if(chatListAdapter != null){
                        chatListAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Chat chat_i = dataSnapshot.getValue(Chat.class);
                int pos = findChatPositionById(chat_i.getId());
                if(pos != -1){
                    chatListing.set(pos,chat_i);
                    chatListAdapter.notifyDataSetChanged();
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
        userListReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                isUserListLoaded = true;
                setAdapter(isChatListLoaded,isUserListLoaded);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        userListReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user_i = dataSnapshot.getValue(User.class);
                userList.add(user_i);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                User u = dataSnapshot.getValue(User.class);
                if(u.getNumber().equals(currentUser.getNumber())){
                    currentUser = u;
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

    public int findChatPositionById(int id){
        int length = chatListing.size();
        for(int i = 0; i < length; i++){
            if(chatListing.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    public void iniFloatingActionButtons() {
        optionFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!wereOptionsOpen){
                    chatFAB.setVisibility(View.VISIBLE);
                    chatFAB.setY(DEVICE_HEIGHT-DEVICE_HEIGHT/3);
                    optionFAB.setImageResource(R.drawable.ic_cancel);
                    optionFAB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorLightAmber));

                    wereOptionsOpen = true;
                }else{
                    chatFAB.setVisibility(View.INVISIBLE);
                    optionFAB.setSize(FloatingActionButton.SIZE_NORMAL);
                    optionFAB.setRippleColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                    optionFAB.setImageResource(R.drawable.ic_more);
                    wereOptionsOpen = false;
                }
            }
        });
        chatFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iniUserList = new Intent(getApplicationContext(),UsersListActivity.class);
                iniUserList.putExtra("current_user_route",currentUserRoute);
                startActivity(iniUserList);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_options, menu);
        return true;
    }

    public String findNumberByUser(int id){
        int size = userList.size();
        for(int i = 0; i < size; i++){
            if(userList.get(i).getId() == id){
                return userList.get(i).getNumber();
            }
        }
        return null;
    }

    public void iniChatlistListener(){
        chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                Intent optionActivity = new Intent(getApplicationContext(),OptionPaneActivity.class);
                int chatID = chatListing.get(pos).getId();
                optionActivity.putExtra("chatID",chatID);
                optionActivity.putExtra("userNumber",currentUser.getNumber());
                startActivity(optionActivity);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == INITCODE) {
            switch(resultCode){
                case MUTECODE:
                    return;
                case DELETECODE:
                    return;
            }
        }
    }

}
