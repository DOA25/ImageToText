package com.example.android.imagetotext;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class convertImage extends AppCompatActivity {

    Button convertImg;
    ImageView imgViewer;
    Button savePDF;
    final int CAM_REQUEST = 1;
    final int PICK_IMAGE_REQUEST =2;
    Uri mImageUri = null;
    File imgStore = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_image);
        convertImg = (Button) findViewById(R.id.conImg);
        imgViewer = (ImageView) findViewById(R.id.ImageView);

        convertImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                String s = getTextFromImg();
                Intent displayResult = new Intent(getBaseContext(), textEditor.class);
                displayResult.putExtra("result", s);
                displayResult.putExtra("fileName", getIntent().getExtras().getString("fileName"));
                displayResult.putExtra("fSystem", getIntent().getSerializableExtra("fSystem"));
                startActivity(displayResult);
            }
        }
        );
    }


    private Uri createFileURI(){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(System.currentTimeMillis());
        String fileName = "PHOTO_"+timeStamp+".jpg";
        return Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),fileName));
    }

    public void useCamera(View view)
    {
        Intent takePictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE );
        if(takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            mImageUri = FileProvider.getUriForFile(convertImage.this, BuildConfig.APPLICATION_ID + ".provider", new File(createFileURI().getPath()));
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
            this.startActivityForResult(takePictureIntent, CAM_REQUEST);
        }
    }

    public void getImage(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode ==PICK_IMAGE_REQUEST && resultCode == RESULT_OK)
        {
            mImageUri =  data.getData();
            Picasso.with(this).load(mImageUri).into(imgViewer);
            convertImg.setEnabled(true);
        }
            if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
                mImageUri = data.getData();
                Picasso.with(this).load(mImageUri).into(imgViewer);
                convertImg.setEnabled(true);
            }

    }

    public String getTextFromImg()
    {
        try{
            InputStream in = getContentResolver().openInputStream(mImageUri);
           Bitmap bitmap = BitmapFactory.decodeStream(in);
            TextRecognizer txtReg = new TextRecognizer.Builder(getApplicationContext()).build();

            if (!txtReg.isOperational()) {
                Toast.makeText(this, "Could not get text", Toast.LENGTH_SHORT);
            } else {
                Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                SparseArray<TextBlock> items = txtReg.detect(frame);
                StringBuilder sb = new StringBuilder();

                for(int i = 0; i<items.size(); i++)
                {
                    TextBlock myItems = items.valueAt(i);
                    sb.append(myItems.getValue());
                    sb.append(" ");
                }
                return sb.toString();

            }
        }
        catch (Exception e){}
        return "Fail";
    }

}
