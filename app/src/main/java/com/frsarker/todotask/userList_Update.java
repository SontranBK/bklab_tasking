package com.frsarker.todotask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userList_Update extends AppCompatActivity implements MyAdapter.OnItemClickListener {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<User> list;
    Button asign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        recyclerView = findViewById(R.id.userList);
        database = FirebaseDatabase.getInstance().getReference("User");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list,this);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);
                }
                myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        asign = findViewById(R.id.button);
        asign.setOnClickListener(view ->{
            Intent intent = new Intent(userList_Update.this,Update_Delete_Task_Company.class);
            intent.putExtra("Id",getIntent().getStringExtra("Id"));
            intent.putExtra("NameTask",getIntent().getStringExtra("NameTask"));
            intent.putExtra("Description",getIntent().getStringExtra("Description"));
            intent.putExtra("TimeBegin",getIntent().getStringExtra("TimeBegin"));
            intent.putExtra("TimeEnd",getIntent().getStringExtra("TimeEnd"));
            intent.putExtra("Member",Colab);
            startActivity(intent);
        });

    }
    String Colab ="";
    @Override
    public void onItemClick(int position) {
        User user = list.get(position);
        Toast.makeText(getApplicationContext(),user.getName()+" is added to task", Toast.LENGTH_SHORT).show();
        Colab += user.getName()+",";
    }
}
