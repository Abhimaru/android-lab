package com.example.filesave;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity{
    private EditText etFileName, etFileText;
    private Spinner fileSpinner;
    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etFileName = findViewById(R.id.etFileName_p24);
        etFileText = findViewById(R.id.etFileText_p24);
        fileSpinner = findViewById(R.id.spinner_p24);
        Button saveBtn = findViewById(R.id.btnSave_p24);
        Button readBtn = findViewById(R.id.btnRead_p24);
        saveBtn.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
            else{
                if(saveFile())
                    Toast.makeText(this, "File Saved!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "File Not Saved!", Toast.LENGTH_SHORT).show();
            }
        });
        displayList();
        readBtn.setOnClickListener(v -> readFile());
    }

    public Boolean saveFile(){
        String fileName = etFileName.getText().toString() + ".txt";
        String fileText = etFileText.getText().toString();

        try{
            String FolderPath = getExternalFilesDir(null) + "/";
            File folder = new File(FolderPath);
            if(!folder.exists()) folder.mkdirs();
            File file = new File(folder, fileName);
            if (file.exists()) {
                // Generate a new unique filename based on the existing name
                int counter = 1;
                String baseFileName = fileName.substring(0, fileName.lastIndexOf('.'));
                String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
                while (true) {
                    String newFileName = baseFileName + "-" + counter + fileExtension;
                    file = new File(folder, newFileName);
                    if (!file.exists()) {
                        break;
                    }
                    counter++;
                }
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fileText.getBytes());
            etFileName.setText("");
            etFileText.setText("");
            fos.close();

            displayList();
            return Boolean.TRUE;
        }
        catch (Exception e){
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public void displayList(){
        String FolderPath = getExternalFilesDir(null) + "/";
        // get names of all files in this FolderPath directory folder
        File folder = new File(FolderPath);
        File[] files = folder.listFiles();
        if(files == null){
            Toast.makeText(this, "No Files Found!", Toast.LENGTH_SHORT).show();
            return;
        }
        String[] FILES = new String[files.length];
        for(int i = 0; i < files.length; i++){
            FILES[i] = files[i].getName();
        }
        // show the list of files in a spinner
        fileSpinner = findViewById(R.id.spinner_p24);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, FILES);
        fileSpinner.setAdapter(adapter);
    }

    public void readFile(){
        try{
            String fileName = fileSpinner.getSelectedItem().toString();
            String FolderPath = getExternalFilesDir(null) + "/";
            File file = new File(FolderPath, fileName);
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine()) != null){
                sb.append(line).append("\n");
            }
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
            br.close();
        }
        catch (Exception e){
            Toast.makeText(this, "File Not Found...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if(saveFile())
                    Toast.makeText(this, "File Saved!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "File Not Saved!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        displayList();
    }
}