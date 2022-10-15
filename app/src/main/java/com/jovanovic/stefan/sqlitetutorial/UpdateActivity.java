package com.jovanovic.stefan.sqlitetutorial;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, des_input, date_input, desc_input;
    Button update_button, delete_button;

    Trip selectedTrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        des_input = findViewById(R.id.des_input2);
        date_input = findViewById(R.id.date_input2);
        desc_input = findViewById(R.id.desc_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndDisplayInfo();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
//                title = title_input.getText().toString().trim();
//                author = author_input.getText().toString().trim();
//                pages = pages_input.getText().toString().trim();
//                myDB.updateData(id, title, author, pages);


                selectedTrip.setName(name_input.getText().toString().trim());
                selectedTrip.setDes(des_input.getText().toString().trim());
                selectedTrip.setDate(date_input.getText().toString().trim());
                selectedTrip.setDesc(desc_input.getText().toString().trim());
                long result = myDB.update(selectedTrip);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAction();
            }
        });

    }

    void getAndDisplayInfo() {
        Intent intent = getIntent();
        selectedTrip = (Trip) intent.getSerializableExtra("selectedTrip");

        //display in textview
        name_input.setText(selectedTrip.getName());
        des_input.setText(selectedTrip.getDes());
        date_input.setText(selectedTrip.getDate());
        desc_input.setText(selectedTrip.getDesc());

        //display in action bar
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(selectedTrip.getName());
        }
    }

    void deleteAction() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + selectedTrip.getName() + " ?");
        builder.setMessage("Are you sure you want to delete " + selectedTrip.getName() + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                long result = myDB.delete(String.valueOf(selectedTrip.getId()));
                if (result == -1) {
                    Toast.makeText(getBaseContext(), "Failed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Delete Successfully!", Toast.LENGTH_SHORT).show();
                }
                onBackPressed();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
