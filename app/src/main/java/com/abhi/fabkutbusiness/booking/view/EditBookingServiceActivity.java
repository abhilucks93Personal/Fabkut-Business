package com.abhi.fabkutbusiness.booking.view;

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
import com.abhi.fabkutbusiness.booking.controller.ServicesAdapter;
import com.abhi.fabkutbusiness.booking.controller.SlotAdapter;
import com.abhi.fabkutbusiness.booking.controller.StylistAdapter;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointments;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

import java.util.ArrayList;

/**
 * Created by abhi on 17/04/17.
 */

public class EditBookingServiceActivity extends AppCompatActivity implements View.OnClickListener {

    View actionBarView;
    TextView tvTitle;
    ImageView iconLeft;
    TextView tvSelectService, tvBooking;
    TextView tvTime, tvTotal;
    RecyclerView rvServices, rvStylist, rvSlots;
    StylistAdapter mAdapterStylist;
    TextView tvMorning, tvAfternoon, tvEvening;
    ServicesAdapter mAdapterServices;
    SlotAdapter mAdapterSlots;
    ArrayList<ResponseModelRateInfoData> rateInfoDatas;
    ArrayList<String> slots;
    int totalTime = 0, totalCost = 0;
    ResponseModelAppointmentsData data = new ResponseModelAppointmentsData();
    private ArrayList<String> bookedSlots = new ArrayList<>();
    private String bookingDate;
    private ArrayList<String> elapsedSlots;
    private int reschedulePos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_service_booking);

        setActionBarView();
        findViewById();
        getData();
        initUi();
    }

    private void getData() {

        reschedulePos = getIntent().getIntExtra("pos", -1);
        data = getIntent().getParcelableExtra("data");
        if (data == null) {
            finish();
        }


    }

    private void setData() {
        setServiceTotal(Integer.parseInt(data.getTotalAmount()), Integer.parseInt(data.getTotalTime()));
        mAdapterStylist.setSelectedStylistDataList(data.getEmployee());
        mAdapterSlots.setSlotFullSelection(data.getSlots());
        ArrayList<ResponseModelRateInfoData> rateInfoDatas = data.getServices();
        if (rateInfoDatas.size() == 0)
            setServiceTotal(0, 0);
        this.rateInfoDatas.clear();
        this.rateInfoDatas.addAll(rateInfoDatas);
        mAdapterServices.notifyDataSetChanged();
    }

    private void initUi() {

        rateInfoDatas = new ArrayList<>();
        mAdapterServices = new ServicesAdapter(rateInfoDatas, new ArrayList<ResponseModelRateInfoData>(), R.layout.item_selected_service_list, true, this);
        rvServices.setAdapter(mAdapterServices);

        slots = new ArrayList<>();
        ArrayList<String> selectedSlots = new ArrayList<>();
        selectedSlots.addAll(data.getSlots());
        bookingDate = data.getBookingDate();
        elapsedSlots = Utility.getElapsedSlots(this);
        mAdapterSlots = new SlotAdapter(this, R.layout.item_slots, slots, elapsedSlots, selectedSlots, bookedSlots, true, bookingDate);
        rvSlots.setAdapter(mAdapterSlots);
        initSlotUi(Utility.getCurrentDate("dd/MM/yyyy"));

        ArrayList<ResponseModelEmployeeData> employeeDatas = Utility.getResponseModelEmployee(this, Constants.keySalonEmployeeData).getData();
        mAdapterStylist = new StylistAdapter(employeeDatas, this);
        rvStylist.setAdapter(mAdapterStylist);

        setServiceTotal(0, 0);

        setData();

    }

    private void initSlotUi(String date) {
        String openTime = Utility.getPreferences(EditBookingServiceActivity.this, Constants.keySalonOpenTime);
        ArrayList<String> morningSlots = Utility.getFormattedTimeSlots(date, openTime, Constants.timeStartAfternoon, Constants.displayDateFormatWithTime);

        if (elapsedSlots.containsAll(morningSlots)) {

            tvMorning.setTextColor(getResources().getColor(R.color.colorGrey));
            tvMorning.setEnabled(false);

            ArrayList<String> afternoonSlots = Utility.getFormattedTimeSlots(date, Constants.timeStartAfternoon, Constants.timeEndAfternoon, Constants.displayDateFormatWithTime);

            if (elapsedSlots.containsAll(afternoonSlots)) {

                tvAfternoon.setTextColor(getResources().getColor(R.color.colorGrey));
                tvAfternoon.setEnabled(false);
                String closeTime = Utility.getPreferences(EditBookingServiceActivity.this, Constants.keySalonCloseTime);
                ArrayList<String> eveningSlots = Utility.getFormattedTimeSlots(date, Constants.timeEndAfternoon, closeTime, Constants.displayDateFormatWithTime);

                if (elapsedSlots.containsAll(eveningSlots)) {
                    tvEvening.setTextColor(getResources().getColor(R.color.colorGrey));
                    tvEvening.setEnabled(false);
                } else {
                    tvEvening.performClick();
                }
            } else {
                tvAfternoon.performClick();
            }

        } else {
            tvMorning.performClick();
        }
    }

    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Edit Services");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        tvSelectService = (TextView) findViewById(R.id.tv_select_service);
        tvSelectService.setOnClickListener(this);

        tvBooking = (TextView) findViewById(R.id.tv_booking);
        tvBooking.setOnClickListener(this);

        tvTime = (TextView) findViewById(R.id.tv_time);
        tvTotal = (TextView) findViewById(R.id.tv_total);
        rvServices = (RecyclerView) findViewById(R.id.rv_services);
        rvServices.setNestedScrollingEnabled(false);

        rvStylist = (RecyclerView) findViewById(R.id.rv_stylist);

        tvMorning = (TextView) findViewById(R.id.tv_morning);
        tvMorning.setOnClickListener(this);
        tvAfternoon = (TextView) findViewById(R.id.tv_afternoon);
        tvAfternoon.setOnClickListener(this);
        tvEvening = (TextView) findViewById(R.id.tv_evening);
        tvEvening.setOnClickListener(this);
        rvSlots = (RecyclerView) findViewById(R.id.rv_slots);

    }

    private void setActionBarView() {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }

    public void setServiceTotal(int totalCost, int totalTime) {
        this.totalTime = totalTime;
        this.totalCost = totalCost;
        tvTime.setText("Time : " + totalTime + "min");
        tvTotal.setText("Total : " + totalCost + "Rs.");
        mAdapterSlots.setSlotSelection(getSelectedSlotCount(totalTime));
    }

    private int getSelectedSlotCount(int totalTime) {
        int count = 0;

        if (totalTime != 0)
            count = (int) Math.ceil(totalTime / Constants.slotDifference);

        return count;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.actionbar_view_icon_left:
                finish();
                break;

            case R.id.tv_select_service:
                startActivityForResult(new Intent(EditBookingServiceActivity.this, SelectServiceActivity.class)
                        .putExtra("data", rateInfoDatas), 101);
                break;

            case R.id.tv_morning:
                setSlot(0);
                break;

            case R.id.tv_afternoon:
                setSlot(1);
                break;

            case R.id.tv_evening:
                setSlot(2);
                break;

            case R.id.tv_booking:
                getSelectedData();
                break;
        }
    }

    private void getSelectedData() {
        if (isValidated()) {
            data.setTotalAmount("" + totalCost);
            data.setTotalTime("" + totalTime);
            data.setServices(rateInfoDatas);
            data.setEmployee(mAdapterStylist.getSelectedStylistDataList());
            data.setSlots(mAdapterSlots.getSelectedSlotList());


            ResponseModelAppointments responseModelAppointments = Utility.getResponseModelAppointments(EditBookingServiceActivity.this, Constants.keySalonAppointmentsData);

            if (responseModelAppointments != null) {

                responseModelAppointments.getData().set(reschedulePos, data);
                Utility.addPreferencesAppointmentsData(EditBookingServiceActivity.this, Constants.keySalonAppointmentsData, responseModelAppointments);
            }


            Intent intent = new Intent();
            intent.putExtra("data", data);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private boolean isValidated() {

        if (rateInfoDatas == null) {
            Utility.showToast(EditBookingServiceActivity.this, "Please select services");
            return false;
        }

        if (rateInfoDatas.size() == 0) {
            Utility.showToast(EditBookingServiceActivity.this, "Please select services");
            return false;
        }

        if (mAdapterStylist.getSelectedStylistDataList() == null) {
            Utility.showToast(EditBookingServiceActivity.this, "Please select a Stylist");
            return false;
        }


        if (mAdapterSlots.getSelectedSlotList() == null) {
            Utility.showToast(EditBookingServiceActivity.this, "Please select Time Slots");
            return false;
        }

        if (mAdapterSlots.getSelectedSlotList().size() == 0) {
            Utility.showToast(EditBookingServiceActivity.this, "Please select Time Slots");
            return false;
        }

        return true;
    }

    private void setSlot(int slot) {
        slots.clear();

        switch (slot) {

            case 0:
                // Morning Slot
                tvMorning.setTextColor(getResources().getColor(R.color.colorBlue2));
                tvAfternoon.setTextColor(getResources().getColor(R.color.colorBlack));
                tvEvening.setTextColor(getResources().getColor(R.color.colorBlack));
                String openTime = Utility.getPreferences(EditBookingServiceActivity.this, Constants.keySalonOpenTime);
                slots.addAll(Utility.getTimeSlots(openTime, Constants.timeStartAfternoon));
                mAdapterSlots.notifyDataSetChanged();
                break;

            case 1:
                // Afternoon Slot
                tvMorning.setTextColor(getResources().getColor(R.color.colorBlack));
                tvAfternoon.setTextColor(getResources().getColor(R.color.colorBlue2));
                tvEvening.setTextColor(getResources().getColor(R.color.colorBlack));
                slots.addAll(Utility.getTimeSlots(Constants.timeStartAfternoon, Constants.timeEndAfternoon));
                mAdapterSlots.notifyDataSetChanged();
                break;

            case 2:
                // Evening Slot
                tvMorning.setTextColor(getResources().getColor(R.color.colorBlack));
                tvAfternoon.setTextColor(getResources().getColor(R.color.colorBlack));
                tvEvening.setTextColor(getResources().getColor(R.color.colorBlue2));
                String closeTime = Utility.getPreferences(EditBookingServiceActivity.this, Constants.keySalonCloseTime);
                slots.addAll(Utility.getTimeSlots(Constants.timeEndAfternoon, closeTime));
                mAdapterSlots.notifyDataSetChanged();
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 101:

                if (resultCode == RESULT_OK) {

                    ArrayList<ResponseModelRateInfoData> rateInfoDatas = data.getParcelableArrayListExtra("dataList");
                    if (rateInfoDatas.size() == 0)
                        setServiceTotal(0, 0);
                    this.rateInfoDatas.clear();
                    this.rateInfoDatas.addAll(rateInfoDatas);
                    mAdapterServices.notifyDataSetChanged();
                }

                break;
        }
    }


    public void setDisabledSlotList(ArrayList<String> bookedSlots) {
        this.bookedSlots.clear();
        this.bookedSlots.addAll(bookedSlots);
        mAdapterSlots.setDisabledSlotList(bookedSlots);
    }


}
