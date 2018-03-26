package com.example.android.imagetotext;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class openDocument extends AppCompatActivity {

    private FileSystem files;
    private final int REQUEST_FILE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_document);
    }

    public void openFile(View v)
    {
        files = (FileSystem) getIntent().getSerializableExtra("fSystem");

        Intent findFile = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(files.getLocation()+"/");
        findFile.setDataAndType(uri, "*/*");
        startActivity(Intent.createChooser(findFile, "Open File"));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == REQUEST_FILE && requestCode == RESULT_OK)
        {
            String content = files.OpenDoc(new File(data.getData().getPath()));

            if(content == null)
            {
                Toast.makeText(getBaseContext(), "Unable to open file", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent i = new Intent (this, textEditor.class);
            i.putExtra("result", content);
            i.putExtra("fSystem", files);
        }
    }

}
