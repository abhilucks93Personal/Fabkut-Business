package com.abhi.fabkutbusiness.booking.controller;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.booking.view.EditBookingServiceActivity;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

import java.util.ArrayList;
import java.util.List;


public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.MyViewHolder> {

    private Activity context;
    private List<ResponseModelRateInfoData> rateDataList;
    private ArrayList<ResponseModelRateInfoData> selectedRateDataList;
    private int mLayoutResourceId;
    private Boolean isSelectedLayout;
    private int totalTime = 0, totalCost = 0;

    public ArrayList<ResponseModelRateInfoData> getSelectedRateDataList() {
        return selectedRateDataList;
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


    public ServicesAdapter(List<ResponseModelRateInfoData> rateDataList, ArrayList<ResponseModelRateInfoData> selectedData, int mLayoutResourceId, Boolean isSelectedLayout, Activity context) {
        this.rateDataList = rateDataList;
        this.mLayoutResourceId = mLayoutResourceId;
        this.isSelectedLayout = isSelectedLayout;
        this.context = context;
        selectedRateDataList = selectedData;
        totalCost = 0;
        totalTime = 0;
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

        if (position == 0) {
            totalCost = 0;
            totalTime = 0;
        }

        if (!isSelectedLayout) {
            holder.ivTick.setVisibility(View.GONE);
            for (ResponseModelRateInfoData data : selectedRateDataList) {
                if (data.getSub_service_name().equals(rateData.getSub_service_name())) {
                    holder.ivTick.setVisibility(View.VISIBLE);
                }
            }
        }

        holder.tvName.setText(rateData.getSub_service_name());
        holder.tvPrice.setText("" + rateData.getRate() + "rs");


        holder.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSelectedLayout) {
                    if (holder.ivTick.getVisibility() == View.GONE) {
                        selectedRateDataList.add(rateData);
                        holder.ivTick.setVisibility(View.VISIBLE);
                    } else {
                        for (ResponseModelRateInfoData data : selectedRateDataList) {
                            if (data.getSub_service_name().equals(rateData.getSub_service_name())) {
                                selectedRateDataList.remove(data);
                                break;
                            }
                        }
                        holder.ivTick.setVisibility(View.GONE);
                    }
                    // notifyItemChanged(position);
                }
            }
        });

        if (isSelectedLayout) {

            totalCost += Integer.parseInt(rateData.getRate());
            totalTime += Integer.parseInt(rateData.getEta());

            if (position == (rateDataList.size() - 1)) {
                if (context instanceof BookNowActivity)
                    ((BookNowActivity) context).setServiceTotal(totalCost, totalTime);
                else if (context instanceof EditBookingServiceActivity)
                    ((EditBookingServiceActivity) context).setServiceTotal(totalCost, totalTime);
            }
        }

    }


    @Override
    public int getItemCount() {
        return rateDataList.size();
    }


}