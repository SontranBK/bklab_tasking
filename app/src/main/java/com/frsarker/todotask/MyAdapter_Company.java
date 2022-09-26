package com.frsarker.todotask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class MyAdapter_Company extends RecyclerView.Adapter<MyAdapter_Company.MyViewHolder_Company> {

    Context context;
    ArrayList<Task_Company> list;
    private OnTaskClickListener  mlistener_company;
    public MyAdapter_Company(Context context, ArrayList<Task_Company> list,OnTaskClickListener mlistener_company) {
        this.context = context;
        this.list = list;
        this.mlistener_company=mlistener_company;
    }

    @NonNull
    @Override
    public MyViewHolder_Company onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.task_list_row_company,parent,false);
        return  new MyViewHolder_Company(v,mlistener_company);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_Company holder, int position) {

        Task_Company task_company = list.get(position);
        holder.NameTask.setText(task_company.getNameTask());
        holder.Status.setText(task_company.getStatus());
        holder.Member.setText(task_company.getMember());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder_Company extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView NameTask,Status,Member;
        OnTaskClickListener onTaskClickListener;
        CheckBox checkBox;
        public MyViewHolder_Company(@NonNull View itemView,OnTaskClickListener onTaskClickListener) {
            super(itemView);
            NameTask = itemView.findViewById(R.id.task_name);
            Status = itemView.findViewById(R.id.Satus);
            Member = itemView.findViewById(R.id.Colab_task);
            checkBox = itemView.findViewById(R.id.checkBtn);
            this.onTaskClickListener=onTaskClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onTaskClickListener.onTaskClick(getAdapterPosition());
        }
    }
    public interface OnTaskClickListener{
        void onTaskClick(int position);
    }

}
