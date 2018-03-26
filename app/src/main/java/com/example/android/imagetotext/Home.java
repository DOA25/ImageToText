package com.example.android.imagetotext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private Button createNote;
    private Button MyGroups;
    private Button open;
    private Button signOut;
    private File dir;
    private FileSystem files;
    public static Home instance = null;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        instance = this;
        MyGroups = (Button) findViewById(R.id.MyGroups);
        createNote = (Button) findViewById(R.id.MyDocuments);
        signOut = (Button) findViewById(R.id.SignOut);
        files = new FileSystem(Home.instance.getExternalFilesDir(null) + "/Documents/");
        open = (Button) findViewById(R.id.openDoc);
        MyGroups.setOnClickListener(this);
        createNote.setOnClickListener(this);
        signOut.setOnClickListener(this);
        open.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void onClick(View v) {
        if(v==MyGroups) {
            startActivity(new Intent(this, MyGroups.class));
        }
        else if (v==createNote) {
            Intent i = new Intent(this, createNote.class);
            i.putExtra("fSystem", files);
            startActivity(i);
        }
        else if (v== open)
        {
            startActivity(new Intent(this, openDocument.class).putExtra("fSystem", files));
        }
        else if (v == signOut)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Login.class));
        }
    }
}
