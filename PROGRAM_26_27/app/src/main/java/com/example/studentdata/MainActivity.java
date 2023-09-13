package com.example.studentdata;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etFName, etLName, etEmail, etPhone, etAddress, etPinCode;
    RadioGroup gender;
    RadioButton rbMale, rbFemale;
    TextInputLayout tilFName, tilLName, tilEmail, tilPhone, tilAddress, tilPinCode;
    ImageButton btnPrev, btnNext;
    Button btnRegister, btnUpdate;
    Spinner spCity;
    DBHelper dbHelper;
    private int currentStudentIndex = 0;
    private ArrayList<StudentModal> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etFName = findViewById(R.id.etStudentFName_p26_27);
        etLName = findViewById(R.id.etStudentLName_p26_27);
        etEmail = findViewById(R.id.etStudentEmail_p26_27);
        etPhone = findViewById(R.id.etStudentPhone_p26_27);
        etAddress = findViewById(R.id.etStudentAddress_p26_27);
        etPinCode = findViewById(R.id.etStudentPinCode_p26_27);
        gender = findViewById(R.id.rgGender_p26_27);
        spCity = findViewById(R.id.spCity_p26_27);
        rbMale = findViewById(R.id.rbMale_p26_27);
        rbFemale = findViewById(R.id.rbFemale_p26_27);
        tilFName = findViewById(R.id.textInput_fName_p26_27);
        tilLName = findViewById(R.id.textInput_lName_p26_27);
        tilEmail = findViewById(R.id.textInput_email_p26_27);
        tilPhone = findViewById(R.id.textInput_phone_p26_27);
        tilAddress = findViewById(R.id.textInput_address_p26_27);
        tilPinCode = findViewById(R.id.textInput_pinCode_p26_27);
        btnPrev = findViewById(R.id.previous_p26_27);
        btnNext = findViewById(R.id.next_p26_27);
        btnRegister = findViewById(R.id.register_p26_27);
        btnUpdate = findViewById(R.id.update_p26_27);
        dbHelper = new DBHelper(this);
        etFName.addTextChangedListener(createTextWatcher(tilFName, etFName));
        etLName.addTextChangedListener(createTextWatcher(tilLName, etLName));
        etEmail.addTextChangedListener(createTextWatcher(tilEmail, etEmail));
        etPhone.addTextChangedListener(createTextWatcher(tilPhone, etPhone));
        etAddress.addTextChangedListener(createTextWatcher(tilAddress, etAddress));
        etPinCode.addTextChangedListener(createTextWatcher(tilPinCode, etPinCode));
        btnNext.setOnClickListener(v -> showNextStudent());

        btnPrev.setOnClickListener(v -> showPreviousStudent());

        btnRegister.setOnClickListener(v -> {
            try{
                if (isAllFieldsValid()) {
                    addStudentToDatabase();
                } else {
                    Toast.makeText(MainActivity.this, "Please fill in all fields correctly", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e){
                Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(v ->  {
            if (isAllFieldsValid()) {
                updateCurrentStudent();
            } else {
                Toast.makeText(MainActivity.this, "Please fill in all fields correctly", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private TextWatcher createTextWatcher(final TextInputLayout inputLayout, final EditText editText) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateField(inputLayout, editText);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    private void validateField(TextInputLayout inputLayout, EditText editText) {
        if (editText.getId() == R.id.etStudentFName_p26_27) {
            Validator.validateName(inputLayout, editText);
        } else if (editText.getId() == R.id.etStudentLName_p26_27) {
            Validator.validateName(inputLayout, editText);
        } else if (editText.getId() == R.id.etStudentEmail_p26_27) {
            Validator.validateEmail(inputLayout, editText);
        } else if (editText.getId() == R.id.etStudentPhone_p26_27) {
            Validator.validatePhone(inputLayout, editText);
        } else if (editText.getId() == R.id.etStudentPinCode_p26_27){
            Validator.validatePinCode(inputLayout, editText);
        } else if (editText.getId() == R.id.etStudentAddress_p26_27){
            Validator.validateAddress(inputLayout, editText);
        }
    }

    private boolean isAllFieldsValid() {
        return Validator.validateName(tilFName, etFName) &&
                Validator.validateName(tilLName, etLName) &&
                Validator.validateEmail(tilEmail, etEmail) &&
                Validator.validatePhone(tilPhone, etPhone) &&
                Validator.validatePinCode(tilPinCode, etPinCode) &&
                Validator.validateAddress(tilAddress, etAddress); // Add this line if you have a validateAddress method
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.option_menu_p26_27,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.add_student){
            makeReadableOnly(false, 1);
            clearForm();
        } else if (itemId == R.id.view_student) {
            makeReadableOnly(true, 2);
            loadStudentDataFromDatabase();
        } else if(itemId == R.id.update_student){
            makeReadableOnly(false, 3);
        }
        else if(itemId == R.id.search_student){
            Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(searchIntent);
        }
        else if(itemId == R.id.delete_student){
            deleteStudent();
        }
        return false;
    }

    public void makeReadableOnly(Boolean flag, int id){
        etFName.setEnabled(!flag);
        etLName.setEnabled(!flag);
        etEmail.setEnabled(!flag);
        etPhone.setEnabled(!flag);
        etAddress.setEnabled(!flag);
        etPinCode.setEnabled(!flag);
        gender.setEnabled(!flag);
        spCity.setEnabled(!flag);
        rbMale.setEnabled(!flag);
        rbFemale.setEnabled(!flag);
        if(id == 1){
            //insert
            btnPrev.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
            btnRegister.setVisibility(View.VISIBLE);
            findViewById(R.id.add_student).setBackgroundColor(getColor(R.color.lightBlue));
            findViewById(R.id.view_student).setBackgroundColor(getColor(R.color.transparent));
            findViewById(R.id.update_student).setVisibility(View.GONE);
            findViewById(R.id.delete_student).setVisibility(View.GONE);
        } else if (id == 2) {
            //view
            btnPrev.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
            findViewById(R.id.add_student).setBackgroundColor(getColor(R.color.transparent));
            findViewById(R.id.view_student).setBackgroundColor(getColor(R.color.lightBlue));
            findViewById(R.id.update_student).setBackgroundColor(getColor(R.color.transparent));
            findViewById(R.id.update_student).setVisibility(View.VISIBLE);
            findViewById(R.id.delete_student).setVisibility(View.VISIBLE);
        } else if (id == 3){
            findViewById(R.id.view_student).setBackgroundColor(getColor(R.color.transparent));
            findViewById(R.id.update_student).setBackgroundColor(getColor(R.color.lightBlue));
            btnUpdate.setVisibility(View.VISIBLE);
        }
    }

    public String getSelectedRadioBtnText(){
        RadioButton rb = findViewById(gender.getCheckedRadioButtonId());
        return rb.getText().toString();
    }
    public void addStudentToDatabase() {
        String fName = etFName.getText().toString();
        String lName = etLName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String gender = getSelectedRadioBtnText();
        String address = etAddress.getText().toString();
        String city = spCity.getSelectedItem().toString();
        String pinCode = etPinCode.getText().toString();

        if (fName.isEmpty() || lName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || pinCode.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            dbHelper.addDetail(fName, lName, email, phone, gender, address, city, pinCode);
            Toast.makeText(MainActivity.this, "Student details added to the database", Toast.LENGTH_SHORT).show();
            clearForm();
        }
    }
    public void loadStudentDataFromDatabase() {
        studentList = dbHelper.fetchDetails();
        currentStudentIndex = 0;
        if (studentList.size() > 0) {
            displayStudentData(studentList.get(currentStudentIndex));
            btnNext.setVisibility(View.VISIBLE);
            btnPrev.setVisibility(View.GONE);
        } else {
            btnNext.setVisibility(View.GONE);
            btnPrev.setVisibility(View.GONE);
        }
    }
    public void displayStudentData(StudentModal student) {
        etFName.setText(student.fname);
        etLName.setText(student.lname);
        etEmail.setText(student.email);
        etPhone.setText(student.phone);
        if (student.gender.equals("Male")) {
            rbMale.setChecked(true);
            rbFemale.setChecked(false);
        } else {
            rbMale.setChecked(false);
            rbFemale.setChecked(true);
        }
        etAddress.setText(student.address);
        spCity.setSelection(getCityIndex(student.city));
        etPinCode.setText(student.pincode);
    }
    public int getCityIndex(String city) {
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) spCity.getAdapter();
        return adapter.getPosition(city);
    }
    public void showNextStudent() {
        if (currentStudentIndex < studentList.size() - 1) {
            currentStudentIndex++;
            displayStudentData(studentList.get(currentStudentIndex));
            btnPrev.setVisibility(View.VISIBLE);
        } else {
            btnNext.setVisibility(View.GONE);
            btnPrev.setVisibility(View.VISIBLE);
        }
    }
    public void showPreviousStudent() {
        if (currentStudentIndex > 0) {
            currentStudentIndex--;
            displayStudentData(studentList.get(currentStudentIndex));
            // Make "Next" button visible again
            btnNext.setVisibility(View.VISIBLE);
        } else {
            btnPrev.setVisibility(View.GONE);
            btnNext.setVisibility(View.VISIBLE);
        }
    }
    private void updateCurrentStudent() {
        try {
            int currentStudentId = studentList.get(currentStudentIndex).id;
            String fName = etFName.getText().toString();
            String lName = etLName.getText().toString();
            String email = etEmail.getText().toString();
            String phone = etPhone.getText().toString();
            String gender = getSelectedRadioBtnText();
            String address = etAddress.getText().toString();
            String city = spCity.getSelectedItem().toString();
            String pinCode = etPinCode.getText().toString();

            if (fName.isEmpty() || lName.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || pinCode.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.updateStudent(currentStudentId, fName, lName, email, phone, gender, address, city, pinCode);
                Toast.makeText(MainActivity.this, "Student details updated in the database", Toast.LENGTH_SHORT).show();
                loadStudentDataFromDatabase();
                makeReadableOnly(true, 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Sorry Currently update is not possible...", Toast.LENGTH_SHORT).show();
        }
    }
    private void deleteStudent() {
        try {
            int currentStudentId = studentList.get(currentStudentIndex).id;
            dbHelper.deleteById(currentStudentId);
            studentList.remove(currentStudentIndex);

            if (!studentList.isEmpty()) {
                if (currentStudentIndex < studentList.size()) {
                    displayStudentData(studentList.get(currentStudentIndex));
                } else {
                    currentStudentIndex--;
                    displayStudentData(studentList.get(currentStudentIndex));
                }
            } else {
                btnNext.setVisibility(View.GONE);
                btnPrev.setVisibility(View.GONE);
                btnUpdate.setVisibility(View.GONE);
                btnRegister.setVisibility(View.VISIBLE);
                findViewById(R.id.add_student).setBackgroundColor(getColor(R.color.lightBlue));
                findViewById(R.id.view_student).setBackgroundColor(getColor(R.color.transparent));
                findViewById(R.id.update_student).setBackgroundColor(getColor(R.color.transparent));
                clearForm();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "No student is selected to delete", Toast.LENGTH_SHORT).show();
        }
    }
    public void clearForm(){
        etFName.setText("");
        etLName.setText("");
        etEmail.setText("");
        etPhone.setText("");
        etAddress.setText("");
        etPinCode.setText("");
    }
}