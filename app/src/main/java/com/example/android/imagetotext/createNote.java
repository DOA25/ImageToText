package com.example.android.imagetotext;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class createNote extends AppCompatActivity implements View.OnClickListener {

    EditText input;
    private Button convert;
    private Button blank;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        convert = (Button) findViewById(R.id.conImg);
        blank = (Button) findViewById(R.id.BlankNote);

        convert.setOnClickListener(this);
        blank.setOnClickListener(this);
    }

    public void convertImage ()
    {

        Intent a = new Intent(getBaseContext(), convertImage.class);
        FileSystem path = (FileSystem) getIntent().getSerializableExtra("fSystem");
        a.putExtra("fileName", input.getText().toString());
        a.putExtra("fSystem", path);
        startActivity(a);


    }

    String s;
    public void getName(final int num)
    {

        final AlertDialog.Builder name = new AlertDialog.Builder(this);
        name.setTitle("New note");
        name.setMessage("Name of file");
        input = new EditText(this);
        name.setView(input);
        name.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                s = input.getText().toString();

                switch(num)
                {
                    case 1:
                        createBlank();
                        break;
                    case 2:
                        convertImage();
                        break;
                    default:
                        break;
                }
            }
        });
        name.setNegativeButton("Go back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        name.show();
    }
    public void onClick(View v)
    {
        if(v == convert)
        {
            getName(2);
        }
        else if (v == blank)
        {
            getName(1);
        }
    }

    public void createBlank()
    {


             Intent a = new Intent(getBaseContext(), textEditor.class);
             FileSystem path = (FileSystem) getIntent().getSerializableExtra("fSystem");
             a.putExtra("fileName", input.getText().toString());
             a.putExtra("fSystem", path);
             finish();
             startActivity(a);

    }
}
