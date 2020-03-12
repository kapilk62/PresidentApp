package com.example.presidentapp.Adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.presidentapp.Model.Emergency_Num_Model;
import com.example.presidentapp.R;
import java.util.List;

public class Emergency_number_adapter extends ArrayAdapter<Emergency_Num_Model> {

    private Context context;
    private List<Emergency_Num_Model> EmergencyNumberList;

    public Emergency_number_adapter(Context context, List<Emergency_Num_Model> EmergencyNumberList){
        super(context, R.layout.emergency_number_listview, EmergencyNumberList);
        this.context = context;
        this.EmergencyNumberList= EmergencyNumberList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.emergency_number_listview, null, true);

        TextView textViewemergencyname= listViewItem.findViewById(R.id.textViewEmergencyName);
        TextView textViewemergencynumber = listViewItem.findViewById(R.id.textViewEmergencyNumber);

        Emergency_Num_Model emergency_num_model  = EmergencyNumberList.get(position);

        textViewemergencyname.setText(emergency_num_model.getEmergencyName());
        textViewemergencynumber.setText(emergency_num_model.getEmergencyNumber());

        return listViewItem;
    }
}
