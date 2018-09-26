package com.company.dsii.whatsapp.AppActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.company.dsii.whatsapp.Adapters.UsersAdapter;
import com.company.dsii.whatsapp.Models.User;
import com.company.dsii.whatsapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UsersListActivity extends AppCompatActivity {

    private ListView usersList;
    private UsersAdapter userlistAdapter;
    private ArrayList<User> userListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        Toolbar toolbar = findViewById(R.id.users_toolbar_menu);
        setSupportActionBar(toolbar);
        Bundle sharedData = getIntent().getExtras();
        if(sharedData.get("Users") == null){
            userListing = new ArrayList<>();
        }else{
            userListing = (ArrayList<User>) sharedData.get("Users");
        }
        usersList = findViewById(R.id.userListView);
        userlistAdapter = new UsersAdapter(this, userListing);
        usersList.setAdapter(userlistAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_users, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_new_broadcast:
                //Intent Option = new Intent(this,UsersListActivity.class);
                //startActivity(iniUserList);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
