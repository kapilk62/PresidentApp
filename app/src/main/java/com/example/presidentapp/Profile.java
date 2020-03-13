package com.example.presidentapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


public class Profile extends AppCompatActivity{
    private static final String TAG = "ProfileActivity";
    TextInputEditText profileFirstName,profileLastName,profileEmail,profileMobile;
    Button updateprofilebutton;
    String FIRST_NAME = null;
    String LAST_NAME = null;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseUsers;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);
        profileFirstName = findViewById(R.id.firstname_txt_fld);
        profileLastName = findViewById(R.id.lastename_txt_fld);
        profileEmail = findViewById(R.id.email_txt_fld);
        profileMobile = (TextInputEditText) findViewById(R.id.mob_no_txt_fld);
        updateprofilebutton = findViewById(R.id.updateprofile_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getInstance().getReference("Users").child(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                profileFirstName.setText(user.getFirst_name());
                profileLastName.setText(user.getLast_name());
                profileEmail.setText(user.getEmail());
                profileMobile.setText(user.getMobile_number());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this, databaseError.getCode(),Toast.LENGTH_LONG).show();
            }
        });

        updateprofilebutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String first_name = profileFirstName.getText().toString().trim();
                String last_name = profileLastName.getText().toString().trim();
                String email = profileEmail.getText().toString().trim();
                String mobile_number = profileMobile.getText().toString();
                Log.d(TAG, "onClick: "+mobile_number);
                if(TextUtils.isEmpty(first_name)){
                    profileFirstName.setError("First Name Required");
                }
                if(TextUtils.isEmpty(last_name)){
                    profileLastName.setError("Last Name Required");
                }
                if(TextUtils.isEmpty(email)){
                    profileEmail.setError("Email Id Required");
                }
                if(TextUtils.isEmpty(mobile_number)){
                    profileMobile.setError("Mobile Number Required");
                }
                updateprofile(first_name,last_name,email,mobile_number);
                finish();
            }
        });

    }

    private boolean updateprofile(String FirstName, String LastName, String Email , String MobileNumber){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseAuth.getUid());
        User user = new User(FirstName, LastName, Email, MobileNumber);
        databaseReference.setValue(user);
        Toast.makeText(this, "Profile Updated", Toast.LENGTH_LONG).show();
        return true;
    }

}


