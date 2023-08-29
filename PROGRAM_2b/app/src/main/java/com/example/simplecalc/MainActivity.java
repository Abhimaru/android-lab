package com.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText num1, num2;
    private TextView ans;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.number_1);
        num2 = findViewById(R.id.number_2);
        ans = findViewById(R.id.ans_text);
        Button btn1 = findViewById(R.id.add_button);
        Button btn2 = findViewById(R.id.sub_button);
        Button btn3 = findViewById(R.id.mul_button);
        Button btn4 = findViewById(R.id.div_button);

        btn1.setOnClickListener(view -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            if(checkEmpty()){
                return;
            }
            Object a = operation(btn1.getText().toString());
            ans.setText(getString(R.string.addition_text) + a);
        });

        btn2.setOnClickListener(view -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            if(checkEmpty()){
                return;
            }
            Object a = operation(btn2.getText().toString());
            ans.setText(getString(R.string.subtract_text) + a);
        });

        btn3.setOnClickListener(view -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            if(checkEmpty()){
                return;
            }
            Object a = operation(btn3.getText().toString());
            ans.setText(getString(R.string.multiplication_text) + a);
        });

        btn4.setOnClickListener(view -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            if(checkEmpty()){
                return;
            }
            Object a = operation(btn4.getText().toString());
            ans.setText(getString(R.string.division_text) + a);
        });
    }

    public boolean checkEmpty(){
        String myNum1 = num1.getText().toString().trim();
        String myNum2 = num2.getText().toString().trim();

        if(myNum1.isEmpty() || myNum2.isEmpty()){
            Toast.makeText(getApplicationContext(), "Value can't be empty", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }

    public Object operation(String op){
        float n1, n2;
        Object a;
        n1 = Float.parseFloat(num1.getText().toString().trim());
        n2 = Float.parseFloat(num2.getText().toString().trim());

        switch (op){
            case "+":
                a = n1 + n2;
                break;
            case "-":
                a = n1 - n2;
                break;
            case "*":
                a = n1 * n2;
                break;
            case "/":
                try {
                    if(n2 == 0){
                        throw new ArithmeticException();
                    }
                    a = n1 / n2;
                }
                catch (ArithmeticException e){
                    Toast.makeText(getApplicationContext(), "Can't divide by zero", Toast.LENGTH_LONG).show();
                    return "Error";
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + op);
        }
        if((Float) a == ((Float) a).intValue()){
            a = ((Float) a).intValue();
        }
        return a;
    }
}

