package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void phoneapp(View view){
        Intent phoneIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("tel:9090900990"));
        startActivity(phoneIntent);
    }

    public void mailapp(View view){
        Intent mailIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:epartment@gmail.com"));
        startActivity(mailIntent);
    }
}
