package com.example.presidentapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.presidentapp.Model.Member_DataModel;
import com.example.presidentapp.Model.Member_Flats_DataModel;
import com.example.presidentapp.R;

import java.util.ArrayList;

public class RecyclerView_Member_Flats_adapter extends RecyclerView.Adapter<RecyclerView_Member_Flats_adapter.ViewHolder> {
    ArrayList mValues;
    Context mContext;
    protected RecyclerView_Member_Flats_adapter.ItemListener mListener;

    public RecyclerView_Member_Flats_adapter(Context context, ArrayList values, RecyclerView_Member_Flats_adapter.ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener=itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public ImageView imageView;
        public RelativeLayout relativeLayout;
        Member_Flats_DataModel item;

        public ViewHolder(View v) {

            super(v);
            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);

        }

        public void setData(Member_Flats_DataModel item) {
            this.item = item;

            textView.setText(item.text);
            imageView.setImageResource(item.drawable);
            relativeLayout.setBackgroundColor(Color.parseColor(item.color));

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }

    @Override
    public RecyclerView_Member_Flats_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_member_flat_recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder Vholder, int position) {
        Vholder.setData((Member_Flats_DataModel) mValues.get(position));
    }



    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(Member_Flats_DataModel item);
    }

}
