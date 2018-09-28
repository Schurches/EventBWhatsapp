package com.company.dsii.whatsapp.AppActivities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.company.dsii.whatsapp.Adapters.UsersAdapter;
import com.company.dsii.whatsapp.Models.User;
import com.company.dsii.whatsapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UsersListActivity extends AppCompatActivity {

    private ListView usersList;
    private UsersAdapter userlistAdapter;
    private ArrayList<User> userListing;
    private FloatingActionButton userOptionsFAB;
    private FloatingActionButton broadcastOptionsFAB;
    private FloatingActionButton addUserOptionsFAB;
    private FloatingActionButton addGroupOptionsFAB;
    //Variables
    private int DEVICE_HEIGHT;
    private int DEVICE_WIDTH;
    boolean wereOptionsOpen = false;
    //Database References
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference userReference = db.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        Toolbar toolbar = findViewById(R.id.users_toolbar_menu);
        userOptionsFAB = findViewById(R.id.userListOptions);
        broadcastOptionsFAB = findViewById(R.id.broadcastOption);
        addUserOptionsFAB = findViewById(R.id.userOption);
        addGroupOptionsFAB = findViewById(R.id.groupOption);
        setSupportActionBar(toolbar);
        Bundle sharedData = getIntent().getExtras();
        if(sharedData.get("Users") == null){
            userListing = new ArrayList<>();
        }else{
            userListing = (ArrayList<User>) sharedData.get("Users");
        }
        DEVICE_WIDTH = getResources().getDisplayMetrics().widthPixels;
        DEVICE_HEIGHT = getResources().getDisplayMetrics().heightPixels;




        iniFloatingActionButtons();
        usersList = findViewById(R.id.userListView);
        userlistAdapter = new UsersAdapter(this, userListing);
        usersList.setAdapter(userlistAdapter);
    }

    public void iniFloatingActionButtons(){
        userOptionsFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!wereOptionsOpen){
                    broadcastOptionsFAB.setVisibility(View.VISIBLE);
                    addUserOptionsFAB.setVisibility(View.VISIBLE);
                    addGroupOptionsFAB.setVisibility(View.VISIBLE);
                    broadcastOptionsFAB.setY(DEVICE_HEIGHT-DEVICE_HEIGHT/6);
                    addUserOptionsFAB.setY(DEVICE_HEIGHT-DEVICE_HEIGHT/4);
                    addGroupOptionsFAB.setY(DEVICE_HEIGHT-DEVICE_HEIGHT/2);
                    userOptionsFAB.setImageResource(R.drawable.ic_cancel);
                    userOptionsFAB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorLightAmber));
                    wereOptionsOpen = true;
                }else{
                    broadcastOptionsFAB.setVisibility(View.INVISIBLE);
                    addUserOptionsFAB.setVisibility(View.INVISIBLE);
                    addGroupOptionsFAB.setVisibility(View.INVISIBLE);
                    userOptionsFAB.setImageResource(R.drawable.ic_more);
                    userOptionsFAB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                    wereOptionsOpen = false;
                }
                //Snackbar.make(view, "Test", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        broadcastOptionsFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iniUserList = new Intent(getApplicationContext(),BroadcastActivity.class);
                //iniUserList.putExtra("Users",userList);
                //startActivity(iniUserList);
            }
        });
        addUserOptionsFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        addGroupOptionsFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_users, menu);
        return true;
    }


}
