package com.example.presidentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.presidentapp.Adapter.RecyclerView_Member_Flats_adapter;
import com.example.presidentapp.Adapter.RecyclerView_Members_Adapter;
import com.example.presidentapp.Model.Member_DataModel;
import com.example.presidentapp.Model.Member_Flats_DataModel;

import java.util.ArrayList;

public class Member_Flats extends AppCompatActivity implements RecyclerView_Member_Flats_adapter.ItemListener {

    RecyclerView recyclerView;
    ArrayList arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_flats);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList();
        arrayList.add(new Member_Flats_DataModel("Item 1", R.drawable.ic_account_name, "#09A9FF"));
        arrayList.add(new Member_Flats_DataModel("Item 2", R.drawable.flag_honduras, "#3E51B1"));
        arrayList.add(new Member_Flats_DataModel("Item 3", R.drawable.ic_email, "#673BB7"));
        arrayList.add(new Member_Flats_DataModel("Item 4", R.drawable.ic_upiid, "#4BAA50"));
        arrayList.add(new Member_Flats_DataModel("Item 5", R.drawable.ic_balancesheet, "#F94336"));
        arrayList.add(new Member_Flats_DataModel("Item 6", R.drawable.ic_complaints, "#0A9B88"));

        RecyclerView_Member_Flats_adapter adapter1 = new RecyclerView_Member_Flats_adapter(this, arrayList, this);
        recyclerView.setAdapter(adapter1);


        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        Member_flat_GridLayoutManager layoutManager = new Member_flat_GridLayoutManager(this, 300);
        recyclerView.setLayoutManager(layoutManager);


        /**
         Simple GridLayoutManager that spans two columns
         **/
        GridLayoutManager manager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onItemClick(Member_Flats_DataModel item) {
        Intent intent = new Intent(Member_Flats.this, EmergencyForm.class);
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
