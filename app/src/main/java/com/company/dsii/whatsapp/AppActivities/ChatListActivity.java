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

    ListView chatList;
    ChatAdapter chatListAdapter;

    final int INITCODE= 1000;
    final int MUTECODE = 1001;
    final int DELETECODE = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        Toolbar toolbar = findViewById(R.id.chats_toolbar_menu);
        setSupportActionBar(toolbar);

        ArrayList<Chat> asd = new ArrayList<>();
        asd.add(new Chat(1,1,1,new ArrayList<Integer>(),"Alo"));
        ArrayList<User> asd2 = new ArrayList<>();
        asd2.add(new User(1,"Bielos","Soy la verga",new ArrayList<Integer>(),new ArrayList<Integer>()));
        chatList = findViewById(R.id.chatList);
        chatListAdapter = new ChatAdapter(this, asd, asd2);
        chatList.setAdapter(chatListAdapter);
        iniChatlistListener();
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
                ((Activity) parent.getContext()).startActivityForResult(optionActivity,INITCODE);
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
