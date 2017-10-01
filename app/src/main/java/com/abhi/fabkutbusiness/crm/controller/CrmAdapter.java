package com.abhi.fabkutbusiness.crm.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.crm.model.CrmList_model;
import com.abhi.fabkutbusiness.crm.view.CrmTab;

import java.util.ArrayList;


public class CrmAdapter extends
        RecyclerView.Adapter<CrmAdapter.MyViewHolder>{

    private ArrayList<CrmList_model> dataItem;
    private Context mContext;
    private LayoutInflater inflater;




    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder   {
        public TextView name,contact,vist,profile,email;



        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_name);
            contact = (TextView) view.findViewById(R.id.crmlist_mobileNo_);
            vist = (TextView) view.findViewById(R.id.crmlist_visit);
            profile = (TextView) view.findViewById(R.id.crmlist_profile);
            email= (TextView) view.findViewById(R.id.tv_email);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, CrmTab.class);
                    intent.putExtra("item",dataItem.get(getLayoutPosition()).getEnduser_id());
                    context.startActivity(intent);

                }
            });

        }



    }


    public CrmAdapter(Context mContext,ArrayList<CrmList_model> dataItem) {
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
        this.dataItem = dataItem;


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        CrmList_model c = dataItem.get(position);
        holder.name.setText(c.getEnduser_name());
        holder.contact.setText(c.getContact_no());
        holder.email.setText(c.getEmail());
        holder.profile.setText("Profile: "+c.getProfile_Comp_total()+" %");
        holder.vist.setText("Total Visit : " + c.getTotalVisit());


    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crm_list_item,parent, false);

        return new MyViewHolder(v);
    }
}



