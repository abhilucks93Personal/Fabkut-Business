package com.abhi.fabkutbusiness.main;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointments;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomer;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployee;
import com.abhi.fabkutbusiness.main.model.ResponseModelLogin;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfo;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;

/**
 * Created by abhi on 14/04/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, RetrofitApi.ResponseListener {

    View actionBarView;
    EditText etEmail, etPassword;
    TextView tvForgotPassword, tvLogin;
    private ProgressDialog mProgressDialog;
    private String businessId, businessName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        setActionBarView();
        findViewById();

    }

    private void findViewById() {

        etEmail = (EditText) findViewById(R.id.login_etEmail);
        etPassword = (EditText) findViewById(R.id.login_etPassword);
        tvForgotPassword = (TextView) findViewById(R.id.login_tvForgotPassword);
        tvForgotPassword.setOnClickListener(this);
        tvLogin = (TextView) findViewById(R.id.login_tvLogin);
        tvLogin.setOnClickListener(this);
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
            case R.id.login_tvForgotPassword:
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                break;

            case R.id.login_tvLogin:

                login();


                break;
        }
    }

    private void login() {

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (isValidated(email, password)) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Fetching Data");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            RetrofitApi.getInstance().loginApiMethod(this, this, email, password);
        }
    }

    private boolean isValidated(String email, String password) {

        if (email.length() == 0 || password.length() == 0) {
            Utility.showToast(this, Constants.errorMsgMandatory);
            return false;
        }

        return true;
    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {
        callRollback(Constants.errorMsgWrong);
    }

    @Override
    public void _onNext(Object obj) {
        if (obj instanceof ResponseModelLogin) {
            ResponseModelLogin modelLogin = (ResponseModelLogin) obj;
            if (modelLogin.getSTATUS().equals("200")) {

                Utility.addPreferencesUserData(LoginActivity.this, Constants.keySalonProfileData, modelLogin);

                businessId = modelLogin.getData().get(0).getBusiness_id();
                Utility.addPreferences(LoginActivity.this, Constants.keySalonBusinessId, businessId);

                businessName = modelLogin.getData().get(0).getBusiness_name();
                Utility.addPreferences(LoginActivity.this, Constants.keySalonBusinessName, businessName);

                String openTime = modelLogin.getData().get(0).getOpening_hours();
                Utility.addPreferences(LoginActivity.this, Constants.keySalonOpenTime, openTime);

                String closeTime = modelLogin.getData().get(0).getClosing_hours();
                Utility.addPreferences(LoginActivity.this, Constants.keySalonCloseTime, closeTime);

                String seatsNum = modelLogin.getData().get(0).getSeats();
                Utility.addPreferences(LoginActivity.this, Constants.keySalonSeatsNum, seatsNum);

                Double tax_percentage = modelLogin.getData().get(0).getTax_percentage();
                Utility.addPreferences(LoginActivity.this, Constants.keySalonTaxPercentage, "" + tax_percentage);


                int seats = Integer.parseInt(seatsNum);
                ArrayList<String> seatStatusList = new ArrayList<>();
                for (int i = 0; i < seats; i++) {
                    seatStatusList.add("0");
                }
                Utility.addPreferencesSeatStatusData(LoginActivity.this, Constants.keySalonSeatsStatusList, seatStatusList);

                ResponseModelAppointments responseModelAppointments = new ResponseModelAppointments();
                responseModelAppointments.setData(new ArrayList<ResponseModelAppointmentsData>());
                Utility.addPreferencesAppointmentsData(LoginActivity.this, Constants.keySalonAppointmentsData, responseModelAppointments);
                Utility.addPreferencesBookingData(LoginActivity.this, Constants.keySalonBookingData, responseModelAppointments);
                Utility.addPreferencesCancelBookingData(LoginActivity.this, Constants.keySalonCancelBookingData, responseModelAppointments);

                RetrofitApi.getInstance().customerApiMethod(this, this, businessId);

            } else {
                callRollback(modelLogin.getMESSAGE());
            }
        } else if (obj instanceof ResponseModelCustomer) {
            ResponseModelCustomer modelCustomer = (ResponseModelCustomer) obj;
            if (modelCustomer.getSTATUS().equals("200")) {

                // ask to send customerId from backend

                for (ResponseModelCustomerData data : modelCustomer.getData()) {
                    if (data.getCustomerId() == null)
                        data.setCustomerId(data.getContact_no());
                }

                Utility.addPreferencesCustomerData(LoginActivity.this, Constants.keySalonCustomerData, modelCustomer);
                RetrofitApi.getInstance().employeeApiMethod(this, this, businessId);

            } else {
                callRollback(modelCustomer.getMESSAGE());
            }
        } else if (obj instanceof ResponseModelEmployee) {
            ResponseModelEmployee modelEmployee = (ResponseModelEmployee) obj;
            if (modelEmployee.getSTATUS().equals("200")) {
                Utility.addPreferencesEmployeeData(LoginActivity.this, Constants.keySalonEmployeeData, modelEmployee);
                RetrofitApi.getInstance().rateInfoApiMethod(this, this, businessId);

            } else {
                callRollback(modelEmployee.getMESSAGE());
            }
        } else if (obj instanceof ResponseModelRateInfo) {
            ResponseModelRateInfo modelRateInfo = (ResponseModelRateInfo) obj;
            if (modelRateInfo.getSTATUS().equals("200")) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Utility.addPreferencesRateInfoData(LoginActivity.this, Constants.keySalonRateInfoData, modelRateInfo);
                Utility.addPreferences(LoginActivity.this, Constants.keyLoginCheck, true);
                startActivity(new Intent(LoginActivity.this, NavigationMainActivity.class));
                finish();
            } else {
                callRollback(modelRateInfo.getMESSAGE());
            }
        }
    }

    private void callRollback(String msg) {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
        Utility.showToast(this, msg);
        Utility.clearPreferenceData(getApplicationContext());
    }


}
