package com.frsarker.todotask;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;


public class AccountSettingsActivity extends AppCompatActivity {
    TextView AccountSettings,Logout,FullName,Gmail,FullName_Profile,Role_Profile;
    ImageView img,Back;
    Button edit_profile,change_passoword;
    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    BottomNavigationView bottomNavigationView;
    FirebaseFirestore fstore;
    MyAdapter myAdapter;
    ArrayList<User> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivy_pofile);
        Gmail = findViewById(R.id.Profile_gmail);
        FullName_Profile = findViewById(R.id.Name_user);
        edit_profile = findViewById(R.id.btn_edtProfile);
        change_passoword = findViewById(R.id.btn_changepass);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        fstore = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                        DataSnapshot dataSnapshot = task.getResult();
                        FullName_Profile.setText(String.valueOf(dataSnapshot.child("Name").getValue()));
                        Gmail.setText(String.valueOf(dataSnapshot.child("Email").getValue()));
                    }

                }
            }
        });
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setSelectedItemId(R.id.miSettings);
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
                    case R.id.AddTask:
                        startActivity(new Intent(getApplicationContext(),AddModifyTask_Company.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    public void Logout(View view){
        mAuth.signOut();
        startActivity(new Intent(AccountSettingsActivity.this,LoginActivity.class));
        finish();
    }
    public void Back(View view){
        startActivity(new Intent(AccountSettingsActivity.this,MainActivity.class));
    }
    public void AddTask(View view){
        startActivity(new Intent(getApplicationContext(),AddModifyTask_Company.class));
        finish();
    }
}
