package com.example.itemtotal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox chk1 = findViewById(R.id.checkBox1);
        CheckBox chk2 = findViewById(R.id.checkBox2);
        CheckBox chk3 = findViewById(R.id.checkBox3);
        CheckBox chk4 = findViewById(R.id.checkBox4);
        CheckBox[] checkBoxes = new CheckBox[]{chk1, chk2, chk3, chk4};
        Button btn = findViewById(R.id.btn_1);
        ArrayList<ItemDetails> items = new ArrayList<>();
        items.add(new ItemDetails("Pizza", 10));
        items.add(new ItemDetails("Burger", 5));
        items.add(new ItemDetails("Fries", 3));
        items.add(new ItemDetails("Soda", 2));
        for (int i = 0 ; i<items.size(); i++){
            checkBoxes[i].setText(items.get(i).getItemName() + " - " + items.get(i).getItemPrice());
        }

        btn.setOnClickListener(v -> {
            StringBuilder sb = new StringBuilder();
            double total = 0;
            if (chk1.isChecked()) {
                sb = sb.append(items.get(0).getItemName()).append(" - ").append(items.get(0).getItemPrice()).append("\n");
                total += items.get(0).getItemPrice();
            }
            if (chk2.isChecked()) {
                sb = sb.append(items.get(1).getItemName()).append(" - ").append(items.get(1).getItemPrice()).append("\n");
                total += items.get(1).getItemPrice();
            }
            if (chk3.isChecked()) {
                sb = sb.append(items.get(2).getItemName()).append(" - ").append(items.get(2).getItemPrice()).append("\n");
                total += items.get(2).getItemPrice();
            }
            if (chk4.isChecked()) {
                sb.append(items.get(3).getItemName()).append(" - ").append(items.get(3).getItemPrice()).append("\n");
                total += items.get(3).getItemPrice();
            }
            sb = sb.append("\nTotal: ").append(total);
                Toast.makeText(getApplicationContext(), sb , Toast.LENGTH_SHORT).show();
        }
        );
    }
}