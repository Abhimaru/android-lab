package com.example.android_lab.P2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_lab.R;

public class Addition_p2a extends AppCompatActivity {

    private EditText num1, num2;
    private TextView ans;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_p2a);

        num1 = findViewById(R.id.editTextNumber);
        num2 = findViewById(R.id.editTextNumber3);
        ans = findViewById(R.id.textView2);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(v -> {
            float n1,n2;
            Object a;

            String myNum1 = num1.getText().toString().trim();
            String myNum2 = num2.getText().toString().trim();

            if(myNum1.isEmpty() || myNum2.isEmpty()){
                Toast.makeText(getApplicationContext(), "Value can't be empty", Toast.LENGTH_LONG).show();
                return;
            }

            n1 = Float.parseFloat(myNum1);
            n2 = Float.parseFloat(myNum2);

            a = n1 + n2;
            if((Float) a == ((Float) a).intValue()){
                a = ((Float) a).intValue();
            }
            ans.setText("Answer is: "+ a);
        });
    }
}