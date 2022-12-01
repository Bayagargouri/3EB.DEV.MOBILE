package com.example.student_internship;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class offrestage extends AppCompatActivity {
    Button insert, view, logout1;
    EditText nom, post, competences;
    DatabaseReference databasestages;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offrestage);
        insert = findViewById(R.id.insert);
        view = findViewById(R.id.view);
        nom = findViewById(R.id.editnom);
        post = findViewById(R.id.editpost);
        competences = findViewById(R.id.editcomptences);
        databasestages = FirebaseDatabase.getInstance().getReference();
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData();

            }

        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(offrestage.this, StageList.class));
                finish();
            }
        });
        logout1 = (Button) findViewById(R.id.button11);
        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(offrestage.this,MainActivity.class));

            }
        });
    }

    private void InsertData() {
        String adminname = nom.getText().toString();
        String adminpost = post.getText().toString();
        String admincompetences = competences.getText().toString();
        String id = databasestages.push().getKey();

        Stage stage = new Stage(adminname, adminpost, admincompetences);
        databasestages.child("stages").setValue(stage).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(offrestage.this, "successful", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}