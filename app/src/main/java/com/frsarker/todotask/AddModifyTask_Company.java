package com.frsarker.todotask;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddModifyTask_Company extends AppCompatActivity {
    String Colab ="";
    Calendar calendarStart,calendarEnd;
    EditText edit_text,edit_nametask;
    TextView new_task,description;
    Button save_btn;
    TextView dateTextStart,dateTextEnd,txtStart,txtEnd,Colab_with;
    private DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    int day=0,month=0,year=0,day1=32,month1=13,year1=9999999;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_modify_task_company);
        new_task = findViewById(R.id.add_project);
        calendarStart = new GregorianCalendar();
        calendarEnd = new GregorianCalendar();
        edit_text = findViewById(R.id.Description_task);
        description = findViewById(R.id.description);
        dateTextStart = findViewById(R.id.dateTextStart);
        dateTextEnd = findViewById(R.id.dateTextEnd);
        txtStart = findViewById(R.id.dateTextStart);
        txtEnd = findViewById(R.id.dateTextEnd);
        save_btn = findViewById(R.id.save_btn);
        edit_nametask = findViewById(R.id.edit_nametask);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        dateTextStart.setText(new SimpleDateFormat("E, dd MMMM yyyy").format(calendarStart.getTime()));
        dateTextEnd.setText(new SimpleDateFormat("E, dd MMMM yyyy").format(calendarEnd.getTime()));
        Colab_with = findViewById(R.id.Colab);
        Colab_with.setText(getIntent().getStringExtra("Member"));
        edit_nametask.setText(getIntent().getStringExtra("NameTask"));
        edit_text.setText(getIntent().getStringExtra("Description"));
        dateTextStart.setText(getIntent().getStringExtra("TimeBegin"));
        dateTextEnd.setText(getIntent().getStringExtra("TimeEnd"));
    }
    public void saveTask(View v) {

        /*Checking for Empty Task*/
        if (edit_text.getText().toString().trim().length() > 0 && edit_nametask.getText().toString().trim().length() >0) {
           AddTask();
           startActivity(new Intent(AddModifyTask_Company.this,TaskList.class));
           finish();
        } else {
            Toast.makeText(getApplicationContext(), "Empty task can't be saved.", Toast.LENGTH_SHORT).show();
        }

    }
    public void AddTask(){
        Toast.makeText(getApplicationContext(), "Task Added.", Toast.LENGTH_SHORT).show();
        FirebaseUser user = mAuth.getCurrentUser();
        String task_id = mDatabase.push().getKey();
        mDatabase.child("Task").child(task_id).child("NameTask").setValue(edit_nametask.getText().toString());
        mDatabase.child("Task").child(task_id).child("TimeBegin").setValue(dateTextStart.getText().toString());
        mDatabase.child("Task").child(task_id).child("TimeEnd").setValue(dateTextEnd.getText().toString());
        mDatabase.child("Task").child(task_id).child("Status").setValue("In progress");
        mDatabase.child("Task").child(task_id).child("Id").setValue(task_id);
        mDatabase.child("Task").child(task_id).child("Member").setValue(Colab_with.getText().toString());
        mDatabase.child("Task").child(task_id).child("Description").setValue(edit_text.getText().toString());
    }
    public void chooseDateBegin(View view) {
        final View dialogView = View.inflate(this, R.layout.date_picker, null);
        final DatePicker datePicker = dialogView.findViewById(R.id.date_picker);
        datePicker.updateDate(calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH), calendarStart.get(Calendar.DAY_OF_MONTH));
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setTitle("Choose Date Begin Task");
        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                calendarStart = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                if(Integer.valueOf(datePicker.getYear()) > year1){
                    Toast.makeText(AddModifyTask_Company.this, "Check time again", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.valueOf(datePicker.getYear()).equals(year1)){
                    if(Integer.valueOf(datePicker.getMonth()) > month1){
                        Toast.makeText(AddModifyTask_Company.this, "Check time again", Toast.LENGTH_SHORT).show();
                    }
                    else if(Integer.valueOf(datePicker.getMonth()).equals(month1)){
                        if(Integer.valueOf(datePicker.getDayOfMonth()) > day1){
                            Toast.makeText(AddModifyTask_Company.this, "Check time again", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            day = Integer.valueOf(datePicker.getDayOfMonth());
                            month = Integer.valueOf(datePicker.getMonth());
                            year = Integer.valueOf(datePicker.getYear());
                            dateTextStart.setText(new SimpleDateFormat("E,dd/MM/yyyy").format(calendarStart.getTime()));
                        }
                    }
                    else{
                        day = Integer.valueOf(datePicker.getDayOfMonth());
                        month = Integer.valueOf(datePicker.getMonth());
                        year = Integer.valueOf(datePicker.getYear());
                        dateTextStart.setText(new SimpleDateFormat("E,dd/MM/yyyy").format(calendarStart.getTime()));
                    }
                }
                else{
                    day = Integer.valueOf(datePicker.getDayOfMonth());
                    month = Integer.valueOf(datePicker.getMonth());
                    year = Integer.valueOf(datePicker.getYear());
                    dateTextStart.setText(new SimpleDateFormat("E,dd/MM/yyyy").format(calendarStart.getTime()));
                }
                //dateTextStart.setText(new SimpleDateFormat("E , dd/MM/yyyy").format(calendarEnd.getTime()));
            }
        });
        builder.show();
    }
    public void chooseDateEnd(View view) {
        final View dialogView = View.inflate(this, R.layout.date_picker, null);
        final DatePicker datePicker = dialogView.findViewById(R.id.date_picker);
        datePicker.updateDate(calendarEnd.get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH), calendarEnd.get(Calendar.DAY_OF_MONTH));
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        builder.setTitle("Choose Date Finish Task");
        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                calendarEnd = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                if(Integer.valueOf(datePicker.getYear()) < year){
                    Toast.makeText(AddModifyTask_Company.this, "Check time again", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.valueOf(datePicker.getYear()).equals(year)){
                    if(Integer.valueOf(datePicker.getMonth()) < month){
                        Toast.makeText(AddModifyTask_Company.this, "Check time again", Toast.LENGTH_SHORT).show();
                    }
                    else if(Integer.valueOf(datePicker.getMonth()).equals(month)){
                        if(Integer.valueOf(datePicker.getDayOfMonth()) < day){
                            Toast.makeText(AddModifyTask_Company.this, "Check time again", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            day1 = Integer.valueOf(datePicker.getDayOfMonth());
                            month1 = Integer.valueOf(datePicker.getMonth());
                            year1 = Integer.valueOf(datePicker.getYear());
                            dateTextEnd.setText(new SimpleDateFormat("E,dd/MM/yyyy").format(calendarEnd.getTime()));
                        }
                    }
                    else{
                        day1 = Integer.valueOf(datePicker.getDayOfMonth());
                        month1 = Integer.valueOf(datePicker.getMonth());
                        year1 = Integer.valueOf(datePicker.getYear());
                        dateTextEnd.setText(new SimpleDateFormat("E,dd/MM/yyyy").format(calendarEnd.getTime()));
                    }
                }
                else{
                    day1 = Integer.valueOf(datePicker.getDayOfMonth());
                    month1 = Integer.valueOf(datePicker.getMonth());
                    year1 = Integer.valueOf(datePicker.getYear());
                    dateTextEnd.setText(new SimpleDateFormat("E,dd/MM/yyyy").format(calendarEnd.getTime()));
                }
            }
        });
        builder.show();
    }
    public void OpenUserList(View view){
        Intent intent = new Intent(AddModifyTask_Company.this,userList.class);
        intent.putExtra("Id","1");
        intent.putExtra("Member","1");
        intent.putExtra("NameTask",edit_nametask.getText().toString());
        intent.putExtra("Description",edit_text.getText().toString());
        intent.putExtra("TimeBegin",dateTextStart.getText().toString());
        intent.putExtra("TimeEnd",dateTextEnd.getText().toString());
        startActivity(intent);
        finish();
    }
    public void Back(View v){
        Intent intent = new Intent(AddModifyTask_Company.this,MainActivity.class);
        startActivity(intent);
    }

}

