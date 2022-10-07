package com.frsarker.todotask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SetStatus extends AppCompatActivity {
    TextView Process,Cancel,Comple,Status;
    String st ="";
    Button Set;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_status);
        Status = findViewById(R.id.Setstatus);
        Status.setText("Status of Task :");
        Process = findViewById(R.id.In_Process);
        Cancel = findViewById(R.id.Cancel);
        Comple = findViewById(R.id.Complete);
        Set = findViewById(R.id.button2);
        Process.setOnClickListener(view->{
            st = "In Process";
            Status.setText("Status of Task : "+st);
        });
        Cancel.setOnClickListener(View->{
            st = "Cancel";
            Status.setText("Status of Task : "+st);
        });
        Comple.setOnClickListener(view->{
            st = "Complete";
            Status.setText("Status of Task : "+st);
        });
        Set.setOnClickListener(view->{
            Intent intent = new Intent(SetStatus.this,Update_Delete_Task_Company.class);
            intent.putExtra("Id",getIntent().getStringExtra("Id"));
            intent.putExtra("NameTask",getIntent().getStringExtra("NameTask"));
            intent.putExtra("Description",getIntent().getStringExtra("Description"));
            intent.putExtra("TimeBegin",getIntent().getStringExtra("TimeBegin"));
            intent.putExtra("TimeEnd",getIntent().getStringExtra("TimeEnd"));
            intent.putExtra("Member",getIntent().getStringExtra("Member"));
            intent.putExtra("Status",st);
            startActivity(intent);
            finish();
        });

    }
}
