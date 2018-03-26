package com.example.android.imagetotext;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import java.io.Serializable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.net.URI;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private Button createNote;
    private Button MyGroups;
    private Button open;
    private File dir;
    private FileSystem files;
    public static Home instance = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        instance = this;
        MyGroups = (Button) findViewById(R.id.MyGroups);
        createNote = (Button) findViewById(R.id.MyDocuments);
        files = new FileSystem(Home.instance.getExternalFilesDir(null) + "/Documents/");
        open = (Button) findViewById(R.id.openDoc);
        MyGroups.setOnClickListener(this);
        createNote.setOnClickListener(this);
        open.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v==MyGroups) {
            finish();
            startActivity(new Intent(this, MyGroups.class));
        }
        else if (v==createNote) {
            finish();
            Intent i = new Intent(this, createNote.class);
            i.putExtra("fSystem", files);
            startActivity(i);
        }
        else if (v== open)
        {

            startActivity(new Intent(this, openDocument.class).putExtra("fSystem", files));
        }
    }
}
