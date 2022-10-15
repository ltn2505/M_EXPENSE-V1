package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText name_input, des_input, date_input, desc_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_trip);
        des_input = findViewById(R.id.destination_trip);
        date_input = findViewById(R.id.trip_date);
        desc_input = findViewById(R.id.trip_description);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
//                myDB.addBook(title_input.getText().toString().trim(),
//                        author_input.getText().toString().trim(),
//                        Integer.valueOf(pages_input.getText().toString().trim()));
                Trip trip = new Trip();
                trip.setName(name_input.getText().toString().trim());
                trip.setDes(des_input.getText().toString().trim());
                trip.setDate(date_input.getText().toString().trim());
                trip.setDesc(desc_input.getText().toString().trim());
                long result = myDB.add(trip);
                if (result == -1) {
                    Toast.makeText(getBaseContext(), "Failed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Added Successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
