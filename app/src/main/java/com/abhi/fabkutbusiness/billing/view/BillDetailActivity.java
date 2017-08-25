package com.abhi.fabkutbusiness.billing.view;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.billing.controller.BillingServicesAdapter;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfo;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

/**
 * Created by abhi on 17/04/17.
 */

public class BillDetailActivity extends AppCompatActivity implements View.OnClickListener {

    View actionBarView;
    TextView tvTitle;
    ImageView iconLeft;
    ListView listServices;
    ResponseModelAppointmentsData billingData;
    TextView tvAddServices, tvPay;
    TextView tvName, tvNumber;
    BillingServicesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bill_detail);

        setActionBarView();
        findViewById();
        initData();


    }


    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Billing");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        listServices = (ListView) findViewById(R.id.list_services_billing);

        tvName = (TextView) findViewById(R.id.tv_name);
        tvNumber = (TextView) findViewById(R.id.tv_number);

        tvAddServices = (TextView) findViewById(R.id.tv_add_services);
        tvAddServices.setOnClickListener(this);
        tvPay = (TextView) findViewById(R.id.tv_pay);
        tvPay.setOnClickListener(this);

    }

    private void setActionBarView() {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }

    private void initData() {

        billingData = getIntent().getParcelableExtra("data");

        adapter = new BillingServicesAdapter(BillDetailActivity.this, billingData.getServices());
        listServices.setAdapter(adapter);

        tvName.setText(billingData.getCustomerEndUser_FirstName() + " " + billingData.getCustomerEndUser_LastName());
        tvNumber.setText(billingData.getCustomerMobile());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.actionbar_view_icon_left:
                finish();
                break;

            case R.id.tv_add_services:

                startActivity(new Intent(BillDetailActivity.this, AddBillingServiceActivity.class));
                break;

            case R.id.tv_pay:
                startActivity(new Intent(BillDetailActivity.this, PaymentActivity.class));
                break;
        }
    }

    public void removeService(String serviceName) {

        for (ResponseModelRateInfoData _data : billingData.getServices()) {
            if (_data.getSub_service_name().equals(serviceName)) {
                billingData.getServices().remove(_data);
                break;
            }
        }
        adapter.notifyDataSetChanged();

    }
}
