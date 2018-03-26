package com.example.android.imagetotext;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jkcarino.rtexteditorview.RTextEditorToolbar;
import com.jkcarino.rtexteditorview.RTextEditorView;

import java.io.File;



public class textEditor extends AppCompatActivity {

    RTextEditorView edit;
    RTextEditorToolbar toolbar = null;
    File file;
    FileSystem files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_editor);
        edit = (RTextEditorView) findViewById(R.id.editor_view);
        edit.setIncognitoModeEnabled(true);
        files = (FileSystem) getIntent().getSerializableExtra("fSystem");
        toolbar = (RTextEditorToolbar) findViewById(R.id.editor_toolbar);
        toolbar.setEditorView(edit);




        if(getIntent().getExtras().getString("result") != null)
        {
            final String [] result = getIntent().getExtras().getString("result").trim().split("\\r\\n|\\r|\\n");

            for(int i = 0; i < result.length; i++) {
                edit.insertText(result[i]);
            }

        }
    }

    public void saveFile(View v)
    {
        String html = edit.getHtml();
        Intent i = getIntent();
        if( files.saveFile(html, i.getExtras().getString("fileName")))
        {
            Toast.makeText(v.getContext(), "File saved", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(v.getContext(), "Unable to save File", Toast.LENGTH_SHORT).show();
        }
    }
}
