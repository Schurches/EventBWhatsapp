package com.company.dsii.whatsapp.AppActivities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.company.dsii.whatsapp.Adapters.UsersAdapter;
import com.company.dsii.whatsapp.Models.Chat;
import com.company.dsii.whatsapp.Models.User;
import com.company.dsii.whatsapp.R;
import com.company.dsii.whatsapp.Util.SparseIDs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UsersListActivity extends AppCompatActivity {

    private ListView usersList;
    private UsersAdapter userlistAdapter;
    private ArrayList<User> userListing = new ArrayList<>();
    private FloatingActionButton userOptionsFAB;
    private FloatingActionButton broadcastOptionsFAB;
    private FloatingActionButton addUserOptionsFAB;
    private FloatingActionButton addGroupOptionsFAB;
    //Variables
    private int DEVICE_HEIGHT;
    private int DEVICE_WIDTH;
    boolean wereOptionsOpen = false;
    private User currentUser;
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
        DEVICE_WIDTH = getResources().getDisplayMetrics().widthPixels;
        DEVICE_HEIGHT = getResources().getDisplayMetrics().heightPixels;
        Bundle data = getIntent().getExtras();
        String route = data.getString("current_user_route");
        iniCurrentUser(route);
    }

    public void iniCurrentUser(String route){
        DatabaseReference currentUserReference = db.getReference(route);
        currentUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentUser = dataSnapshot.getValue(User.class);
                iniDatabase();
                iniFloatingActionButtons();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void iniClickEvents(){
        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                boolean check = usersList.isItemChecked(i);
                usersList.setItemChecked(i,check);
            }
        });
    }

    public boolean isUserInContactsList(int ID, ArrayList<Integer> userIDs){
        int size = userIDs.size();
        for(int i = 0; i < size; i++){
            if(ID == userIDs.get(i)){
                return true;
            }
        }
        return false;
    }

    public void iniDatabase(){
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usersList = findViewById(R.id.userListView);
                userlistAdapter = new UsersAdapter(getApplicationContext(), userListing);
                usersList.setAdapter(userlistAdapter);
                usersList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                iniClickEvents();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        userReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User U = dataSnapshot.getValue(User.class);
                if(isUserInContactsList(U.getId(),currentUser.getContacts()) && U.getId() != currentUser.getId()){
                    userListing.add(U);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                User U = dataSnapshot.getValue(User.class);
                if(U.getId() == currentUser.getId()){
                    currentUser = U;
                }else{
                    if(isUserInContactsList(U.getId(),currentUser.getContacts()) && U.getId() != currentUser.getId()){
                        userListing.add(U);
                    }
                    int size = userListing.size();
                    for(int i = 0; i < size; i++){
                        if(userListing.get(i).getId() == U.getId()){
                            userListing.set(i,U);
                            userlistAdapter.notifyDataSetChanged();
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

    public User findUserById(int ID){
        int size = userListing.size();
        if(currentUser.getId() == ID){
            return currentUser;
        }
        for(int i = 0; i<size;i++){
            if(userListing.get(i).getId() == ID){
                return userListing.get(i);
            }
        }
        return null;
    }

    public void iniFloatingActionButtons(){
        userOptionsFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!wereOptionsOpen){
                    broadcastOptionsFAB.setVisibility(View.VISIBLE);
                    addUserOptionsFAB.setVisibility(View.VISIBLE);
                    addGroupOptionsFAB.setVisibility(View.VISIBLE);
                    broadcastOptionsFAB.setY(DEVICE_HEIGHT-DEVICE_HEIGHT/4-100);
                    addUserOptionsFAB.setY(DEVICE_HEIGHT-DEVICE_HEIGHT/4-300);
                    addGroupOptionsFAB.setY(DEVICE_HEIGHT-DEVICE_HEIGHT/4-500);
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
            }
        });
        broadcastOptionsFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iniUserList = new Intent(getApplicationContext(),BroadcastActivity.class);
                startActivity(iniUserList);
            }
        });
        addUserOptionsFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iniAddContact = new Intent(getApplicationContext(),AddContactActivity.class);
                iniAddContact.putExtra("current_user_number",currentUser.getNumber());
                iniAddContact.putExtra("current_user_id",currentUser.getId());
                iniAddContact.putExtra("current_user_contact_size",currentUser.getContacts().size());
                startActivity(iniAddContact);
            }
        });
        addGroupOptionsFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray selectedUsers = usersList.getCheckedItemPositions();
                int selectedSize = selectedUsers.size();
                if(selectedSize > 0){
                    int firstSelected = selectedUsers.keyAt(0);
                    String title = "";
                    ArrayList<Integer> chattingUserIDs = new ArrayList<>();
                    String nodeRoute = currentUser.getUsername()+",";
                    chattingUserIDs.add(currentUser.getId());
                    for(int i = 0; i < selectedSize; i++){
                        int position = selectedUsers.keyAt(i);
                        User u = userListing.get(position);
                        nodeRoute = nodeRoute + u.getUsername()+"-";
                        chattingUserIDs.add(u.getId());
                    }
                    if(selectedSize == 1){
                        title = userListing.get(firstSelected).getUsername();
                    }else{
                        title = "Group with "+selectedSize+" people";
                    }
                    int id = 0;
                    int membersSize = chattingUserIDs.size();
                    Chat C = new Chat(id,chattingUserIDs,title,"Comienza a chatear!");
                    for(int i = 0; i < membersSize; i++){
                        User u = findUserById(chattingUserIDs.get(i));
                        if(u == null){
                            getDifferentUserFromCurrent(currentUser.getNumber(),chattingUserIDs,id,i,C,nodeRoute,view);
                        }else{
                            getDifferentUserFromCurrent(u.getNumber(),chattingUserIDs,id,i,C,nodeRoute,view);
                        }
                    }
                }else{
                    Snackbar.make(view,"No has seleccionado contactos",Snackbar.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void getDifferentUserFromCurrent(String number, final ArrayList<Integer> groupMembers, final int chatID, final int step, final Chat C, final String route, final View view){
        final DatabaseReference otherUserReference = db.getReference("Users/"+number);
        otherUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User chatMember = dataSnapshot.getValue(User.class);
                chatMember = addMembersAsFriendsOfOtherUser(chatMember,groupMembers);
                chatMember.getChatIDs().add(chatID);
                otherUserReference.setValue(chatMember).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(step == groupMembers.size()-1){
                            DatabaseReference chatReference = db.getReference("Chats");
                            chatReference.child(route).setValue(C);
                            Snackbar.make(view,"Nuevo chat creado!",Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                            finish();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public User addMembersAsFriendsOfOtherUser(User u, ArrayList<Integer> groupMembers){
        int size = groupMembers.size();
        for(int i = 0; i < size; i++){
            if(groupMembers.get(i) != u.getId()){
                if(!isAlreadyAFriend(groupMembers.get(i),u.getContacts().size(),u.getContacts())){
                    u.getContacts().add(groupMembers.get(i));
                }
            }
        }
        return u;
    }

    public void addGroupchatIDForEachUserDifferentFromCurrent(User u, int chatID){
        u.getChatIDs().add(chatID);
    }

    public boolean isAlreadyAFriend(int ID, int size, ArrayList<Integer> friendsList){
        for(int i=0;i<size;i++){
            if(ID==friendsList.get(i)){
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_users, menu);
        return true;
    }


}
