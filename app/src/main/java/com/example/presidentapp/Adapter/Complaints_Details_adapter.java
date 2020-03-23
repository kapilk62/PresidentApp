package com.example.presidentapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.presidentapp.Model.AddComplaintsModel;
import com.example.presidentapp.R;

import java.util.List;

public class Complaints_Details_adapter extends ArrayAdapter<AddComplaintsModel>{

    private Context context;
    private List<AddComplaintsModel> ComplaintsDetailsList;

    public Complaints_Details_adapter(Context context, List<AddComplaintsModel> ComplaintsDetailsList){
        super(context, R.layout.complaintdetails_listview, ComplaintsDetailsList);
        this.context=context;
        this.ComplaintsDetailsList=ComplaintsDetailsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.complaintdetails_listview,null,true);

        TextView textViewComplaintName = listViewItem.findViewById(R.id.complaintname_txtview_listview);
        AddComplaintsModel addComplaintsModel = ComplaintsDetailsList.get(position);
        textViewComplaintName.setText("➡ "+addComplaintsModel.getComplaintname());
        return listViewItem;
    }
}
