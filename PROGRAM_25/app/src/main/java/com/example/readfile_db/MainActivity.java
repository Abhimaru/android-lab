package com.example.readfile_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private static final int PERMISSION_REQUEST_CODE = 1;
    private EditText etName, etEmail, etPhone;
    private DBHelper db;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner_p25);
        etName = findViewById(R.id.etStudentName_p25);
        etEmail = findViewById(R.id.etStudentEmail_p25);
        etPhone = findViewById(R.id.etStudentPhone_p25);
        etName.setEnabled(false);
        etEmail.setEnabled(false);
        etPhone.setEnabled(false);
        Button saveBtn = findViewById(R.id.btnSave_p25);
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
        else{
           createAndSaveFile();
        }
        saveBtn.setOnClickListener(v -> readAndStoreFileDB());
    }

    public void setSpinner(){
        db = new DBHelper(this);
        ArrayList<StudentModel> studentList = db.fetchStudents();
        ArrayList<String> studentId = new ArrayList<>();
        for(StudentModel student : studentList){
            studentId.add(student.s_id);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, studentId);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
        String studentId = parent.getItemAtPosition(position).toString();
        StudentModel student = db.getStudent(studentId);
        etName.setText(student.name);
        etEmail.setText(student.email);
        etPhone.setText(student.phone);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    public void createAndSaveFile(){
        String fileText = "21CP315, Abhishek Maru, abhi@gmail.com, 1234567890\n" +
                "21CP301, Bhavya Soni, bhavya@gmail.com, 2390749248\n" +
                "21CP311, Utsav Kachhiya, utsav@gmail.com, 12307822320\n" +
                "20CP018, Vedant Dhamecha, vedant@gmail.com, 2934923982\n" +
                "21CP313, Jaydeep Baldaniya, jaydeep@gmail.com, 320972228\n" +
                "21CP314, Vivek Baldaniya, vivek@gmail.com, 2903723271\n";
        try{
            String FolderPath = getExternalFilesDir(null) + "/";
            File folder = new File(FolderPath);
            if(!folder.exists()) folder.mkdir();
            File file = new File(folder, "StudentData.txt");
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fileText.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "File Not Found!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error Saving File!", Toast.LENGTH_SHORT).show();
        }
        setSpinner();
    }

    public void readAndStoreFileDB(){
        String FolderPath = getExternalFilesDir(null) + "/";
        File folder = new File(FolderPath);
        if(!folder.exists()) folder.mkdir();
        File file = new File(folder, "StudentData.txt");
        try{
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            DBHelper db = new DBHelper(this);
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                String id = data[0];
                String name = data[1];
                String email = data[2];
                String phone = data[3];
                db.addStudentDetail(id, name, email, phone);
            }
            Toast.makeText(this, "Data Imported Successfully!", Toast.LENGTH_SHORT).show();
            setSpinner();
            db.close();
            br.close();
            isr.close();
            fis.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "File Not Found!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error Reading File!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull
    String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,
                grantResults);
        if(requestCode == PERMISSION_REQUEST_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                createAndSaveFile();
            }
            else{
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}