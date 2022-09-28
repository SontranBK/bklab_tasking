package com.frsarker.todotask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText InputFullname, InputEmail, InputPassword, InputPhoneNumber;
    CheckBox ckAdmin, ckUser;
    Button crtAccount, backtoadmin;
    boolean valid = true;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    private DatabaseReference mDatabase;
    static int count = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        InputFullname = findViewById(R.id.edtFullname);
        InputEmail = findViewById(R.id.edtEmail);
        InputPassword = findViewById(R.id.edtPassword);
        InputPhoneNumber = findViewById(R.id.edtNumberPhone);
        ckAdmin = findViewById(R.id.checkAdmin);
        ckUser = findViewById(R.id.checkUser);
        crtAccount = findViewById(R.id.btnCreateAccount);
        backtoadmin = findViewById(R.id.backtoclient);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        crtAccount.setOnClickListener(view -> {
            mAuth.createUserWithEmailAndPassword(InputEmail.getText().toString(), InputPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    DocumentReference df = fStore.collection("User").document(user.getUid());
                    Map<String, Object> userInfo = new HashMap<>();
                    userInfo.put("Name", InputFullname.getText().toString());
                    userInfo.put("UserEmail", InputEmail.getText().toString());
                    userInfo.put("PhoneNumber", InputPhoneNumber.getText().toString());
                    if (ckAdmin.isChecked()) {
                        userInfo.put("Role", 1);
                        mDatabase.child("User").child(InputFullname.getText().toString()).child("Role").setValue("Admin");
                    } else {
                        userInfo.put("Role", 2);
                        mDatabase.child("User").child(InputFullname.getText().toString()).child("Role").setValue("user");
                    }
                    df.set(userInfo);
                    mDatabase.child("User").child(InputFullname.getText().toString()).child("Name").setValue(InputFullname.getText().toString());
                    Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                }
            });
        });
        backtoadmin.setOnClickListener(view->{
            startActivity(new Intent(RegisterActivity.this,AdminActivity.class));
        });

    }
}


