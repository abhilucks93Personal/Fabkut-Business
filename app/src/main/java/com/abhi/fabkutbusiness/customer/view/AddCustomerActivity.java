package com.abhi.fabkutbusiness.customer.view;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomer;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;

import java.util.ArrayList;

/**
 * Created by abhi on 17/04/17.
 */

public class AddCustomerActivity extends AppCompatActivity implements View.OnClickListener {

    View actionBarView;
    TextView tvTitle;
    ImageView iconLeft, iconRight;
    EditText etFirstName, etLastName, etEmail, etMobile, etAllergies;
    RadioButton rbMale, rbFemale;
    TextView tvSave;
    String businessId, businessName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_customer);

        setActionBarView();
        findViewById();
        initData();

    }

    private void initData() {
        businessId = Utility.getPreferences(this, Constants.keySalonBusinessId);
        businessName = Utility.getPreferences(this, Constants.keySalonBusinessName);
    }

    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Add Customer");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        etFirstName = (EditText) findViewById(R.id.et_firstName);
        etLastName = (EditText) findViewById(R.id.et_lastName);
        etEmail = (EditText) findViewById(R.id.et_email);
        etMobile = (EditText) findViewById(R.id.et_mobile);
        etAllergies = (EditText) findViewById(R.id.et_allergies);

        rbMale = (RadioButton) findViewById(R.id.rb_male);
        rbFemale = (RadioButton) findViewById(R.id.rb_female);

        tvSave = (TextView) findViewById(R.id.tv_save);
        tvSave.setOnClickListener(this);

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

            case R.id.tv_save:
                saveData();
                break;
        }
    }

    private void saveData() {


        String strFirstName = etFirstName.getText().toString().trim();
        String strLastName = etLastName.getText().toString().trim();
        String strEmail = etEmail.getText().toString().trim();
        String strMobile = etMobile.getText().toString().trim();
        String strAllergies = etAllergies.getText().toString().trim();
        String strGender;
        if (rbMale.isChecked())
            strGender = "Male";
        else
            strGender = "Female";


        if (isValidated(strFirstName, strLastName, strEmail, strMobile, strAllergies, strGender)) {


            ResponseModelCustomerData responseModelCustomerData = new ResponseModelCustomerData(businessId, businessName, strFirstName + " " + strLastName, "", strGender, strEmail, strMobile, strAllergies);

            ResponseModelCustomer responseModelCustomer = Utility.getResponseModelCustomer(this, Constants.keySalonCustomerData);

            responseModelCustomer.getData().add(responseModelCustomerData);

            Utility.addPreferencesCustomerData(this, Constants.keySalonCustomerData, responseModelCustomer);

            String seatNum = Utility.getEmptySeatNum(this);

            startActivity(new Intent(AddCustomerActivity.this, BookNowActivity.class)
                    .putExtra("data", responseModelCustomerData)
                    .putExtra("seatNum", seatNum));

          /*  Intent intent = new Intent();
            intent.putExtra("data", responseModelCustomerData);
            setResult(RESULT_OK, intent);*/
            finish();

        }
    }

    private boolean isValidated(String strFirstName, String strLastName, String strEmail, String strMobile, String strAllergies, String strGender) {

        if (strFirstName.length() == 0) {
            Utility.showToast(this, "Please enter the First Name.");
            return false;
        }

        if (!Utility.isValidEmail(strEmail)) {
            Utility.showToast(this, "Please enter the valid email.");
            return false;
        }

        if (strMobile.length() <10) {
            Utility.showToast(this, "Please enter the valid Mobile number");
            return false;
        }


        return true;
    }
}
