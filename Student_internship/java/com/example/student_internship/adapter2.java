package com.example.student_internship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter2 extends RecyclerView.Adapter<adapter2.MyViewHolder> {
    Context context;
    ArrayList<student> List;

    public adapter2(Context context, ArrayList<student> list) {
        this.context = context;
        this.List = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.studententry,parent,false);

        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        student Student=List.get(position);
        holder.nomstu.setText(Student.getNomstu());
        holder.mailstu.setText(Student.getmailstu());
        holder.competencesstu.setText(Student.getcomptencesstu());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder{
        TextView nomstu,mailstu,competencesstu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomstu=itemView.findViewById(R.id.namestu);
            mailstu=itemView.findViewById(R.id.mailstu);
            competencesstu=itemView.findViewById(R.id.competencestu);

        }
    }
}