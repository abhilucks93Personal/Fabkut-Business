package com.abhi.fabkutbusiness.billing.view;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.billing.model.ResponseModelBillPaymentData;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointments;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

/**
 * Created by abhi on 17/04/17.
 */

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher, CompoundButton.OnCheckedChangeListener {

    View actionBarView;
    TextView tvTitle;
    ImageView iconLeft;
    EditText etPrice, etPaid, etUnpaid, etRemark;
    CheckBox cbWaved, cbInvoice;
    ResponseModelAppointmentsData data;
    int total;
    int unpaidAmount = 0;
    int paidAmount = 0;
    TextView tvSubmit;
    int billingItemPos;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment);

        setActionBarView();
        findViewById();
        initData();

    }

    private void initData() {
        data = getIntent().getParcelableExtra("data");
        total = getIntent().getIntExtra("total", 0);
        billingItemPos = getIntent().getIntExtra("pos", -1);
        etPrice.setText("" + total);
        etUnpaid.setText("0");
    }


    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Payment Details");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        etPrice = (EditText) findViewById(R.id.et_price);
        etPaid = (EditText) findViewById(R.id.et_paid);
        etPaid.addTextChangedListener(this);
        etUnpaid = (EditText) findViewById(R.id.et_unpaid);
        cbWaved = (CheckBox) findViewById(R.id.cb_waved);
        cbWaved.setOnCheckedChangeListener(this);
        cbInvoice = (CheckBox) findViewById(R.id.cb_invoice);

        etRemark = (EditText) findViewById(R.id.et_remark);

        tvSubmit = (TextView) findViewById(R.id.tv_submit);
        tvSubmit.setOnClickListener(this);


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
                if (etPaid.getText().length() > 0)
                    fetchData();
                else
                    Utility.showToast(PaymentActivity.this, "Please enter the paid amount.");
                break;
        }
    }

    private void fetchData() {

        ResponseModelBillPaymentData paymentData = new ResponseModelBillPaymentData();

        paymentData.setBillId("" + System.currentTimeMillis());
        paymentData.setBusinessId(data.getBusinessId());
        paymentData.setCustomerId(data.getCustomerId());
        paymentData.setCustomerName(data.getCustomerEndUser_FirstName() + " " + data.getCustomerEndUser_LastName());
        paymentData.setIsWaivedOff("" + cbWaved.isChecked());
        paymentData.setPaid("" + paidAmount);
        paymentData.setRemark(etRemark.getText().toString().trim());
        paymentData.setSubtotal(data.getTotalAmount());
        paymentData.setTax("" + (total - Integer.parseInt(data.getTotalAmount())));
        paymentData.setTotal("" + total);
        paymentData.setUnPaid("" + unpaidAmount);

        data.setBillPayment(paymentData);

        if (paidAmount == total) {
            completeBilling();
        } else if (paidAmount < total && cbWaved.isChecked()) {
            completeBilling();
        } else {
            if (Utility.isInternetConnected(PaymentActivity.this)) {
                updateOutstandingAmount();
            } else {
                showAlertDialog(Constants.msgBillingUnpaidAmountSync);
            }
        }

    }

    private void updateOutstandingAmount() {
        // call API
        Utility.showToast(PaymentActivity.this, "Updating Outstanding amount on server...");

        // onSuccess
        Utility.setPreviousBalance(this, data.getCustomerId(), unpaidAmount);
        completeBilling();
    }

    private void completeBilling() {
        Utility.releaseSeat(this, data.getSeatNumber(), billingItemPos);
        Utility.releaseEmployeeSelectedSlots(this, data.getEmployee().getEmp_id(), data.getSlots());
        Utility.setTotalRevenue(this, data.getCustomerId(), paidAmount);
        Utility.setTotalVisits(this, data.getCustomerId());
        Utility.updateBillingListData(this, data);
        if (cbInvoice.isChecked())
            startActivityForResult(new Intent(this, InvoiceActivity.class)
                    .putExtra("data", data), 101);
        else {
            setResult(RESULT_OK);
            finish();
        }
    }

    private void showAlertDialog(String msg) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();

        dialog.setTitle("Alert");
        dialog.setMessage(msg);
        dialog.setCancelable(true);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }

        });

        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {

            setResult(RESULT_OK);
            finish();

        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().length() > 0)
            paidAmount = Integer.parseInt(s.toString());
        else
            paidAmount = 0;
        unpaidAmount = total - paidAmount;

        if (!cbWaved.isChecked())
            etUnpaid.setText("" + unpaidAmount + "");
        else
            etUnpaid.setText("0");

        if (paidAmount > total) {
            cbWaved.setChecked(false);
            cbWaved.setEnabled(false);
        } else {
            cbWaved.setEnabled(true);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (!isChecked)
            etUnpaid.setText("" + unpaidAmount + "");
        else
            etUnpaid.setText("0");

    }
}
