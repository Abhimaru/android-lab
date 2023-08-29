package com.example.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button showSelectedButton = findViewById(R.id.showSelectedButton_p7);
        final ListView listView = findViewById(R.id.listView_p7);
        final String[] technologies = getResources().getStringArray(R.array.p7_array_technologies);

        // Create an ArrayAdapter to populate the ListView
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, technologies);

        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        showSelectedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected items and append them to the toast message
                ArrayList<String> selectedItems = new ArrayList<>();
                for (int i = 0; i < listView.getCount(); i++) {
                    if (listView.isItemChecked(i)) {
                        selectedItems.add(technologies[i]);
                    }
                }

                // Display the selected items in a toast message
                if (!selectedItems.isEmpty()) {
                    StringBuilder message = new StringBuilder("Selected items: ");
                    for (String item : selectedItems) {
                        message.append(item).append(", ");
                    }
                    // Remove the trailing comma and space
                    message.delete(message.length() - 2, message.length());
                    Toast.makeText(MainActivity.this, message.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No items selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

