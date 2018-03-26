package com.example.android.imagetotext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyDocuments extends AppCompatActivity implements View.OnClickListener{

    private Button Home;
    private Button MyGroups;
    private Button UploadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_groups);

        MyGroups = (Button) findViewById(R.id.MyDocuments);
        UploadImage = (Button) findViewById(R.id.UploadImage);

        Home.setOnClickListener(this);
        MyGroups.setOnClickListener(this);
        UploadImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == MyGroups){
            startActivity(new Intent(this, MyGroups.class));
        }else if(v == UploadImage){
            startActivity(new Intent(this, UploadImage.class));
        }
    }
}
