package com.frsarker.todotask;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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
    BottomNavigationView bottomNavigationView;
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
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setSelectedItemId(R.id.Task);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.miCalendar:
                        return true;

                    case R.id.Task:
                        startActivity(new Intent(getApplicationContext(),TaskList.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.miHome:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.miSettings:
                        startActivity(new Intent(getApplicationContext(),AccountSettingsActivity.class));
                        overridePendingTransition(0,0);
                        return  true;
                }
                return false;
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
        intent.putExtra("Status", task_company.getStatus());
        startActivity(intent);
        finish();
    }
    public void AddTask(View view){
        startActivity(new Intent(getApplicationContext(),AddModifyTask_Company.class));
        finish();
    }
}
