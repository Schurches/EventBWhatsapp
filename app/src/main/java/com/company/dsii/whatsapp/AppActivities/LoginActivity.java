package com.company.dsii.whatsapp.AppActivities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class LoginActivity extends AppCompatActivity {

    private EditText name;
    private EditText num;
    private Button login;
    private Button register;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference userRef;
    DatabaseReference sparseReference;
    ArrayList<Boolean> isIDtaken = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SparseIDs.initValues(-100,100);
        sparseReference = db.getReference("Util");
        name = findViewById(R.id.username);
        num = findViewById(R.id.telNum);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButton);
        iniSparseArray();
    }

    public void iniSparseArray(){
        sparseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SparseIDs.initValues(isIDtaken);
                setClickListenerForButton();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        sparseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                isIDtaken.add(dataSnapshot.getValue(Boolean.class));
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

    public void checkForUserOnReference(final String route, final String username, final String number){
        userRef = db.getReference(route);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User U = dataSnapshot.getValue(User.class);
                if(U == null){
                    Toast.makeText(getApplicationContext(),"Usuario incorrecto", Toast.LENGTH_SHORT).show();
                }else{
                    if(U.getUsername().equals(username) && U.getNumber().equals(number)){
                        Intent startChatList = new Intent(getApplicationContext(),ChatListActivity.class);
                        startChatList.putExtra("current_user_route",route);
                        startActivity(startChatList);
                    }else{
                        Toast.makeText(getApplicationContext(),"Numero incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addNewUser(final String username, final String number){
        userRef = db.getReference("Users/"+number);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
                if(u != null){
                    Toast.makeText(getApplicationContext(),"Ya existe este usuario", Toast.LENGTH_SHORT).show();
                }else{
                    userRef.setValue(buildUser(username,number));
                    Toast.makeText(getApplicationContext(),"Agregado nuevo usuario!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public User buildUser(String username, String number){
        int ID = SparseIDs.findAnAvailableID();
        sparseReference.setValue(SparseIDs.getIsIDAvailable());
        ArrayList<Integer> chatIDs = new ArrayList<>();
        chatIDs.add(ID);
        ArrayList<Integer> contactsID = new ArrayList<>();
        contactsID.add(ID);
        ArrayList<Integer> messages = new ArrayList<>();
        messages.add(ID);
        HashMap<String, ArrayList<Integer>> chatToMessage = new HashMap<>();
        chatToMessage.put(ID+"",messages);
        return new User(ID,username,number,contactsID,chatIDs,chatToMessage);
    }


    public void setClickListenerForButton(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                String number = num.getText().toString();
                checkForUserOnReference("Users/"+number,username,number);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                String number = num.getText().toString();
                addNewUser(username,number);
            }
        });
    }
}
