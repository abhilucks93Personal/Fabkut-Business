package com.abhi.fabkutbusiness.billing.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.billing.controller.BillingServicesAdapter;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
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
    TextView tvName, tvNumber, tvSubTotal, tvTax, tvPreviousBalance, tvOrderTotal;
    BillingServicesAdapter adapter;
    int subTotal = 0;
    Double taxPercentage = 18.0;
    int PreviousBalance;
    int orderTotal;
    int billingItemPos;

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

        tvName = (TextView) findViewById(R.id.tv_name);
        tvNumber = (TextView) findViewById(R.id.tv_number);

        listServices = (ListView) findViewById(R.id.list_services_billing);

        tvSubTotal = (TextView) findViewById(R.id.tv_subtotal);
        tvTax = (TextView) findViewById(R.id.tv_tax);
        tvPreviousBalance = (TextView) findViewById(R.id.tv_previous_balance);
        tvOrderTotal = (TextView) findViewById(R.id.tv_order_total);

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
        billingItemPos = getIntent().getIntExtra("pos", -1);
        taxPercentage = Utility.getPreferences(this, Constants.keySalonTaxPercentage, 18.0);
        PreviousBalance = Utility.getPreviousBalance(this, billingData.getCustomerId());

        adapter = new BillingServicesAdapter(BillDetailActivity.this, billingData.getServices());
        listServices.setAdapter(adapter);

        tvName.setText(billingData.getCustomerEndUser_FirstName() + " " + billingData.getCustomerEndUser_LastName());
        tvNumber.setText(billingData.getCustomerMobile());
        tvPreviousBalance.setText("Rs. " + PreviousBalance + " ");
        setPaymentData();

    }

    private void setPaymentData() {

        subTotal = Utility.getBillingSubTotalAmount(billingData.getServices());
        billingData.setTotalAmount("" + subTotal);
        tvSubTotal.setText("Rs. " + subTotal);

        int taxAmount = Utility.getTaxAmount(subTotal, taxPercentage);
        tvTax.setText("Rs. " + taxAmount + "");

        orderTotal = subTotal + taxAmount;
        tvOrderTotal.setText("Rs. " + orderTotal + "");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.actionbar_view_icon_left:
                finish();
                break;

            case R.id.tv_add_services:
                startActivityForResult(new Intent(BillDetailActivity.this, AddBillingServiceActivity.class), 101);
                break;

            case R.id.tv_pay:
                startActivityForResult(new Intent(BillDetailActivity.this, PaymentActivity.class)
                        .putExtra("data", billingData)
                        .putExtra("total", orderTotal)
                        .putExtra("pos", billingItemPos), 102);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                ResponseModelRateInfoData _data = data.getParcelableExtra("data");
                addService(_data);
            }
        } else if (requestCode == 102) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }

    public void showRemoveServiceDialog(final String serviceName) {

        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        dialog.setTitle("Alert");
        dialog.setMessage("Remove 1 service : " + serviceName);
        dialog.setCancelable(true);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (ResponseModelRateInfoData _data : billingData.getServices()) {
                    if (_data.getSub_service_name().equals(serviceName)) {
                        billingData.getServices().remove(_data);
                        break;
                    }
                }
                adapter.notifyDataSetChanged();
                setPaymentData();
                dialog.dismiss();
            }

        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });


        dialog.show();

    }

    private void addService(ResponseModelRateInfoData data) {
        billingData.getServices().add(data);
        adapter.notifyDataSetChanged();
        setPaymentData();

    }
}
