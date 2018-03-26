package com.example.android.imagetotext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyGroups extends AppCompatActivity implements View.OnClickListener{

    private Button MyDocuments;
    private Button UploadImage;
    private Button CreateGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_groups);

        MyDocuments = (Button) findViewById(R.id.MyDocuments);
        UploadImage = (Button) findViewById(R.id.UploadImage);
        CreateGroup = (Button) findViewById(R.id.CreateGroup);


        MyDocuments.setOnClickListener(this);
        UploadImage.setOnClickListener(this);
        CreateGroup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == MyDocuments){
            startActivity(new Intent(this, MyDocuments.class));
        }else if(v == UploadImage){
            startActivity(new Intent(this, UploadImage.class));
        }
        else if(v == CreateGroup){
            startActivity(new Intent(this, NewGroup.class));
        }

    }
}
