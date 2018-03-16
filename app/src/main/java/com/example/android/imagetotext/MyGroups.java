package com.example.android.imagetotext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyGroups extends AppCompatActivity implements View.OnClickListener{

    private Button Home;
    private Button MyDocuments;
    private Button UploadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_groups);

        Home = (Button) findViewById(R.id.Home);
        MyDocuments = (Button) findViewById(R.id.MyDocuments);
        UploadImage = (Button) findViewById(R.id.UploadImage);

        Home.setOnClickListener(this);
        MyDocuments.setOnClickListener(this);
        UploadImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==Home){
            finish();
            startActivity(new Intent(this, Home.class));
        }else if(v == MyDocuments){
            finish();
            startActivity(new Intent(this, MyDocuments.class));
        }else if(v == UploadImage){
            finish();
            startActivity(new Intent(this, UploadImage.class));
        }
    }
}
