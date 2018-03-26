package com.example.android.imagetotext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class displayResults extends AppCompatActivity {
    private TextView results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);
        results = (TextView) findViewById(R.id.textView);
        Intent i = getIntent();
        results.setText(i.getExtras().getString("result"));

    }
}
