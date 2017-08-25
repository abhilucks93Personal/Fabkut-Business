package com.abhi.fabkutbusiness.booking.view;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.booking.controller.CustomerDataAdapter;
import com.abhi.fabkutbusiness.customer.view.AddCustomerActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointments;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfo;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

import java.util.ArrayList;

/**
 * Created by abhi on 17/04/17.
 */

public class BookNowActivity extends AppCompatActivity implements View.OnClickListener {

    View actionBarView;
    TextView tvTitle;
    ImageView iconLeft, iconRight;
    TextView tvAddServices;
    LinearLayout llMain;
    TextView tvName, tvEmail, tvGender, tvMobile, tvDesc, tvBookNow;
    AutoCompleteTextView actSearch;
    CustomerDataAdapter customerDataAdapter;
    ResponseModelAppointmentsData dataAppointment = new ResponseModelAppointmentsData();
    RelativeLayout rlServiceHeader;
    LinearLayout llServiceFooter;
    ImageView ivServiceArrow;
    LinearLayout llEditServices;
    TextView tvServicesName, tvServicesPrice, tvServicesDateSlots, tvServicesStylistName, tvEditServices, tvRemoveServices;
    ImageView ivServicesStylistImage;
    private String seatNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_now);

        setActionBarView();
        findViewById();
        initData();
    }


    private void initData() {

        seatNum = getIntent().getStringExtra("seatNum");

        ArrayList<ResponseModelCustomerData> data = Utility.getResponseModelCustomer(this, Constants.keySalonCustomerData).getData();
        customerDataAdapter = new CustomerDataAdapter(this, R.layout.simple_text_view, data);
        actSearch.setThreshold(1);
        actSearch.setAdapter(customerDataAdapter);
    }

    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Book Now");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        iconRight = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_right);
        iconRight.setImageDrawable(getResources().getDrawable(R.drawable.add_customers));
        iconRight.setVisibility(View.VISIBLE);
        iconRight.setOnClickListener(this);

        actSearch = (AutoCompleteTextView) findViewById(R.id.act_search);

        tvAddServices = (TextView) findViewById(R.id.tv_add_services);
        tvAddServices.setOnClickListener(this);

        llEditServices = (LinearLayout) findViewById(R.id.ll_edit_service);

        llMain = (LinearLayout) findViewById(R.id.book_now_ll_main);


        tvName = (TextView) findViewById(R.id.tv_name);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvGender = (TextView) findViewById(R.id.tv_gender);
        tvMobile = (TextView) findViewById(R.id.tv_mobile);
        tvDesc = (TextView) findViewById(R.id.tv_description);

        tvBookNow = (TextView) findViewById(R.id.tv_bookNow);
        tvBookNow.setOnClickListener(this);

        ivServiceArrow = (ImageView) findViewById(R.id.iv_service_arrow);
        rlServiceHeader = (RelativeLayout) findViewById(R.id.rl_service_header);
        rlServiceHeader.setOnClickListener(this);
        llServiceFooter = (LinearLayout) findViewById(R.id.ll_service_footer);

        tvServicesName = (TextView) findViewById(R.id.tv_services_name);
        tvServicesPrice = (TextView) findViewById(R.id.tv_services_price);
        tvServicesDateSlots = (TextView) findViewById(R.id.tv_services_date_slot);
        tvServicesStylistName = (TextView) findViewById(R.id.tv_services_stylist_name);
        tvEditServices = (TextView) findViewById(R.id.tv_edit_service);
        tvEditServices.setOnClickListener(this);
        tvRemoveServices = (TextView) findViewById(R.id.tv_remove_service);
        tvRemoveServices.setOnClickListener(this);
        ivServicesStylistImage = (ImageView) findViewById(R.id.iv_services_stylist_image);

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
                startActivityForResult(new Intent(BookNowActivity.this, AddCustomerActivity.class), 101);
                break;

            case R.id.tv_add_services:
                startActivityForResult(new Intent(BookNowActivity.this, AddBookingServiceActivity.class)
                        .putExtra("data", dataAppointment)
                        .putExtra("isEdit", false), 102);
                break;

            case R.id.tv_bookNow:
                if (tvAddServices.getVisibility() != View.VISIBLE) {
                    int seat = Integer.parseInt(seatNum);

                    for (ResponseModelRateInfoData rateData : dataAppointment.getServices()) {
                        rateData.setEmployee_name(dataAppointment.getEmployee().getEmp_name());
                        rateData.setEmployee_id(dataAppointment.getEmployee().getEmp_id());
                    }

                    if (seat >= 0) {

                        Utility.bookSeat(BookNowActivity.this, dataAppointment);

                    } else {
                        ResponseModelAppointments responseModelAppointments = Utility.getResponseModelAppointments(BookNowActivity.this, Constants.keySalonAppointmentsData);

                        if (responseModelAppointments != null) {
                            responseModelAppointments.getData().add(dataAppointment);
                        }
                        Utility.addPreferencesAppointmentsData(BookNowActivity.this, Constants.keySalonAppointmentsData, responseModelAppointments);
                    }

                    Utility.setEmployeeSelectedSlots(BookNowActivity.this, dataAppointment.getEmployee().getEmp_id(), dataAppointment.getSlots());

                    finish();
                } else {
                    Utility.showToast(BookNowActivity.this, "Please add services.");
                }

                break;

            case R.id.rl_service_header:

                if (llServiceFooter.getVisibility() == View.VISIBLE) {
                    ivServiceArrow.setImageDrawable(getResources().getDrawable(R.drawable.ser_close));
                    llServiceFooter.setVisibility(View.GONE);
                } else {
                    ivServiceArrow.setImageDrawable(getResources().getDrawable(R.drawable.ser_open));
                    llServiceFooter.setVisibility(View.VISIBLE);
                }

                break;


            case R.id.tv_remove_service:

                dataAppointment.setTotalAmount("" + 0);
                dataAppointment.setTotalTime("" + 0);
                //  dataAppointment.setBookingDate();
                dataAppointment.setServices(new ArrayList<ResponseModelRateInfoData>());
                dataAppointment.setEmployee(null);
                dataAppointment.setSlots(new ArrayList<String>());

                tvAddServices.setVisibility(View.VISIBLE);
                llEditServices.setVisibility(View.GONE);

                tvServicesName.setText("");
                tvServicesPrice.setText("");
                tvServicesStylistName.setText("");
                tvServicesDateSlots.setText("");

                break;

            case R.id.tv_edit_service:
                startActivityForResult(new Intent(BookNowActivity.this, AddBookingServiceActivity.class)
                        .putExtra("data", dataAppointment)
                        .putExtra("isEdit", true), 102);
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 101:
                if (resultCode == RESULT_OK) {

                    ResponseModelCustomerData responseModelCustomerData = (ResponseModelCustomerData) data.getSerializableExtra("data");

                    setData(responseModelCustomerData);
                }
                break;

            case 102:
                if (resultCode == RESULT_OK) {

                    dataAppointment = data.getParcelableExtra("data");
                    setServicesData();
                }
                break;
        }
    }

    private void setServicesData() {
        tvAddServices.setVisibility(View.GONE);
        llEditServices.setVisibility(View.VISIBLE);

        tvServicesName.setText(Utility.getFormattedServiceList(dataAppointment.getServices()));
        tvServicesPrice.setText("Price : " + dataAppointment.getTotalAmount() + "");
        tvServicesStylistName.setText(dataAppointment.getEmployee().getEmp_name());
        tvServicesDateSlots.setText(dataAppointment.getSlots().get(0));

    }

    public void setData(ResponseModelCustomerData responseModelCustomerData) {
        llMain.setVisibility(View.VISIBLE);

        tvAddServices.setVisibility(View.VISIBLE);
        llEditServices.setVisibility(View.GONE);

        actSearch.setText("");
        actSearch.clearFocus();
        Utility.hideKeyboard(BookNowActivity.this);

        tvName.setText(responseModelCustomerData.getEndUser_FirstName() + " " + responseModelCustomerData.getEndUser_LastName());
        tvMobile.setText(responseModelCustomerData.getContact_no());
        tvEmail.setText(responseModelCustomerData.getEmail());
        tvGender.setText(responseModelCustomerData.getGender());
        if (responseModelCustomerData.getAllergies().length() > 0) {
            tvDesc.setText("Allergic to :\n" + responseModelCustomerData.getAllergies());
        }

        dataAppointment = new ResponseModelAppointmentsData();
        dataAppointment.setBookingId(System.currentTimeMillis());
        dataAppointment.setCustomerEndUser_FirstName(responseModelCustomerData.getEndUser_FirstName());
        dataAppointment.setCustomerEndUser_LastName(responseModelCustomerData.getEndUser_LastName());
        dataAppointment.setBookingStatus(Constants.BOOKING_STATUS_CONFIRM);
        dataAppointment.setBookingType(Constants.BOOKING_TYPE_OFFLINE);
        dataAppointment.setCustomerMobile(responseModelCustomerData.getContact_no());
        dataAppointment.setSeatNumber(seatNum);

    }
}
