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

public class condidat extends AppCompatActivity {
    Button insert,view,logout;
    EditText nomstu,mailstu,competencesstu;
    DatabaseReference databasestudent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condidat);
        insert=findViewById(R.id.insert);
        view=findViewById(R.id.view);
        nomstu=findViewById(R.id.namestudent);
        mailstu=findViewById(R.id.mailstudent);
        competencesstu=findViewById(R.id.competancestudent);
        databasestudent= FirebaseDatabase.getInstance().getReference();
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData();

            }

        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(condidat.this,CondidatList.class));
                finish();
            }
        });
        logout = (Button) findViewById(R.id.button10);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(condidat.this,MainActivity.class));

            }
        });
    }

    private void InsertData() {
        String adminname=nomstu.getText().toString();
        String adminmail=mailstu.getText().toString();
        String admincompetences=competencesstu.getText().toString();
        String id=databasestudent.push().getKey();

        student Student=new student(adminname,adminmail,admincompetences);
        databasestudent.child("student").child(id).setValue(Student).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(condidat.this,"successful Apply",Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}