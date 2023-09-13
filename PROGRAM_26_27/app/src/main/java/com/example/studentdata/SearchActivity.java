package com.example.studentdata;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import com.example.studentdata.StudentAdapter;
import com.example.studentdata.StudentModal;
import com.example.studentdata.DBHelper;

public class SearchActivity extends AppCompatActivity {

    EditText etSearch;
    ListView lvSearchResults;
    DBHelper dbHelper;
    StudentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearch = findViewById(R.id.etSearchTxt_p26_27);
        lvSearchResults = findViewById(R.id.lvSearch_p26_27);
        dbHelper = new DBHelper(this);

        adapter = new StudentAdapter(this, new ArrayList<StudentModal>());
        lvSearchResults.setAdapter(adapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                String query = s.toString();
                fetchSearchResults(query);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void fetchSearchResults(String query) {
        // Query the database and update the ListView with search results
        ArrayList<StudentModal> searchResults = dbHelper.searchStudents(query);

        // Clear the previous results and add the new results
        adapter.clear();
        adapter.addAll(searchResults);

        // Show or hide the ListView based on whether there are results
        lvSearchResults.setVisibility(searchResults.isEmpty() ? View.GONE : View.VISIBLE);
    }
}
