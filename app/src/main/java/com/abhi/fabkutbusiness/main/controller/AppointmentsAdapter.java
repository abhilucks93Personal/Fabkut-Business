package com.abhi.fabkutbusiness.main.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointments;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;

import java.util.ArrayList;

/**
 * Created by abhi on 21/05/17.
 */

public class AppointmentsAdapter extends ArrayAdapter<ResponseModelAppointmentsData> {

    Context context;

    private static class ViewHolder {
        TextView name, description, status, time, num, tvCancel, tvReschedule, tvBookNow;
        LinearLayout dummyFooter;

    }

    public AppointmentsAdapter(Context context, ArrayList<ResponseModelAppointmentsData> appointmentsData) {
        super(context, R.layout.item_appointments, appointmentsData);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder; // view lookup cache stored in tag

        viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_appointments, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.description = (TextView) convertView.findViewById(R.id.tv_description);
            viewHolder.status = (TextView) convertView.findViewById(R.id.tv_status);
            viewHolder.time = (TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.num = (TextView) convertView.findViewById(R.id.tv_num);

            viewHolder.tvBookNow = (TextView) convertView.findViewById(R.id.tv_book_now);
            viewHolder.tvCancel = (TextView) convertView.findViewById(R.id.tv_cancel_booking);
            viewHolder.tvReschedule = (TextView) convertView.findViewById(R.id.tv_reschedule_booking);
            viewHolder.dummyFooter = (LinearLayout) convertView.findViewById(R.id.dummy_footer);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ResponseModelAppointmentsData appointmentsData = getItem(position);

        viewHolder.name.setText("" + appointmentsData.getCustomerEndUser_FirstName() + " " + appointmentsData.getCustomerEndUser_LastName());

        viewHolder.status.setText(" (" + Utility.getBookingTypeText(appointmentsData.getBookingType()) + ") ");

        if (appointmentsData.getServices() != null)
            viewHolder.description.setText(Utility.getFormattedServiceList(appointmentsData.getServices()));

        if (appointmentsData.getSlots() != null)
            viewHolder.time.setText("" + Utility.getFormattedSlotTime(appointmentsData.getSlots(), appointmentsData.getBookingDate()) + " ");

        viewHolder.num.setText("" + appointmentsData.getCustomerMobile() + " ");


        if (position == getCount() - 1)
            viewHolder.dummyFooter.setVisibility(View.INVISIBLE);
        else
            viewHolder.dummyFooter.setVisibility(View.GONE);


        viewHolder.tvBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationMainActivity) context).bookWaitingCustomer(position);
            }
        });

        viewHolder.tvReschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationMainActivity) context).rescheduleWaitingCustomer(position);
            }
        });

        viewHolder.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(((NavigationMainActivity) context), position, appointmentsData, appointmentsData.getCustomerEndUser_FirstName() + " " + appointmentsData.getCustomerEndUser_LastName());
            }
        });


        return convertView;
    }


    private void showDialog(final Activity activity, final int pos, final ResponseModelAppointmentsData appointmentsData, String name) {
        LayoutInflater factory = LayoutInflater.from(activity);
        final View deleteDialogView = factory.inflate(R.layout.booking_cancel_dialog, null);
        final AlertDialog deleteDialog = new AlertDialog.Builder(activity).create();
        deleteDialog.setView(deleteDialogView);

        TextView tvName = (TextView) deleteDialogView.findViewById(R.id.tv_cancel_name);
        tvName.setText(name);

        final EditText etReason = (EditText) deleteDialogView.findViewById(R.id.et_cancel_reason);


        TextView tvSubmit = (TextView) deleteDialogView.findViewById(R.id.tv_cancel_submit);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strReason = etReason.getText().toString().trim();
                if (strReason.length() > 0) {
                    appointmentsData.setCancelReason(strReason);
                    Utility.cancelBooking(activity, appointmentsData, pos);

                    deleteDialog.dismiss();

                    ((NavigationMainActivity) context).refreshAppointmentData();

                } else {
                    Utility.showToast(getContext(), "Please enter the reason for cancellation.");
                }


            }
        });

        deleteDialog.show();

    }
}
