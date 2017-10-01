package com.abhi.fabkutbusiness.billing.controller;

import android.app.Activity;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.billing.view.BillDetailActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

import java.util.ArrayList;
import java.util.List;


public class BillingServicesAdapter2 extends RecyclerView.Adapter<BillingServicesAdapter2.MyViewHolder> {

    private List<ResponseModelRateInfoData> rateData;
    private Activity context;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvOriginalPrice, tvEmployee, tvRemove, tvDiscountedPrice, tvDiscount;


        MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvOriginalPrice = (TextView) view.findViewById(R.id.tv_original_price);
            tvDiscountedPrice = (TextView) view.findViewById(R.id.tv_discounted_price);
            tvDiscount = (TextView) view.findViewById(R.id.tv_discount);
            tvEmployee = (TextView) view.findViewById(R.id.tv_employee);
            tvRemove = (TextView) view.findViewById(R.id.tv_remove);


        }
    }


    public BillingServicesAdapter2(Activity context, ArrayList<ResponseModelRateInfoData> rateData) {
        this.context = context;
        this.rateData = rateData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_services_billing, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int position) {
        final ResponseModelRateInfoData billingServicesData = rateData.get(position);
        viewHolder.tvName.setText(billingServicesData.getSub_service_name());
        if (billingServicesData.getRate().length() > 0) {
            viewHolder.tvDiscountedPrice.setText("Rs. " + billingServicesData.getRate());
            viewHolder.tvOriginalPrice.setPaintFlags(viewHolder.tvOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.tvEmployee.setText(billingServicesData.getEmployee_name());
        }

        viewHolder.tvRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BillDetailActivity) context).showRemoveServiceDialog(billingServicesData.getSub_service_name());
            }
        });

    }


    @Override
    public int getItemCount() {
        return rateData.size();
    }


}