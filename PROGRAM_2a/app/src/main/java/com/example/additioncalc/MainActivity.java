package com.example.additioncalc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText num1, num2;
    private TextView ans;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = (EditText) findViewById(R.id.editTextNumber);
        num2 = (EditText) findViewById(R.id.editTextNumber3);
        ans = (TextView) findViewById(R.id.textView2);
        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(v -> {
            float n1,n2;
            Object a;

            String mynum1 = num1.getText().toString().trim();
            String mynum2 = num2.getText().toString().trim();

            if(mynum1.isEmpty() || mynum2.isEmpty()){
                Toast.makeText(getApplicationContext(), "Value can't be empty", Toast.LENGTH_LONG).show();
                return;
            }

            n1 = Float.parseFloat(mynum1);
            n2 = Float.parseFloat(mynum2);

            a = n1 + n2;
            if((Float) a == ((Float) a).intValue()){
                a = (Integer)((Float) a).intValue();
            }
            ans.setText("Answer is: "+ a);
        });

    }
}

