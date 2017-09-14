package com.abhi.fabkutbusiness.billing.controller;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class BillingAddStylistAdapter extends RecyclerView.Adapter<BillingAddStylistAdapter.MyViewHolder> {

    private List<ResponseModelEmployeeData> employeesDataList;
    private Activity context;
    private int selectedIndex = -1;
    private String selectedEmpId = "-1";

    public ResponseModelEmployeeData getSelectedStylist() {
        if (selectedIndex == -1)
            return null;
        else
            return employeesDataList.get(selectedIndex);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        CircleImageView ivProfileImage;
        ImageView ivTick;


        MyViewHolder(View view) {
            super(view);
            ivProfileImage = (CircleImageView) view.findViewById(R.id.iv_stylist_image);
            ivTick = (ImageView) view.findViewById(R.id.iv_stylist_tick);
            tvName = (TextView) view.findViewById(R.id.tv_stylist_name);
            selectedIndex = 0;
        }
    }


    public BillingAddStylistAdapter(List<ResponseModelEmployeeData> employeesDataList, Activity context) {
        this.employeesDataList = employeesDataList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stylist_list_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ResponseModelEmployeeData employeeData = employeesDataList.get(position);

        if (selectedEmpId.equals(employeeData.getEmp_id())) {
            selectedIndex = position;
            holder.ivTick.setVisibility(View.VISIBLE);
        } else {
            holder.ivTick.setVisibility(View.INVISIBLE);
        }

        holder.tvName.setText(employeeData.getEmp_name());

        Picasso.with(context)
                .load(employeeData.getEmp_profile_image())
                .placeholder(R.drawable.dummy_profile)
                .into(holder.ivProfileImage);


        holder.ivProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIndex = position;
                selectedEmpId = employeeData.getEmp_id();
                notifyDataSetChanged();
            }
        });

    }


    @Override
    public int getItemCount() {
        return employeesDataList.size();
    }


}