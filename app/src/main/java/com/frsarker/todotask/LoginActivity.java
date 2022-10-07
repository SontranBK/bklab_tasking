package com.frsarker.todotask;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

    private View _bg__login_ek2;
    private View ellipse_1;
    private View ellipse_1_ek1;
    private ImageView undraw_mobile_re_q4nk_1;
    private TextView welcome_back_;
    private View rectangle_2;
    private TextView enter_your_email;
    private View rectangle_2_ek1;
    private TextView confirm_password;
    private TextView forgot_password;
    private View rectangle_1;
    private TextView get_started;
    private TextView already_have_an_account___sign_in;
    FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _bg__login_ek2 = (View) findViewById(R.id._bg__login_ek2);
        ellipse_1 = (View) findViewById(R.id.ellipse_1);
        ellipse_1_ek1 = (View) findViewById(R.id.ellipse_1_ek1);
        undraw_mobile_re_q4nk_1 = (ImageView) findViewById(R.id.undraw_mobile_re_q4nk_1);
        welcome_back_ = (TextView) findViewById(R.id.welcome_back_);
        rectangle_2 = (View) findViewById(R.id.rectangle_2);
        enter_your_email = (TextView) findViewById(R.id.enter_your_email);
        rectangle_2_ek1 = (View) findViewById(R.id.rectangle_2_ek1);
        confirm_password = (TextView) findViewById(R.id.confirm_password);
        forgot_password = (TextView) findViewById(R.id.forgot_password);
        rectangle_1 = (View) findViewById(R.id.rectangle_1);
        get_started = (TextView) findViewById(R.id.get_started);
        already_have_an_account___sign_in = (TextView) findViewById(R.id.already_have_an_account___sign_in);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


    }
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            startActivity(new Intent( LoginActivity.this,MainActivity.class));
        }
    }
    public void loginUser(View view){
        mAuth.signInWithEmailAndPassword(enter_your_email.getText().toString(),confirm_password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
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
                            mDatabase.child("Users").child(user.getUid()).child("Name").setValue(value.getString("Name"));
                            /*if( value.getLong("Role") == 1){
                                mDatabase.child("Users").child(user.getUid()).child("Role").setValue("Admin");

                                startActivity(new Intent(LoginActivity.this,AdminActivity.class));
                            }
                            else{
                                mDatabase.child("Users").child(user.getUid()).child("Role").setValue(value.getString("User"));

                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }*/
                            startActivity(new Intent(LoginActivity.this,Welcome.class));
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
    public void OpenRegister(View view){
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }
}
