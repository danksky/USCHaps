package com.pherodev.uschaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity{

    private EditText editUsername, editPassword;
    private Button loginButton;
    private String appendEmail = "@usc.edu";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        instantiateComponents();
        addListeners();
    }

    private void instantiateComponents(){
        editUsername = (EditText) findViewById(R.id.login_username_input);
        editPassword = (EditText) findViewById(R.id.login_password_input);
        loginButton = (Button) findViewById(R.id.login_button);
    }

    private void addListeners(){
        // Set up login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });
    }

    private void authenticateUser(){
        // Grab email and password from edit text
        String email = editUsername.getText().toString().trim() + appendEmail;
        String password = editPassword.getText().toString();

        System.out.println(email + " " + password);
        // TODO: add firebase stuff here

        Intent intent = new Intent(LoginActivity.this, MainNavigationActivity.class);
        startActivity(intent);
    }
}
