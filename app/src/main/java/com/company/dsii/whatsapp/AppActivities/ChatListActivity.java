package com.company.dsii.whatsapp.AppActivities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.company.dsii.whatsapp.Adapters.ChatAdapter;
import com.company.dsii.whatsapp.Models.Chat;
import com.company.dsii.whatsapp.Models.Message;
import com.company.dsii.whatsapp.Models.User;
import com.company.dsii.whatsapp.R;

import java.util.ArrayList;

public class ChatListActivity extends AppCompatActivity {

    private ListView chatList;
    private ChatAdapter chatListAdapter;
    private final int INITCODE= 1000;
    private final int MUTECODE = 1001;
    private final int DELETECODE = 1002;
    private final ArrayList<Chat> chatListing = new ArrayList<>();
    private final ArrayList<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        Toolbar toolbar = findViewById(R.id.chats_toolbar_menu);
        setSupportActionBar(toolbar);
        chatList = findViewById(R.id.chatList);
        iniChatListInformation();
        iniUserListInformation();
        chatListAdapter = new ChatAdapter(this, chatListing, userList);
        chatList.setAdapter(chatListAdapter);
        iniChatlistListener();
    }


    public void iniUserListInformation(){
        ArrayList<Integer> friendsList = new ArrayList<>();
        ArrayList<Integer> friendChatList = new ArrayList<>();
        friendChatList.add(6);
        friendChatList.add(2);
        friendChatList.add(1);
        friendChatList.add(5);
        friendsList.add(3);
        friendsList.add(6);
        friendsList.add(1);
        friendsList.add(7);
        friendsList.add(2);
        userList.add(new User(2,"Bielos","Soy la verga",friendsList,friendChatList));
    }

    public void iniChatListInformation(){
        ArrayList<Integer> messagesList = new ArrayList<>();
        messagesList.add(3);
        messagesList.add(4);
        messagesList.add(5);
        messagesList.add(6);
        chatListing.add(new Chat(1,2,1,messagesList,"Alo"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_new_chat:
                Intent iniUserList = new Intent(this,UsersListActivity.class);
                iniUserList.putExtra("Users",userList);
                startActivity(iniUserList);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void iniChatlistListener(){
        chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                Intent optionActivity = new Intent(getApplicationContext(),OptionPaneActivity.class);
                startActivity(optionActivity);
                //((Activity) parent.getContext()).startActivity(optionActivity);
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
