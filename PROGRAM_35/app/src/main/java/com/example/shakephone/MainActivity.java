package com.example.shakephone;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvHello;
    SensorManager sensorManager;
    Sensor accelerometer;
    Boolean isShaking = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHello = findViewById(R.id.tvHello_P35);
        
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (accelerometer != null) {
                sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
    
    }

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            double acceleration = Math.sqrt(x * x + y * y + z * z);

            if (acceleration > 12) { // Adjust this threshold as needed
                if (!isShaking) {
                    isShaking = true;
                    changeColors();
                }
            } else {
                isShaking = false;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Not needed for this example.
        }
    };

    private void changeColors() {
        int textColor = Color.rgb(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255),
                (int) (Math.random() * 255)
        );

        int bgColor = Color.rgb(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255),
                (int) (Math.random() * 255)
        );

        tvHello.setTextColor(textColor);
        tvHello.setBackgroundColor(bgColor);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(sensorEventListener);
        }
    }
}