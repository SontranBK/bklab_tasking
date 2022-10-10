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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.net.ssl.SSLEngineResult;

public class Update_Delete_Task_Company extends AppCompatActivity {
    String Colab ="";
    Calendar calendarStart,calendarEnd;
    EditText edit_text,edit_nametask;
    TextView dateTextStart,dateTextEnd,txtStart,txtEnd,Colab_with,Sttus,st;
    Button update_btn,delete_btn,setStatus;
    private DatabaseReference mDatabase;
    FirebaseAuth mAuth;

    int day,day1,month,month1,year,year1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_update_task_company);
        Sttus = findViewById(R.id.Status);
        st = findViewById(R.id.st);
        calendarStart = new GregorianCalendar();
        calendarEnd = new GregorianCalendar();
        edit_text = findViewById(R.id.Description_task);
        dateTextStart = findViewById(R.id.dateTextStart);
        dateTextEnd = findViewById(R.id.dateTextEnd);
        txtStart = findViewById(R.id.dateTextStart);
        txtEnd = findViewById(R.id.dateTextEnd);
        update_btn = findViewById(R.id.save_btn);
        edit_nametask = findViewById(R.id.edit_nametask);
        delete_btn = findViewById(R.id.deleteTask);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        dateTextStart.setText(new SimpleDateFormat("E,dd MMMM yyyy").format(calendarStart.getTime()));
        dateTextEnd.setText(new SimpleDateFormat("E,dd MMMM yyyy").format(calendarEnd.getTime()));
        Colab_with = findViewById(R.id.Colab);
        edit_nametask.setText(getIntent().getStringExtra("NameTask"));
        edit_text.setText(getIntent().getStringExtra("Description"));
        dateTextStart.setText(getIntent().getStringExtra("TimeBegin"));
        dateTextEnd.setText(getIntent().getStringExtra("TimeEnd"));
        Colab_with.setText(getIntent().getStringExtra("Member"));
        Sttus.setText(getIntent().getStringExtra("Status"));
        mDatabase.child("Task").child(getIntent().getStringExtra("Id")).removeValue();
        String[] begin_t  = removeCharAt(removeCharAt(removeCharAt(removeCharAt(dateTextStart.getText().toString(),0),0),0),0).split("/");
        String[] end_t    = removeCharAt(removeCharAt(removeCharAt(removeCharAt(dateTextEnd.getText().toString(),0),0),0),0).split("/");
        day = Integer.valueOf(begin_t[0]);
        day1 = Integer.valueOf(end_t[0]);
        month = Integer.valueOf(begin_t[1]);
        year = Integer.valueOf(begin_t[2]);
        month1 = Integer.valueOf(end_t[1]);
        year1 = Integer.valueOf(end_t[2]);

    }
    public void setStatus(View v){
        Intent intent = new Intent(Update_Delete_Task_Company.this, SetStatus.class);
        intent.putExtra("Id",getIntent().getStringExtra("Id"));
        intent.putExtra("NameTask",getIntent().getStringExtra("NameTask"));
        intent.putExtra("Description",getIntent().getStringExtra("Description"));
        intent.putExtra("TimeBegin",getIntent().getStringExtra("TimeBegin"));
        intent.putExtra("TimeEnd",getIntent().getStringExtra("TimeEnd"));
        intent.putExtra("Member",getIntent().getStringExtra("Member"));
        intent.putExtra("Status",getIntent().getStringExtra("Status"));
        startActivity(intent);
        finish();
    }
    public void updateTask(View v){
        if (edit_text.getText().toString().trim().length() > 0 && edit_nametask.getText().toString().trim().length() >0) {
            UpdateTask();
            Toast.makeText(getApplicationContext(), "Task Update.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Update_Delete_Task_Company.this,TaskList.class));
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Empty task can't be saved.", Toast.LENGTH_SHORT).show();
        }
    }
    public void UpdateTask(){
        mDatabase.child("Task").child(getIntent().getStringExtra("Id")).removeValue();
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("Task").child(getIntent().getStringExtra("Id")).child("NameTask").setValue(edit_nametask.getText().toString());
        mDatabase.child("Task").child(getIntent().getStringExtra("Id")).child("TimeBegin").setValue(dateTextStart.getText().toString());
        mDatabase.child("Task").child(getIntent().getStringExtra("Id")).child("TimeEnd").setValue(dateTextEnd.getText().toString());
        mDatabase.child("Task").child(getIntent().getStringExtra("Id")).child("Id").setValue(getIntent().getStringExtra("Id"));
        mDatabase.child("Task").child(getIntent().getStringExtra("Id")).child("Member").setValue(Colab_with.getText().toString());
        mDatabase.child("Task").child(getIntent().getStringExtra("Id")).child("Description").setValue(edit_text.getText().toString());
        mDatabase.child("Task").child(getIntent().getStringExtra("Id")).child("Status").setValue(Sttus.getText().toString());
    }
    public void deleteTask(View v){
        mDatabase.child("Task").child(getIntent().getStringExtra("Id")).removeValue();
        Toast.makeText(getApplicationContext(), "Task Delete", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Update_Delete_Task_Company.this,TaskList.class));
    }
    public void OpenUserList_Company(View view){
        Intent intent =new Intent(Update_Delete_Task_Company.this,userList_Update.class);
        intent.putExtra("Id",getIntent().getStringExtra("Id"));
        intent.putExtra("NameTask",edit_nametask.getText().toString());
        intent.putExtra("Description",edit_text.getText().toString());
        intent.putExtra("TimeBegin",dateTextStart.getText().toString());
        intent.putExtra("TimeEnd",dateTextEnd.getText().toString());
        intent.putExtra("Status",Sttus.getText().toString());
        startActivity(intent);
        finish();
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
                    Toast.makeText(Update_Delete_Task_Company.this, "Check time again", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.valueOf(datePicker.getYear()).equals(year1)){
                    if(Integer.valueOf(datePicker.getMonth()) > month1){
                        Toast.makeText(Update_Delete_Task_Company.this, "Check time again", Toast.LENGTH_SHORT).show();
                    }
                    else if(Integer.valueOf(datePicker.getMonth()).equals(month1)){
                        if(Integer.valueOf(datePicker.getDayOfMonth()) > day1){
                            Toast.makeText(Update_Delete_Task_Company.this, "Check time again", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(Update_Delete_Task_Company.this, "Check time again", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.valueOf(datePicker.getYear()).equals(year)){
                    if(Integer.valueOf(datePicker.getMonth()) < month){
                        Toast.makeText(Update_Delete_Task_Company.this, "Check time again", Toast.LENGTH_SHORT).show();
                    }
                    else if(Integer.valueOf(datePicker.getMonth()).equals(month)){
                        if(Integer.valueOf(datePicker.getDayOfMonth()) < day){
                            Toast.makeText(Update_Delete_Task_Company.this, "Check time again", Toast.LENGTH_SHORT).show();
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
    public void Back(View v){
        UpdateTask();
        startActivity(new Intent(Update_Delete_Task_Company.this,TaskList.class));
        finish();
    }
    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }
}
