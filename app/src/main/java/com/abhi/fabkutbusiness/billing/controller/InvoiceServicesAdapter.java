package com.abhi.fabkutbusiness.billing.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

import java.util.List;


public class InvoiceServicesAdapter extends RecyclerView.Adapter<InvoiceServicesAdapter.MyViewHolder> {

    private List<ResponseModelRateInfoData> rateDataList;
    private int mLayoutResourceId;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvNetPrice;


        MyViewHolder(View view) {
            super(view);

            tvName = (TextView) view.findViewById(R.id.tv_service_name);
            tvPrice = (TextView) view.findViewById(R.id.tv_service_price);
            tvNetPrice = (TextView) view.findViewById(R.id.tv_service_netPrice);

        }
    }


    public InvoiceServicesAdapter(List<ResponseModelRateInfoData> rateDataList, int mLayoutResourceId) {
        this.rateDataList = rateDataList;
        this.mLayoutResourceId = mLayoutResourceId;

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
        holder.tvPrice.setText("" + rateData.getRate() + "");
        holder.tvNetPrice.setText("" + rateData.getRate() + "");


    }


    @Override
    public int getItemCount() {
        return rateDataList.size();
    }


}