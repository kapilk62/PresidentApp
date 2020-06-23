package com.example.presidentapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.presidentapp.Model.GateKeeper_AddGatesModel;
import com.example.presidentapp.R;

import java.util.List;

public class Gates_adapter extends ArrayAdapter<GateKeeper_AddGatesModel>{
    private Context context;
    private List<GateKeeper_AddGatesModel> GatesDetailsList;

    public Gates_adapter(Context context, List<GateKeeper_AddGatesModel> GatesDetailsList){
        super(context, R.layout.gates_listview, GatesDetailsList);
        this.context = context;
        this.GatesDetailsList= GatesDetailsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.gates_listview, null, true);

        TextView textViewgates= listViewItem.findViewById(R.id.gateslistview_txt_view);

        GateKeeper_AddGatesModel gateKeeper_addGatesModel = GatesDetailsList.get(position);

        textViewgates.setText(gateKeeper_addGatesModel.getGatesName());

        return listViewItem;

    }
}
