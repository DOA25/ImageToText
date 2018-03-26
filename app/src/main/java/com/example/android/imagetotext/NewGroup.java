package com.example.android.imagetotext;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class NewGroup extends AppCompatActivity implements View.OnClickListener{

    private Button Submit;
    private EditText enterGroupName;
    private DatabaseReference mDatabaseRef;
    private DatabaseReference mUserRef;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private String UserID;
    private ArrayList<String> groupsAr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        progressDialog = new ProgressDialog(this);
        Submit = (Button) findViewById(R.id.Submit);
        Submit.setOnClickListener(this);
        enterGroupName = (EditText) findViewById(R.id.groupName);
        groupsAr = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mUserRef = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        UserID = user.getUid();
        mUserRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               showData(dataSnapshot);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );
    }

    private void showData(DataSnapshot datasnap) {
        ArrayList<String> groups = new ArrayList<>();

        for(DataSnapshot ds : datasnap.getChildren()){
            GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
            groups.add(ds.child(UserID).child("groups").getValue(String.class));
            Log.d("group", groups.toString());




        }
        int grCount = 0;
        for(int i = 0; i < groups.size(); i++){
            if(groups.get(i) != null){
                groupsAr.set(grCount, groups.get(i));
                grCount++;
            }
        }
        Log.d("groupAR", groupsAr.toString());
       // groupsAr = groups;


    }

    @Override
    public void onClick(View v) {
        if(v == Submit){
            createNewGroup();
        }
    }

    private void createNewGroup(){
        String groupname = enterGroupName.getText().toString().trim();
        if(TextUtils.isEmpty(groupname)){
            Toast.makeText(this, "Enter a group name!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Creating Group");
        progressDialog.show();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        ArrayList<String> users = new ArrayList<>();
        users.add(UserID);
        groupsAr.add(groupname);
        Log.d("groupAR2", groupsAr.toString());
        Log.d("groupAR", groupsAr.toString());
        mDatabaseRef.child("groups").child(groupname).child("users").setValue(users);   //add users arraylist
        mDatabaseRef.child("users").child(UserID).child("groups").setValue(groupsAr);   //add group arraylist

        finish();
        startActivity(new Intent(getApplicationContext(), MyGroups.class));

    }

}
