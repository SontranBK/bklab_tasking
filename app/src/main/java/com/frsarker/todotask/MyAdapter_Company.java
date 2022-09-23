package com.frsarker.todotask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MyAdapter_Company extends RecyclerView.Adapter<MyAdapter_Company.MyViewHolder_Company> {

    Context context;
    ArrayList<Task_Company> list;


    public MyAdapter_Company(Context context, ArrayList<Task_Company> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder_Company onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.task_list_row_company,parent,false);
        return  new MyViewHolder_Company(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_Company holder, int position) {

        Task_Company task_company = list.get(position);
        holder.FullName.setText(task_company.getName_Task());
        holder.Status.setText(task_company.getStatus());
        holder.Colab.setText("Admin");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder_Company extends RecyclerView.ViewHolder{

        TextView FullName,Status,Colab;
        public MyViewHolder_Company(@NonNull View itemView) {
            super(itemView);
            FullName = itemView.findViewById(R.id.FullName);
            Status = itemView.findViewById(R.id.Satus);
            Colab = itemView.findViewById(R.id.Colab);

        }
    }

}
