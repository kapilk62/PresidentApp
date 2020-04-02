package com.example.presidentapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.presidentapp.Model.AddAnnouncementModel;
import com.example.presidentapp.R;

import java.util.List;
import java.util.zip.Inflater;

public class Announcement_adapter extends ArrayAdapter<AddAnnouncementModel>{
    private Context context;
    private List<AddAnnouncementModel> AnnouncementDetailsList;

    public Announcement_adapter(Context context, List<AddAnnouncementModel> AnnouncementDetailsList){
        super(context, R.layout.announcement_listview, AnnouncementDetailsList);
        this.context = context;
        this.AnnouncementDetailsList= AnnouncementDetailsList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.announcement_listview, null, true);

        TextView textViewannouncementname= listViewItem.findViewById(R.id.announcementnameshow_txt_view);
        TextView textViewannouncementnumber = listViewItem.findViewById(R.id.announcementdescriptionshow_txt_view);
        TextView textViewannouncementtime = listViewItem.findViewById(R.id.announcementtimeshow_txt_view);
        TextView textViewannouncementdate = listViewItem.findViewById(R.id.announcementdateshow_txt_view);
        TextView textViewannouncementtype = listViewItem.findViewById(R.id.announcementtypeshow_txt_view);

        AddAnnouncementModel addAnnouncementModel = AnnouncementDetailsList.get(position);

        textViewannouncementname.setText(addAnnouncementModel.getAnnoucementName());
        textViewannouncementnumber.setText(addAnnouncementModel.getAnnoucementDescrpition());
        textViewannouncementtime.setText(addAnnouncementModel.getAnnoucementTime());
        textViewannouncementdate.setText(addAnnouncementModel.getAnnoucementDate());
        textViewannouncementtype.setText(addAnnouncementModel.getAnnoucementType());

        return listViewItem;
    }
}
