package com.frsarker.todotask;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button btnLogin;
    TextInputEditText Email;
    TextInputEditText Password;
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        Email = findViewById(R.id.EmailInput);
        Password = findViewById(R.id.PasswordInput);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        btnLogin.setOnClickListener(view->{
            loginUser();
        });

    }
    public void loginUser(){
        mAuth.signInWithEmailAndPassword(Email.getText().toString(),Password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    mDatabase.child("Users").child(user.getUid()).child("Email").setValue(user.getEmail());
                    Toast.makeText(LoginActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                    fstore = FirebaseFirestore.getInstance();
                    DocumentReference documentReference = fstore.collection("User").document(user.getUid());
                    documentReference.addSnapshotListener(LoginActivity.this, new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                            if( value.getLong("Role") == 1){
                                startActivity(new Intent(LoginActivity.this,AdminActivity.class));
                            }
                            else{
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }
                        }
                    });
                    Log.d(TAG,"Sign successful");

                }
                else{
                    Log.w(TAG,"signInWithEmail:failure",task.getException());
                    Toast.makeText(LoginActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
