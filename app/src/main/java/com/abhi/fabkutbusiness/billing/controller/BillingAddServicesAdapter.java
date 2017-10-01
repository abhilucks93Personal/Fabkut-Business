package com.abhi.fabkutbusiness.billing.controller;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

import java.util.List;


public class BillingAddServicesAdapter extends RecyclerView.Adapter<BillingAddServicesAdapter.MyViewHolder> {

    private Activity context;
    private List<ResponseModelRateInfoData> rateDataList;
    private int mLayoutResourceId;
    private int selectedIndex = -1;

    public ResponseModelRateInfoData getSelectedService() {
        if (selectedIndex >= 0)
            return rateDataList.get(selectedIndex);
        else
            return null;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice;
        ImageView ivTick;
        View convertView;


        MyViewHolder(View view) {
            super(view);
            convertView = view;

            ivTick = (ImageView) view.findViewById(R.id.iv_service_check);
            tvName = (TextView) view.findViewById(R.id.tv_service_name);
            tvPrice = (TextView) view.findViewById(R.id.tv_service_price);

        }
    }


    public BillingAddServicesAdapter(List<ResponseModelRateInfoData> rateDataList, int mLayoutResourceId, Activity context) {
        this.rateDataList = rateDataList;
        this.mLayoutResourceId = mLayoutResourceId;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(mLayoutResourceId, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ResponseModelRateInfoData rateData = rateDataList.get(position);


        holder.tvName.setText(rateData.getSub_service_name());
        holder.tvPrice.setText("" + rateData.getRate() + "rs");


        if (position == selectedIndex)
            holder.ivTick.setVisibility(View.VISIBLE);
        else
            holder.ivTick.setVisibility(View.INVISIBLE);

        holder.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIndex = position;
                notifyDataSetChanged();
            }
        });


    }


    @Override
    public int getItemCount() {
        return rateDataList.size();
    }


}