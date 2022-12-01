package com.example.student_internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_login extends AppCompatActivity implements View.OnClickListener {
    private Button register, restpassword;
    private EditText mail,password;
    private Button login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        register = (Button) findViewById(R.id.button5);
        register.setOnClickListener(this);

        login = (Button) findViewById(R.id.button4);
        login.setOnClickListener(this);

        mail = (EditText) findViewById(R.id.editTextTextPersonName5);
        password = (EditText) findViewById(R.id.editTextTextPersonName6);

        restpassword = (Button) findViewById(R.id.button7);
        restpassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button5:
                startActivity(new Intent(this, activity_sigup.class));
                break;
            case R.id.button4:
                userLogin();
                break;
            case R.id.button7:
                startActivity(new Intent(this,Forgetpassword.class));
                break;
        }
    }

    private void userLogin() {
        String email = mail.getText().toString().trim();
        String Password =password.getText().toString().trim();

        if(email.isEmpty()) {
            mail.setError("Mail is required!");
            mail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mail.setError("invalid mail!");
            mail.requestFocus();
            return;
        }

        if (Password.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }

        if(Password.length() < 6){
            password.setError("Min Password length should be 6 characters");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(activity_login.this,compte1.class));

                }else{
                    Toast.makeText(activity_login.this, "Failed to register!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}