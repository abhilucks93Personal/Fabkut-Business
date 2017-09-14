package com.abhi.fabkutbusiness.billing.view;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.billing.controller.BillingAddServicesAdapter;
import com.abhi.fabkutbusiness.billing.controller.BillingAddStylistAdapter;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

import java.util.ArrayList;

/**
 * Created by abhi on 17/04/17.
 */

public class AddBillingServiceActivity extends AppCompatActivity implements View.OnClickListener {

    View actionBarView;
    TextView tvTitle;
    ImageView iconLeft;
    RecyclerView rvStylist, rvServices;
    BillingAddStylistAdapter mAdapterStylist;
    BillingAddServicesAdapter mAdapterService;
    TextView tvSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_service_billing);

        setActionBarView();
        findViewById();
        initData();

    }

    private void initData() {
        ArrayList<ResponseModelEmployeeData> employeeDatas = Utility.getResponseModelEmployee(this, Constants.keySalonEmployeeData).getData();
        mAdapterStylist = new BillingAddStylistAdapter(employeeDatas, this);
        rvStylist.setAdapter(mAdapterStylist);

        ArrayList<ResponseModelRateInfoData> data = Utility.getResponseModelRateInfo(this, Constants.keySalonRateInfoData).getData();
        mAdapterService = new BillingAddServicesAdapter(data, R.layout.item_service_list, this);
        rvServices.setAdapter(mAdapterService);
    }

    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Add Service");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        rvStylist = (RecyclerView) findViewById(R.id.rv_stylist);

        rvServices = (RecyclerView) findViewById(R.id.rv_services);

        tvSubmit = (TextView) findViewById(R.id.tv_submit);
        tvSubmit.setOnClickListener(this);


    }

    private void fetchData() {

        ResponseModelEmployeeData employeeData = mAdapterStylist.getSelectedStylist();
        if (employeeData == null) {
            Utility.showToast(this, "Please select stylist!");
            return;
        }

        ResponseModelRateInfoData serviceData = mAdapterService.getSelectedService();
        if (serviceData == null) {
            Utility.showToast(this, "Please select service!");
            return;
        }

        serviceData.setEmployee_name(employeeData.getEmp_name());
        serviceData.setEmployee_id(employeeData.getEmp_id());

        Intent intent = new Intent();
        intent.putExtra("data", serviceData);
        setResult(RESULT_OK, intent);
        finish();

    }

    private void setActionBarView() {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.actionbar_view_icon_left:
                finish();
                break;

            case R.id.tv_submit:
                fetchData();
                break;
        }
    }
}
