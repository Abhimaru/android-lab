package com.example.imageswitcher;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ImageSwitcher imageSwitcher;
    Button btnNext, btnPrevious;
    TextView tv;

    int[] images = {R.drawable.c, R.drawable.python, R.drawable.youtube, R.drawable.vscode, R.drawable.java};
    int imgLength = images.length;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv_p16a);
        imageSwitcher = findViewById(R.id.imgSwitcher_p16a);
        btnNext = findViewById(R.id.nextBtn_p16a);
        btnPrevious = findViewById(R.id.prevBtn_p16a);
        imageSwitcher.setFactory(() -> {
            ImageView imgView = new ImageView(getApplicationContext());
            imgView.setLayoutParams(new ImageSwitcher.LayoutParams(1080, 1920));
            imgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imgView.setImageResource(images[counter]);
            setValues(counter);
            return imgView;
        });

        Animation animation_out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        Animation animation_in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);

        imageSwitcher.setOutAnimation(animation_out);
        imageSwitcher.setInAnimation(animation_in);

        btnNext.setOnClickListener(v -> {
            counter++;
            if(counter == imgLength){
                counter = 0;
            }
           setValues(counter);
            imageSwitcher.setImageResource(images[counter]);
        });

        btnPrevious.setOnClickListener(v -> {
            counter--;
            if(counter == -1){
                counter = imgLength - 1;
            }
            setValues(counter);
            imageSwitcher.setImageResource(images[counter]);
        });
    }

    @SuppressLint("SetTextI18n")
    private void setValues(int i) {
        tv.setText("Image " + (i + 1) + " of " + imgLength);
    }
}