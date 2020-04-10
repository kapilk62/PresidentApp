package com.example.presidentapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.presidentapp.Model.GateKeeper_AddGateKeeperModel;
import com.example.presidentapp.R;

import java.util.List;

public class GateKeeper_adapter extends ArrayAdapter<GateKeeper_AddGateKeeperModel>{

    private Context context;
    private List<GateKeeper_AddGateKeeperModel> GateKeeperDetailsList;

    public GateKeeper_adapter(Context context, List<GateKeeper_AddGateKeeperModel> GateKeeperDetailsList){
        super(context, R.layout.gatekeeper_listview, GateKeeperDetailsList);
        this.context=context;
        this.GateKeeperDetailsList=GateKeeperDetailsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.gatekeeper_listview, null, true);

        TextView textViewgatekeeperfirstname= listViewItem.findViewById(R.id.textViewGateKeeperFirstNamelistview);
        TextView textViewgatekeeperlastname= listViewItem.findViewById(R.id.textViewGateKeeperLastNamelistview);
        TextView textViewgatekeeperemail= listViewItem.findViewById(R.id.textViewGateKeeperEmaillistview);
        TextView textViewgatekeepermobilenumber= listViewItem.findViewById(R.id.textViewGateKeeperMobileNumberlistview);

        GateKeeper_AddGateKeeperModel gateKeeper_addGateKeeperModel = GateKeeperDetailsList.get(position);

        textViewgatekeeperfirstname.setText(gateKeeper_addGateKeeperModel.getGatekeeperfirstname());
        textViewgatekeeperlastname.setText(gateKeeper_addGateKeeperModel.getGatekeeperlastname());
        textViewgatekeeperemail.setText(gateKeeper_addGateKeeperModel.getGatekeeperemail());
        textViewgatekeepermobilenumber.setText(gateKeeper_addGateKeeperModel.getGatekeepermobilenumber());

        return listViewItem;

    }
}
