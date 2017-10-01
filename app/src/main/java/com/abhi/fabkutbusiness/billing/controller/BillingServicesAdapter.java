package com.abhi.fabkutbusiness.billing.controller;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.billing.view.BillDetailActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

import java.util.ArrayList;

/**
 * Created by abhi on 21/05/17.
 */

public class BillingServicesAdapter extends ArrayAdapter<ResponseModelRateInfoData> {


    public BillingServicesAdapter(Context context, ArrayList<ResponseModelRateInfoData> billingServicesDatas) {
        super(context, R.layout.item_services_billing, billingServicesDatas);
    }

    private static class ViewHolder {
        TextView tvName, tvOriginalPrice, tvEmployee, tvRemove, tvDiscountedPrice, tvDiscount;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder; // view lookup cache stored in tag

        viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_services_billing, parent, false);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvOriginalPrice = (TextView) convertView.findViewById(R.id.tv_original_price);
            viewHolder.tvDiscountedPrice = (TextView) convertView.findViewById(R.id.tv_discounted_price);
            viewHolder.tvDiscount = (TextView) convertView.findViewById(R.id.tv_discount);
            viewHolder.tvEmployee = (TextView) convertView.findViewById(R.id.tv_employee);
            viewHolder.tvRemove = (TextView) convertView.findViewById(R.id.tv_remove);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        try {
            final ResponseModelRateInfoData billingServicesData = getItem(position);
            viewHolder.tvName.setText(billingServicesData.getSub_service_name());
            if (billingServicesData.getRate().length() > 0) {
                viewHolder.tvDiscountedPrice.setText("Rs. " + billingServicesData.getRate());
                viewHolder.tvOriginalPrice.setPaintFlags(viewHolder.tvOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolder.tvEmployee.setText(billingServicesData.getEmployee_name());
            }

            viewHolder.tvRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BillDetailActivity) getContext()).showRemoveServiceDialog(billingServicesData.getSub_service_name());
                }
            });


        } catch (Exception e) {
            Log.e("", "");
        }

        return convertView;
    }
}
