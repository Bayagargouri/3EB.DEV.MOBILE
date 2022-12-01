package com.example.student_internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.firebase.database.FirebaseDatabase;

public class activity_sigup extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private Button register;
    private EditText name, lastname, mail, password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup);

        mAuth = FirebaseAuth.getInstance();

        register = (Button) findViewById(R.id.button);
        register.setOnClickListener(this);

        name =(EditText) findViewById(R.id.namesign);
        lastname =(EditText) findViewById(R.id.editTextTextPersonName2);
        mail = (EditText) findViewById(R.id.mail);
        password =(EditText) findViewById(R.id.password);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                register();
                break;



        }
    }

    private void register() {
        String Name = name.getText().toString().trim();
        String Lastname = lastname.getText().toString().trim();
        String Mail = mail.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if(Name.isEmpty()) {
            name.setError("Name is required!");
            name.requestFocus();
            return;
        }

        if(Lastname.isEmpty()) {
            lastname.setError("Last name is required!");
            lastname.requestFocus();
            return;

        }
        if(Mail.isEmpty()) {
            mail.setError("Mail is required!");
            mail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Mail).matches()){
            mail.setError("Please provide valid mail!");
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

        mAuth.createUserWithEmailAndPassword(Mail,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user= new User(Name, Lastname, Mail);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(activity_sigup.this,"User has been register successfully",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(activity_sigup.this, activity_login.class));
                                    }else{
                                        Toast.makeText(activity_sigup.this, "Failed to register!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                    }
            }


        });



    }
}