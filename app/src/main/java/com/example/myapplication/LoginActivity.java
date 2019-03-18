package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText loginTextField;
    EditText passwordTextField;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        loginTextField = findViewById(R.id.LoginTextId);
        passwordTextField = findViewById(R.id.PasswordTextId);
        btnSubmit = findViewById(R.id.SubmitButtonId);

        configureButton();
    }

    private void configureButton() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, ItemsListActivity.class);

                String rawLogin = loginTextField.getText().toString();
                String rawPassword = passwordTextField.getText().toString();

                if (loginAndPasswordIsValid(rawLogin, rawPassword)) {
                    startActivity(intent);
                } else {
                    showToast("Invalid login or password");
                }
            }
        });
    }

    private Boolean loginAndPasswordIsValid(String login, String password) {
        return DataManager.getInstance().authentificationAnswer(login, password);
    }


    private void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}