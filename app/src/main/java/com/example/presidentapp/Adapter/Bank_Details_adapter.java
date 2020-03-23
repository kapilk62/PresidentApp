package com.example.presidentapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.presidentapp.Model.MyBuildingAddBankModel;
import com.example.presidentapp.R;

import java.util.List;

public class Bank_Details_adapter extends ArrayAdapter<MyBuildingAddBankModel>{

    private Context context;
    private List<MyBuildingAddBankModel> BankDetailsList;

    public Bank_Details_adapter(Context context , List<MyBuildingAddBankModel> BankDetailsList){
        super(context,R.layout.bankdetails_listview, BankDetailsList);
        this.context=context;
        this.BankDetailsList=BankDetailsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.bankdetails_listview,null,true);

        TextView textViewBankName = listViewItem.findViewById(R.id.bankname_txtview_listview);
        MyBuildingAddBankModel myBuildingAddBankModel = BankDetailsList.get(position);
        textViewBankName.setText("➡ "+myBuildingAddBankModel.getAddbankname());
        return listViewItem;
    }
}
