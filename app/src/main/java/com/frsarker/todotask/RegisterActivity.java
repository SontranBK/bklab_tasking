package com.frsarker.todotask;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    private DatabaseReference mDatabase;
    static int count = 1;
     View _bg__registration_ek2;
    View ellipse_1;
     View ellipse_1_ek1;
     View rectangle_1;
     TextView get_started;
     TextView welcome_onboard_;
     TextView lets_help_you_meet_up_your_task;
    TextView already_have_an_account___sign_in;
    View rectangle_2;
     EditText enter_your_full_name;
    View rectangle_2_ek1;
    EditText enter_your_email;
    View rectangle_2_ek2;
    EditText enter_password;
    View rectangle_2_ek3;
    EditText confirm_password;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        _bg__registration_ek2 = findViewById(R.id._bg__login_ek2);
        ellipse_1 = findViewById(R.id.ellipse_1);
        ellipse_1_ek1 = findViewById(R.id.ellipse_1_ek1);
        rectangle_1 =  findViewById(R.id.rectangle_1);
        get_started = findViewById(R.id.get_started);
        welcome_onboard_ =  findViewById(R.id.welcome_onboard_);
        lets_help_you_meet_up_your_task = findViewById(R.id.lets_help_you_meet_up_your_task);
        already_have_an_account___sign_in =  findViewById(R.id.already_have_an_account___sign_in);
        rectangle_2 = (View) findViewById(R.id.rectangle_2);
        enter_your_full_name =  findViewById(R.id.enter_your_full_name);
        rectangle_2_ek1 =  findViewById(R.id.rectangle_2_ek1);
        enter_your_email =  findViewById(R.id.enter_your_email);
        rectangle_2_ek2 = findViewById(R.id.rectangle_2_ek2);
        enter_password =  findViewById(R.id.enter_password);
        rectangle_2_ek3 = findViewById(R.id.rectangle_2_ek3);
        confirm_password = findViewById(R.id.confirm_password);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public void Register_user(View view){
        if(enter_password.getText().toString().isEmpty() || enter_your_email.getText().toString().isEmpty() || enter_your_full_name.getText().toString().isEmpty() || confirm_password.getText().toString().isEmpty()){
            Toast.makeText(RegisterActivity.this, "Check again", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.createUserWithEmailAndPassword(enter_your_email.getText().toString(), enter_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!enter_password.getText().toString().equals(confirm_password.getText().toString())){
                        Toast.makeText(RegisterActivity.this, "Check again password confirm", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        FirebaseUser user = mAuth.getCurrentUser();
                        DocumentReference df = fStore.collection("User").document(user.getUid());
                        Map<String, Object> userInfo = new HashMap<>();
                        userInfo.put("Name", enter_your_full_name.getText().toString());
                        userInfo.put("UserEmail", enter_your_email.getText().toString());
                        userInfo.put("Role", 2);
                        mDatabase.child("User").child(enter_your_full_name.getText().toString()).child("Role").setValue("user");
                        mDatabase.child("Users").child(user.getUid()).child("Role").setValue("user");
                        mDatabase.child("Users").child(user.getUid()).child("Name").setValue(enter_your_full_name.getText().toString());
                        mDatabase.child("Users").child(user.getUid()).child("Email").setValue(enter_your_email.getText().toString());
                        df.set(userInfo);
                        mDatabase.child("User").child(enter_your_full_name.getText().toString()).child("Name").setValue(enter_your_full_name.getText().toString());
                        Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    }
                    /*if (ckAdmin.isChecked()) {
                        userInfo.put("Role", 1);
                        mDatabase.child("User").child(enter_your_full_name.getText().toString()).child("Role").setValue("Admin");
                    } else {
                        userInfo.put("Role", 2);
                        mDatabase.child("User").child(enter_your_full_name.getText().toString()).child("Role").setValue("user");
                    }
                userInfo.put("Role", 2);
                mDatabase.child("User").child(enter_your_full_name.getText().toString()).child("Role").setValue("user");
                df.set(userInfo);
                mDatabase.child("User").child(enter_your_full_name.getText().toString()).child("Name").setValue(enter_your_full_name.getText().toString());
                Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show()*/
                }
            });

        }


    }
    public  void OpenLogin(View view){
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }
}


