package com.pherodev.uschaps;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    private EditText editEmail, editPassword, editConfirmPassword;
    private String email, password, confirm;
    private String atUSC = "@usc.edu";
    private Button registerButton;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        instantiateComponents();
        addListeners();
    }

    private void instantiateComponents(){
        editEmail = (EditText) findViewById(R.id.registration_name);
        editPassword = (EditText) findViewById(R.id.registration_password);
        editConfirmPassword = (EditText) findViewById(R.id.registration_confirm);
        registerButton = (Button) findViewById(R.id.registration_register_button);

        // Initialize progress dialog
        progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Creating Account...");
        progress.setCancelable(false);
    }

    private void addListeners(){
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Values from editText
                email = editEmail.getText().toString().trim();
                password = editPassword.getText().toString();
                confirm = editConfirmPassword.getText().toString();

                // Check Email
                if (email.length() <= 7 && !email.toLowerCase().contains(atUSC))
                {
                    Toast.makeText(RegistrationActivity.this, "Invalid Email.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if password is empty
                if (password.length() <= 0)
                {
                    Toast.makeText(RegistrationActivity.this, "Please enter a password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if Passwords Match
                if (!password.equals(confirm))
                {
                    Toast.makeText(RegistrationActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    return;
                }

                progress.show();

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If successful
                        if (task.isSuccessful()) {
                            progress.dismiss();
                            Toast.makeText(RegistrationActivity.this, "Account successfully created!", Toast.LENGTH_SHORT).show();
                        }
                        // If failed
                        else {
                            progress.dismiss();
                            Toast.makeText(RegistrationActivity.this, "Failed to create account", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });
    }



}




