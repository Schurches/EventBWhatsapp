package com.company.dsii.whatsapp.AppActivities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.company.dsii.whatsapp.Models.User;
import com.company.dsii.whatsapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddContactActivity extends AppCompatActivity {

    private EditText phoneNumber;
    private Button addUser;
    private DatabaseReference usersReference;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private String currentUserNumber;
    private int currentUserID;
    private int currentUserContactSize;
    private String currentContactNumber;
    private Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        phoneNumber = findViewById(R.id.addUserNumber);
        addUser = findViewById(R.id.addUserButton);
        data = getIntent().getExtras();

        currentUserNumber = data.getString("current_user_number","");
        currentUserID = data.getInt("current_user_id");
        currentUserContactSize = data.getInt("current_user_contact_size",0);
        Log.d("asdasdasdasdasd", "onCreate: "+currentContactNumber);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                currentContactNumber = phoneNumber.getText().toString();
                usersReference = db.getReference("Users/"+currentContactNumber);
                usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User U = dataSnapshot.getValue(User.class);
                        if(U == null){
                            Snackbar.make(view,"Este usuario no existe",Snackbar.LENGTH_SHORT).show();
                        }else{
                            linkContactBetweenUsers("Users/"+currentUserNumber,"Users/"+currentContactNumber,U.getContacts().size(),U.getId());
                            Snackbar.make(view,"Nuevo usuario agregado",Snackbar.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void linkContactBetweenUsers(String route1, String route2, int contactSize2, int userID){
        usersReference = FirebaseDatabase.getInstance().getReference(route1).child("contacts");
        usersReference.child(currentUserContactSize+"").setValue(userID);
        usersReference = FirebaseDatabase.getInstance().getReference(route2).child("contacts");
        usersReference.child(contactSize2+"").setValue(currentUserID);
    }


}
