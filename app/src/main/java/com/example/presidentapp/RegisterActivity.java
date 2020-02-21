package com.example.presidentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText mFirstname,mLastname,mEmail,mPassword,mMobileno;
    Button mRegisterbutton;
    TextView mLoginhere;
    FirebaseAuth fAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirstname= findViewById(R.id.firstname);
        mLastname =findViewById(R.id.lastname);
        mEmail = findViewById(R.id.emailid);
        mPassword = findViewById(R.id.paswd);
        mMobileno = findViewById(R.id.mobileno);
        mLoginhere=findViewById(R.id.createtext);

        mRegisterbutton = findViewById(R.id.registerbutton);

        fAuth= FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mRegisterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                String fname=mFirstname.getText().toString().trim();
                String lname=mLastname.getText().toString().trim();
                String m_no=mMobileno.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required");
                    return;
                }
                if (password.length() < 8) {
                    mPassword.setError("Password must be 8 or more Characters");
                    return;
                }

                if(TextUtils.isEmpty(m_no)){
                    mMobileno.setError("Mobile Number is Required");
                    return;
                }

                if(TextUtils.isEmpty(fname)){
                    mFirstname.setError("First Name is Required");
                    return;
                }
                if(TextUtils.isEmpty(lname)){
                    mLastname.setError("Last Name is Required");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User is Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        mLoginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

    }
}
