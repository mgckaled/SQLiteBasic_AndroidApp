package com.mgckaled.sqlitebasicapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCountryActivity extends Activity implements View.OnClickListener {

    private EditText subjectEditText;
    private EditText descEditText;
    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Record");
        setContentView(R.layout.activity_add_country);

        // Instantiation of widgets:
        subjectEditText = findViewById(R.id.subject_editText);
        descEditText = findViewById(R.id.description_editText);
        //Widgets
        Button addTodoBtn = findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_record) {
            final String name = subjectEditText.getText().toString();
            final String desc = descEditText.getText().toString();

            dbManager.insert(name, desc);

            Intent main = new Intent(AddCountryActivity.this,
                    MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
        }
    }
}