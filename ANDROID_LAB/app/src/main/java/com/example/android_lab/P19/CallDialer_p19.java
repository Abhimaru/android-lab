package com.example.android_lab.P19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_lab.R;

public class CallDialer_p19 extends AppCompatActivity {
    protected EditText mno;
    protected Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_dialer_p19);
        mno = findViewById(R.id.mno_p19);
        btn = findViewById(R.id.btn_p19);
        btn.setOnClickListener(v -> {
            String no = mno.getText().toString();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + no));
            try
            {
                startActivity(intent);
            }
            catch (SecurityException s)
            {
                Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG).show();
            }
        });
    }
}