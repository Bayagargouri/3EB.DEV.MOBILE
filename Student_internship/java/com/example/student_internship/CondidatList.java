package com.example.student_internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class CondidatList extends AppCompatActivity{
    RecyclerView recyclerView;
    ArrayList<student> list;
    DatabaseReference databaseReference;
    adapter2 adapterS;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CondidatList.this,condidat.class));
        finish();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condidat_list);
        recyclerView=findViewById(R.id.recycleviewS);
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterS = new adapter2(this,list);
        recyclerView.setAdapter(adapterS);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    student Student=dataSnapshot.getValue(student.class);
                    list.add(Student);
                }
                adapterS.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}