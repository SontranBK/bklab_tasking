package com.frsarker.todotask;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList extends AppCompatActivity implements MyAdapter_Company.OnTaskClickListener {
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter_Company myAdapter_company;
    ArrayList<Task_Company> list;
    Button Personal,Company,User;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasklist);
        recyclerView = findViewById(R.id.tasklist);
        database = FirebaseDatabase.getInstance().getReference("Task");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapter_company = new MyAdapter_Company(this,list,this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerView.setAdapter(myAdapter_company);
        Personal = findViewById(R.id.Personal);
        Company = findViewById(R.id.Company);
        User = findViewById(R.id.button2);
        User.setOnClickListener(view->{
            startActivity(new Intent(TaskList.this,AccountSettingsActivity.class));
            finish();
        });
        Personal.setOnClickListener(view->{
            startActivity(new Intent(TaskList.this,MainActivity.class));
            finish();
        });
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Task_Company task_company = dataSnapshot.getValue(Task_Company.class);
                    list.add(task_company);
                }
                myAdapter_company.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onTaskClick(int position) {
        Task_Company task_company = list.get(position);
        Intent intent = new Intent(TaskList.this,Update_Delete_Task_Company.class);
        intent.putExtra("Id",task_company.getId());
        intent.putExtra("NameTask",task_company.getNameTask());
        intent.putExtra("Description",task_company.getDescription());
        intent.putExtra("TimeBegin",task_company.getTimeBegin());
        intent.putExtra("TimeEnd",task_company.getTimeEnd());
        intent.putExtra("Member",task_company.getMember());
        startActivity(intent);
    }
    public void openAddModifyTask(View v){
        Intent intent =new Intent(TaskList.this,AddModifyTask_Company.class);
        intent.putExtra("Id","");
        intent.putExtra("NameTask","");
        intent.putExtra("Description","");
        intent.putExtra("TimeBegin","Sat,24 September 2022");
        intent.putExtra("TimeEnd","Sat,24 September 2022");
        intent.putExtra("Member","");
        startActivity(intent);
    }
}
