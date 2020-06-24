package com.example.presidentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.presidentapp.Adapter.RecyclerView_Members_Adapter;
import com.example.presidentapp.Model.Member_DataModel;

import java.util.ArrayList;

public class Members extends AppCompatActivity implements RecyclerView_Members_Adapter.ItemListener {

    RecyclerView recyclerView;
    ArrayList arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList();
        arrayList.add(new Member_DataModel("Floor 1" , "#202020"));
        arrayList.add(new Member_DataModel("Floor 2" , "#202020"));
        arrayList.add(new Member_DataModel("Floor 3",  "#202020"));
        arrayList.add(new Member_DataModel("Floor 4",  "#202020"));
        arrayList.add(new Member_DataModel("Floor 5",  "#202020"));
        arrayList.add(new Member_DataModel("Floor 6",  "#202020"));

        RecyclerView_Members_Adapter adapter = new RecyclerView_Members_Adapter(this, arrayList, this);
        recyclerView.setAdapter(adapter);


        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        Member_GridLayoutManager layoutManager = new Member_GridLayoutManager(this, 300);
        recyclerView.setLayoutManager(layoutManager);


        /**
         Simple GridLayoutManager that spans two columns
         **/
        GridLayoutManager manager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onItemClick(Member_DataModel item) {
        Intent intent = new Intent(Members.this, Member_Flats.class);
        intent.putExtra("dataitem", String.valueOf(item));
        startActivity(intent);

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

}
