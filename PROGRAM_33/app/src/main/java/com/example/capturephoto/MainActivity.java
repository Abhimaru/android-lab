package com.example.capturephoto;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    ImageView img;
    Button btnCapture, btnSave;
    private Bitmap capturedBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCapture = findViewById(R.id.btnCapture_p33);
        img = findViewById(R.id.capturedImage_p33);
        btnCapture.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_REQUEST);
        });
        btnSave = findViewById(R.id.btnSave_p33);
        btnSave.setOnClickListener(v -> {
            if(capturedBitmap != null){
                try {
                    saveImageToDCIM();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                Toast.makeText(this, "No image to save.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            assert data != null;
            Bitmap photo = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            img.setVisibility(ImageView.VISIBLE);
            img.setImageBitmap(photo);
            btnSave.setVisibility(Button.VISIBLE);
            capturedBitmap = photo;
        }
    }

    private void saveImageToDCIM() throws IOException {
        ContentResolver resolver = getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "image_" + System.currentTimeMillis() + ".jpg");
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DCIM);

        Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        if (imageUri == null) {
            throw new IOException("Failed to create new MediaStore record.");
        }

        OutputStream fos = resolver.openOutputStream(imageUri);
        if (!capturedBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)) {
            throw new IOException("Failed to save bitmap.");
        }

        assert fos != null;
        fos.flush();
        fos.close();
        Toast.makeText(this, "Image saved successfully.", Toast.LENGTH_SHORT).show();
    }
}