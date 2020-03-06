package com.example.presidentapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Profile extends AppCompatActivity{
    private static final String TAG = "ProfileActivity";
    TextInputEditText profilFirsName,profilLastName,profileEmail,profileMobile;
    Button updateprofilebutton;
    String FIRST_NAME = null;
    String LAST_NAME = null;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseUsers;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);
        profilFirsName = findViewById(R.id.firstname_txt_fld);
        profilLastName = findViewById(R.id.lastename_txt_fld);
        profileEmail = findViewById(R.id.email_txt_fld);
        profileMobile = findViewById(R.id.mob_no_txt_fld);
        updateprofilebutton = findViewById(R.id.updateprofile_btn);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        if (user != null)
        {
            Log.d(TAG, "onCreate: "+user.getEmail());
            profileEmail.setText(user.getEmail());
            profileEmail.setSelection(user.getEmail().length());



        }
    }
    public void update_profile(View view) {

    }


}

