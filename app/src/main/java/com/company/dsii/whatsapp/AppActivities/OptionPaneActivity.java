package com.company.dsii.whatsapp.AppActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.company.dsii.whatsapp.R;

public class OptionPaneActivity extends AppCompatActivity {

    LinearLayout startChat;
    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_pane);
        startChat = findViewById(R.id.startChat);
        data = getIntent().getExtras();
        startChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"alo",Toast.LENGTH_SHORT).show();
                Intent chatroomActivity = new Intent(getApplicationContext(),ChatRoomActivity.class);
                chatroomActivity.putExtra("chatID",data.getInt("chatID"));
                chatroomActivity.putExtra("userNumber",data.getString("userNumber"));
                startActivity(chatroomActivity);
            }
        });
    }
}
