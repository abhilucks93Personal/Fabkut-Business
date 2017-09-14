package com.abhi.fabkutbusiness.billing.view;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.billing.controller.InvoiceServicesAdapter;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;

/**
 * Created by abhi on 17/04/17.
 */

public class InvoiceActivity extends AppCompatActivity implements View.OnClickListener {

    View actionBarView;
    TextView tvTitle;
    ImageView iconLeft, iconRight;
    RecyclerView listServices;
    InvoiceServicesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_invoice);

        setActionBarView();
        findViewById();
        initData();
    }

    private void initData() {

        ResponseModelAppointmentsData data = getIntent().getParcelableExtra("data");

        adapter = new InvoiceServicesAdapter(data.getServices(), R.layout.item_services_invoice);
        listServices.setAdapter(adapter);
    }

    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Invoice");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        iconRight = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_right);
        iconRight.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_save));
        iconRight.setVisibility(View.VISIBLE);
        iconRight.setOnClickListener(this);

        listServices = (RecyclerView) findViewById(R.id.rv_invoice_services);
        listServices.setNestedScrollingEnabled(false);

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

            case R.id.actionbar_view_icon_right:
                saveInvoice();
                break;
        }
    }

    private void saveInvoice() {
        Utility.showToast(this, "Invoice saved");
    }


}
