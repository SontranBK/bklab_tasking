package com.frsarker.todotask;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;


public class AccountSettingsActivity extends AppCompatActivity {
    TextView AccountSettings,Logout,FullName,Role,FullName_Profile,Role_Profile;
    ImageView img,Back;
    Button edit_profile,change_passoword;
    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    FirebaseFirestore fstore;
    MyAdapter myAdapter;
    ArrayList<User> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivy_pofile);
        Back=findViewById(R.id.Back);
        AccountSettings = findViewById(R.id.AccountSettings);
        Logout = findViewById(R.id.Logout);
        FullName = findViewById(R.id.Name_);
        Role = findViewById(R.id.Role_);
        FullName_Profile = findViewById(R.id.FullName_Profile);
        Role_Profile = findViewById(R.id.Role_Profile);
        img = findViewById(R.id.imageView2);
        edit_profile = findViewById(R.id.Edit_Profile);
        change_passoword = findViewById(R.id.Change_Password);
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
                        FullName.setText(String.valueOf(dataSnapshot.child("Name").getValue()));
                        Role.setText(String.valueOf(dataSnapshot.child("Role").getValue()));
                    }

                }
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
}
