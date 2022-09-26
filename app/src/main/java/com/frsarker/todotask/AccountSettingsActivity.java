package com.frsarker.todotask;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class AccountSettingsActivity extends AppCompatActivity {
    TextView AccountSettings,Logout,FullName,Role,FullName_Profile,Role_Profile;
    ImageView img,Back;
    Button edit_profile,change_passoword;
    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    FirebaseFirestore fstore;
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
        mDatabase = FirebaseDatabase.getInstance().getReference();
        fstore = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        DocumentReference documentReference = fstore.collection("User").document(user.getUid());
        documentReference.addSnapshotListener(AccountSettingsActivity.this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                FullName.setText(value.getString("Fullname"));
                if(value.getLong("Role") ==1){
                    Role.setText("Admin");
                }
                else if (value.getLong("Role") ==2){
                    Role.setText("User");
                }
                else{
                    Role.setText("Guest");
                }
            }
        });
    }
    public void Logout(View view){
        mAuth.signOut();
        startActivity(new Intent(AccountSettingsActivity.this,LoginActivity.class));
    }
    public void Back(View view){
        startActivity(new Intent(AccountSettingsActivity.this,MainActivity.class));
    }
}
