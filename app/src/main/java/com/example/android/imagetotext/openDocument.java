package com.example.android.imagetotext;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class openDocument extends AppCompatActivity {

    private FileSystem files;
    private File setFile;
    Spinner allFiles;
    ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_document);
        files = (FileSystem) getIntent().getSerializableExtra("fSystem");
        files.refresh();
        updateSpinner();
        allFiles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setFile = files.getFiles().get(allFiles.getSelectedItemPosition());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    public void updateSpinner()
    {
        allFiles = (Spinner) findViewById(R.id.spinner);
        names = new ArrayList<>();
        for(File name: files.getFiles())
        {names.add(name.getName());}
        ArrayAdapter <String> dropDown = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        dropDown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        allFiles.setAdapter(dropDown);
    }
    public void openFile(View v)
     {
            if(setFile == null || !setFile.exists()){
                Toast.makeText(this, "Unable to open file",Toast.LENGTH_SHORT).show();
                return;}
         Toast.makeText(this, "Opening file",Toast.LENGTH_SHORT).show();
            String file = files.OpenDoc(setFile);
            Intent i = new Intent(this, textEditor.class);
            i.putExtra("htmlFile", file);
            i.putExtra("fileName", setFile.getName().replace(".txt",""));
            i.putExtra("fSystem", files);
            Toast.makeText(this, "File opened",Toast.LENGTH_SHORT).show();
            startActivity(i);

     }

  public void deleteFile(View v)
    {
        if(setFile == null || !setFile.exists()){return;}
        if(files.RemoveFile(setFile))
        {
            names.remove(setFile.getName());
            files.refresh();
            Toast.makeText(this, "File Deleted",Toast.LENGTH_SHORT).show();
            updateSpinner();
        }
        else
        {Toast.makeText(this, "Unable to delete file",Toast.LENGTH_SHORT).show();}
    }

}
