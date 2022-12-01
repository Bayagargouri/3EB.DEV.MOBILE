package com.example.student_internship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class compte1 extends AppCompatActivity implements View.OnClickListener{
    private Button  voir,post;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte1);

        voir = (Button) findViewById(R.id.button2);
        voir.setOnClickListener(this);
        post = (Button) findViewById(R.id.button6);
        post.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                startActivity(new Intent(this, offrestage.class));
                break;
            case R.id.button6:
                startActivity(new Intent(this, condidat.class));
                break;

        }
    }
}