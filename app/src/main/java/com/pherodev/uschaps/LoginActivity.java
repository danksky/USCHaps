package com.pherodev.uschaps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity{

    private EditText editUsername, editPassword;
    private TextView register;
    private Button loginButton;
    private String email, password;
    private String appendEmail = "@usc.edu";
    private ProgressDialog progress;

    // test login
    private Button testLoginButton;

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
        register = (TextView) findViewById(R.id.login_register);

        testLoginButton = (Button) findViewById(R.id.login_test_login);
        // Initialize progress dialog
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Logging In...");
        progress.setCancelable(false);
    }

    private void addListeners(){
        // Set up login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        testLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testLogin();
            }
        });
    }

    private void authenticateUser(){
        // Grab email and password from edit text
        email = editUsername.getText().toString().trim() + appendEmail;
        password = editPassword.getText().toString();

        if (email.length() <= 0 || password.length() <= 0) {
            Toast.makeText(LoginActivity.this, "Please enter an username and/or password", Toast.LENGTH_SHORT).show();
            return;
        }
        progress.show();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // If successful
                if (task.isSuccessful()) {
                    progress.dismiss();
                    Intent intent = new Intent(LoginActivity.this, MainNavigationActivity.class);
                    startActivity(intent);
                }
                // If failed
                else {
                    progress.dismiss();
                    Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void testLogin(){
        // Grab email and password from edit text
        email = "testingaccount" + appendEmail;
        password = "aaaaaaa";
        progress.show();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // If successful
                if (task.isSuccessful()) {
                    progress.dismiss();
                    Intent intent = new Intent(LoginActivity.this, MainNavigationActivity.class);
                    startActivity(intent);
                }
                // If failed
                else {
                    progress.dismiss();
                    Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
