package com.example.student_internship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {
    Context context;
    ArrayList<Stage> List;

    public adapter(Context context, ArrayList<Stage> list) {
        this.context = context;
        this.List = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v= LayoutInflater.from(context).inflate(R.layout.stageentry,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Stage stage=List.get(position);
        holder.nom.setText(stage.getnom());
        holder.post.setText(stage.getpost());
        holder.competences.setText(stage.getcomptences());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public static class MyViewHolder  extends RecyclerView.ViewHolder{
        TextView nom,post,competences;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nom=itemView.findViewById(R.id.name);
            post=itemView.findViewById(R.id.post2);
            competences=itemView.findViewById(R.id.competence2);

        }
    }
}