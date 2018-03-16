package com.example.android.imagetotext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private Button MyDocuments;
    private Button UploadImage;
    private Button MyGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MyGroups = (Button) findViewById(R.id.MyGroups);
        UploadImage = (Button) findViewById(R.id.UploadImage);
        MyDocuments = (Button) findViewById(R.id.MyDocuments);

        MyGroups.setOnClickListener(this);
        UploadImage.setOnClickListener(this);
        MyDocuments.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==MyGroups){
            finish();
            startActivity(new Intent(this, MyGroups.class));
        }else if (v == UploadImage){
            finish();
            startActivity(new Intent(this, UploadImage.class));
        }else if (v==MyDocuments){
            finish();
            startActivity(new Intent(this, MyDocuments.class));
        }
    }
}
