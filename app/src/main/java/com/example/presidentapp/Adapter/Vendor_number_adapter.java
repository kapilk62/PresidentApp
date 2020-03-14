package com.example.presidentapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.presidentapp.Model.Emergency_Num_Model;
import com.example.presidentapp.Model.Vendor_Num_Model;
import com.example.presidentapp.R;

import java.util.List;

public class Vendor_number_adapter extends ArrayAdapter<Vendor_Num_Model> {

    private Context context;
    private List<Vendor_Num_Model> VendorDetailsList;

    public Vendor_number_adapter(Context context, List<Vendor_Num_Model> VendorDetailsList){
        super(context, R.layout.vendor_detail_listview, VendorDetailsList);
        this.context = context;
        this.VendorDetailsList= VendorDetailsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.vendor_detail_listview, null, true);

        TextView textViewvendorname= listViewItem.findViewById(R.id.textViewVendorName);
        TextView textViewvendornumber = listViewItem.findViewById(R.id.textViewVendorNumber);
        TextView textViewvendoraddress = listViewItem.findViewById(R.id.textViewVendorAddress);
        TextView textViewvendorcategory = listViewItem.findViewById(R.id.textViewVendorCategory);

        Vendor_Num_Model vendor_num_model  = VendorDetailsList.get(position);

        textViewvendoraddress.setText(vendor_num_model.getVendorAddress());
        textViewvendorcategory.setText(vendor_num_model.getVendorCategory());
        textViewvendorname.setText(vendor_num_model.getVendorName());
        textViewvendornumber.setText(vendor_num_model.getVendorNumber());

        return listViewItem;
    }
}
