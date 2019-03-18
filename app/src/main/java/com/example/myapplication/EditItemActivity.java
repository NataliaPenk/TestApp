package com.example.myapplication;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class EditItemActivity extends AppCompatActivity {

    private EditText itemField;
    private Button saveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item_activity);

        itemField = findViewById(R.id.EditTextId);
        saveButton = findViewById(R.id.SaveButtonId);

        configureActivity();
    }

    private void configureActivity() {
        String text = Objects.requireNonNull(getIntent().getExtras()).getString("data");
        itemField.setText(text);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemToSave = itemField.getText().toString();
                saveItem(itemToSave);
                onBackPressed();
            }
        });
    }

    private void saveItem(String item) {
        int id = Objects.requireNonNull(getIntent().getExtras()).getInt("id");
        DataManager.getInstance().saveItem(id, item);
    }


}