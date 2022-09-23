package com.frsarker.todotask;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {
    Button admin_btnCreate_account,admin_btn_Task;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        admin_btnCreate_account = findViewById(R.id.CreateAccount);
        admin_btn_Task = findViewById(R.id.CreateTask);
        admin_btnCreate_account.setOnClickListener(view ->{
                    startActivity(new Intent(AdminActivity.this,RegisterActivity.class));
                }
        );
        admin_btn_Task.setOnClickListener(view ->{
            startActivity(new Intent(AdminActivity.this,MainActivity.class));
        });
    }
}
