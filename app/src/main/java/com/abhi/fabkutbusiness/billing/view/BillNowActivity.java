package com.abhi.fabkutbusiness.billing.view;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.billing.controller.BillingAdapter;
import com.abhi.fabkutbusiness.billing.model.BillingData;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;

import java.util.ArrayList;

/**
 * Created by abhi on 17/04/17.
 */

public class BillNowActivity extends AppCompatActivity implements View.OnClickListener {

    View actionBarView;
    TextView tvTitle;
    ImageView iconLeft;
    EditText etSearch;
    ListView listBilling;
    ArrayList<ResponseModelAppointmentsData> billingData = new ArrayList<>();
    private BillingAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bill_now);

        setActionBarView();
        findViewById();
        initData();

    }

    private void initData() {

        billingData = Utility.getResponseModelBookings(BillNowActivity.this, Constants.keySalonBookingData).getData();

        adapter = new BillingAdapter(BillNowActivity.this, billingData);
        listBilling.setAdapter(adapter);
    }

    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Billing");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        etSearch = (EditText) findViewById(R.id.et_search);
        listBilling = (ListView) findViewById(R.id.list_billing);

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
        }
    }

    public void refreshData() {
        billingData.clear();
        billingData.addAll(Utility.getResponseModelBookings(BillNowActivity.this, Constants.keySalonBookingData).getData());
        adapter.notifyDataSetChanged();
    }
}
