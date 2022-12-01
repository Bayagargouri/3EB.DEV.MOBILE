package com.example.student_internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class Forgetpassword extends AppCompatActivity implements View.OnClickListener {

    private EditText mail;
    private Button forget;
    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        mail = (EditText) findViewById(R.id.textmail);
        forget = (Button) findViewById(R.id.button8);

        auth = FirebaseAuth.getInstance();
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpassword();

            }
        });
    }

   private void resetpassword(){
           String email= mail.getText().toString().trim();
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
           auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
               @Override
               public void onComplete(@NonNull Task<Void> task) {
                   if (task.isSuccessful()) {
                       Toast.makeText(Forgetpassword.this, "Check your mail!", Toast.LENGTH_SHORT).show();
                   }else{
                       Toast.makeText(Forgetpassword.this, "Try again!", Toast.LENGTH_SHORT).show();
                   }


               }
           });
   }

    @Override
    public void onClick(View view) {

    }
}