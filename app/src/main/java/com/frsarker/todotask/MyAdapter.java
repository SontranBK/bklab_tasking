package com.frsarker.todotask;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.collection.LLRBNode;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> list;
    private OnItemClickListener mlistener;
    public MyAdapter(Context context, ArrayList<User> list,OnItemClickListener mlistener) {
        this.context = context;
        this.list = list;
        this.mlistener=mlistener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v,mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.Name.setText(user.getName());
        holder.Role.setText(user.getRole());

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView Name,Role;
        CheckBox checkBox;
        OnItemClickListener onItemClickListener;
        public MyViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            Name = itemView.findViewById(R.id.FullName);
            Role = itemView.findViewById(R.id.Role);
            checkBox = itemView.findViewById(R.id.checkBox2);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
            checkBox.setChecked(true);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
