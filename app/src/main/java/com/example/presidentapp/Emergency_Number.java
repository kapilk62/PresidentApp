package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Emergency_Number extends AppCompatActivity {

    TabItem tabEmergency;
    TabItem tabVendor;
    TabLayout tablayout;
    ViewPager viewpager;
    PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency__number);
        tablayout = findViewById(R.id.tabLayout);
        tabEmergency = findViewById(R.id.Emergencytab);
        tabVendor = findViewById(R.id.Vendortab);
        viewpager = findViewById(R.id.viewPager);
        pageAdapter = new PageAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewpager.setAdapter(pageAdapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));



    }

}
