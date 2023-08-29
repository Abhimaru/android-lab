package com.example.android_lab.P16;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.android_lab.R;

public class ImageSlider_p16b extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider_p16b);

        ViewPager viewPager = findViewById(R.id.viewPager_p16b);
        ImageAdapter_p16b adapter = new ImageAdapter_p16b(this);
        viewPager.setAdapter(adapter);
    }
}