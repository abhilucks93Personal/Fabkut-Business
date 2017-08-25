package com.abhi.fabkutbusiness.billing.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.billing.model.BillingData;
import com.abhi.fabkutbusiness.billing.view.BillDetailActivity;
import com.abhi.fabkutbusiness.billing.view.BillNowActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;

import java.util.ArrayList;

/**
 * Created by abhi on 21/05/17.
 */

public class BillingAdapter extends ArrayAdapter<ResponseModelAppointmentsData> {
    Context context;

    private static class ViewHolder {
        TextView name, status, time, num, billNow;

    }

    public BillingAdapter(Context context, ArrayList<ResponseModelAppointmentsData> billingDatas) {
        super(context, R.layout.item_billing, billingDatas);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder; // view lookup cache stored in tag

        viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_billing, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.status = (TextView) convertView.findViewById(R.id.tv_status);
            viewHolder.time = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.num = (TextView) convertView.findViewById(R.id.tv_num);
            viewHolder.billNow = (TextView) convertView.findViewById(R.id.tv_bill_now);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ResponseModelAppointmentsData billingData = getItem(position);

        viewHolder.name.setText("" + billingData.getCustomerEndUser_FirstName() + " " + billingData.getCustomerEndUser_LastName());
        viewHolder.status.setText(" (" + Utility.getBookingTypeText(billingData.getBookingType()) + ") ");
        viewHolder.time.setText("" + Utility.getFormattedSlotTime(billingData.getSlots()) + " ");
        viewHolder.num.setText("" + billingData.getCustomerMobile() + " ");

        viewHolder.billNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Utility.releaseSeat((Activity) getContext(), billingData.getSeatNumber(), position);
                Utility.releaseEmployeeSelectedSlots((Activity) getContext(), billingData.getEmployee().getEmp_id(), billingData.getSlots());
                ((BillNowActivity) context).refreshData();*/


                getContext().startActivity(new Intent(getContext(), BillDetailActivity.class)
                        .putExtra("data", billingData));
            }
        });


        return convertView;
    }
}
