package com.example.studentdata;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.regex.Pattern;

public class Validator {

    public static boolean validateName(TextInputLayout inputLayout, EditText editText) {
        String name = editText.getText().toString().trim();
        if (name.isEmpty()) {
            inputLayout.setError("required Field");
            return false;
        } else if (!isValidName(name)) {
            inputLayout.setError("Number, Special Symbol or Space not allowed");
            return false;
        } else {
            inputLayout.setError(null);
            inputLayout.setHelperText("OK");
            inputLayout.setHelperTextColor(ColorStateList.valueOf(Color.GREEN));
            return true;
        }
    }

    public static boolean validateEmail(TextInputLayout inputLayout, EditText editText) {
        String email = editText.getText().toString().trim();
        if (email.isEmpty()) {
            inputLayout.setError("Email is required");
            return false;
        } else if (!isValidEmail(email)) {
            inputLayout.setError("Please Enter Valid Email");
            return false;
        } else {
            inputLayout.setError(null);
            inputLayout.setHelperText("OK");
            inputLayout.setHelperTextColor(ColorStateList.valueOf(Color.GREEN));
            return true;
        }
    }

    public static boolean validatePhone(TextInputLayout inputLayout, EditText editText) {
        String phone = editText.getText().toString().trim();
        if (phone.isEmpty()) {
            inputLayout.setError("Phone number is required");
            return false;
        } else if (!isValidPhone(phone)) {
            inputLayout.setError("Phone number should contain 10 digit");
            return false;
        } else {
            inputLayout.setError(null);
            inputLayout.setHelperText("OK");
            inputLayout.setHelperTextColor(ColorStateList.valueOf(Color.GREEN));
            return true;
        }
    }

    public static boolean validatePinCode(TextInputLayout inputLayout, EditText editText) {
        String pinCode = editText.getText().toString().trim();
        if (pinCode.isEmpty()) {
            inputLayout.setError("Pin code is required");
            return false;
        } else if (!isValidPinCode(pinCode)) {
            inputLayout.setError("Pincode should contain 6 digit");
            return false;
        } else {
            inputLayout.setError(null);
            inputLayout.setHelperText("OK");
            inputLayout.setHelperTextColor(ColorStateList.valueOf(Color.GREEN));
            return true;
        }
    }

    public static boolean validateAddress(TextInputLayout inputLayout, EditText editText) {
        String pinCode = editText.getText().toString().trim();
        if (pinCode.isEmpty()) {
            inputLayout.setError("Address is required");
            return false;
        } else {
            inputLayout.setError(null);
            inputLayout.setHelperText("OK");
            inputLayout.setHelperTextColor(ColorStateList.valueOf(Color.GREEN));
            return true;
        }
    }

    private static boolean isValidEmail(String email) {
        // Implement your email validation logic (e.g., using a regular expression)
        // Example regex for a basic email format:
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    private static boolean isValidName(String name){
        String nameRegex = "^[A-Za-z'-]+$";
        return Pattern.compile(nameRegex).matcher(name).matches();
    }

    private static boolean isValidPhone(String phone) {
        // Implement your phone number validation logic (e.g., using a regular expression)
        // Example regex for a basic phone number format:
        String phoneRegex = "^[0-9]{10}$"; // Assumes a 10-digit phone number
        return Pattern.compile(phoneRegex).matcher(phone).matches();
    }

    private static boolean isValidPinCode(String pinCode){
        String pinCodeRegex = "^[0-9]{6}$";
        return Pattern.compile(pinCodeRegex).matcher(pinCode).matches();
    }
}
