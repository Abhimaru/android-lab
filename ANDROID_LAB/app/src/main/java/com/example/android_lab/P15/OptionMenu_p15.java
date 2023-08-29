package com.example.android_lab.P15;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.android_lab.R;

public class OptionMenu_p15 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu_p15);
        ActionBar a = getSupportActionBar();
        assert a != null;
        a.setTitle("Option Menu");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu_p15,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        Bitmap bg = Bitmap.createBitmap(1080, 1920, Bitmap.Config.ARGB_8888);
        ImageView i = (ImageView) findViewById(R.id.imgView_15);
        i.setBackground(new BitmapDrawable(bg));
        Canvas canvas = new Canvas(bg);
        Paint paint = new Paint();
        paint.setTextSize(100);
        if(itemId == R.id.circle_p15){
            paint.setColor(Color.BLUE);
            canvas.drawText("Circle", 420, 400, paint);
            canvas.drawCircle(540, 900, 400, paint);
        }
        else if(itemId == R.id.triangle_p15){
            paint.setColor(Color.RED);
            canvas.drawText("Triangle", 380, 400, paint);
            Path path = new Path();
            path.moveTo(200, 500);
            path.lineTo(900, 500);
            path.lineTo(550, 1300);
            path.close();
            canvas.drawPath(path, paint);
        }
        else if(itemId == R.id.square_p15){
            paint.setColor(Color.GREEN);
            canvas.drawText("Square", 400, 400, paint);
            canvas.drawRect(200, 500, 900, 1200, paint);
        }
        else if(itemId == R.id.rectangle_p15){
            canvas.drawText("Rectangle", 360, 400, paint);
            paint.setColor(Color.YELLOW);
            canvas.drawRect(300, 500, 830, 1400, paint);
        }
        else if(itemId == R.id.line_p15){
            paint.setColor(Color.MAGENTA);
            canvas.drawText("Line", 450, 400, paint);
            canvas.drawLine(200, 500, 900, 1200, paint);
        }
        return false;
    }
}