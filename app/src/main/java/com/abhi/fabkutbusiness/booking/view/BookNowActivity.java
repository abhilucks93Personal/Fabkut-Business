package com.abhi.fabkutbusiness.booking.view;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.booking.controller.CustomerDataAdapter;
import com.abhi.fabkutbusiness.booking.controller.ServicesAdapter;
import com.abhi.fabkutbusiness.booking.controller.SlotAdapter;
import com.abhi.fabkutbusiness.booking.controller.StylistAdapter;
import com.abhi.fabkutbusiness.customer.view.AddCustomerActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointments;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfo;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

import java.util.ArrayList;

/**
 * Created by abhi on 17/04/17.
 */

public class BookNowActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    View actionBarView;
    TextView tvTitle;
    ImageView iconLeft;
    LinearLayout llMain, llDetails;
    TextView tvName, tvEmail, tvGender, tvMobile, tvTotalVisits, tvTotalRevenue, tvDesc, tvBookNow;
    ResponseModelAppointmentsData dataAppointment = new ResponseModelAppointmentsData();
    String seatNum;

    TextView tvSelectService;
    TextView tvTime, tvTotal;
    RecyclerView rvServices, rvStylist, rvSlots;
    StylistAdapter mAdapterStylist;
    TextView tvMorning, tvAfternoon, tvEvening;
    ServicesAdapter mAdapterServices;
    SlotAdapter mAdapterSlots;
    ArrayList<ResponseModelRateInfoData> rateInfoDatas;
    ArrayList<String> slots, elapsedSlots;
    int totalTime = 0, totalCost = 0;
    ArrayList<String> bookedSlots = new ArrayList<>();
    TextView tvDate;
    private String currentDate;
    ResponseModelAppointmentsData previousAppointmentData;
    int reschedulePos;
    boolean isEdit;
    int activePosition = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_now);

        setActionBarView();
        findViewById();
        initData();
    }


    private void initData() {

        isEdit = getIntent().getBooleanExtra("isEdit", false);

        if (isEdit) {
            previousAppointmentData = getIntent().getParcelableExtra("data");
            reschedulePos = getIntent().getIntExtra("pos", -1);
            setEditData(previousAppointmentData);
            tvDate.setText(currentDate);
            rateInfoDatas = new ArrayList<>();
            rateInfoDatas.addAll(previousAppointmentData.getServices());

        } else {
            ResponseModelCustomerData responseModelCustomerData = (ResponseModelCustomerData) getIntent().getSerializableExtra("data");
            setData(responseModelCustomerData);
            tvDate.setText(currentDate);
            rateInfoDatas = new ArrayList<>();

        }

        currentDate = Utility.getCurrentDate(Constants.displayDateFormat);
        tvDate.setText(currentDate);

        mAdapterServices = new ServicesAdapter(rateInfoDatas, new ArrayList<ResponseModelRateInfoData>(), R.layout.item_selected_service_list, true, this);
        rvServices.setAdapter(mAdapterServices);


        slots = new ArrayList<>();
        ArrayList<String> selectedSlots = new ArrayList<>();

        elapsedSlots = Utility.getElapsedSlots(this);
        if (elapsedSlots.size() == 0) {
            Utility.showToast(this, "Sorry! Can't book services after closing time.");

        }

        mAdapterSlots = new SlotAdapter(this, R.layout.item_slots, slots, elapsedSlots, selectedSlots, bookedSlots, false, currentDate);
        rvSlots.setAdapter(mAdapterSlots);

        initSlotUi(Utility.getCurrentDate("dd/MM/yyyy"));

        ArrayList<ResponseModelEmployeeData> employeeDatas = Utility.getResponseModelEmployee(this, Constants.keySalonEmployeeData).getData();
        mAdapterStylist = new StylistAdapter(employeeDatas, this);
        rvStylist.setAdapter(mAdapterStylist);


        setServiceTotal(totalCost, totalTime);

    }

    private void initSlotUi(String date) {

        elapsedSlots = Utility.getElapsedSlots(this);

        String openTime = Utility.getPreferences(BookNowActivity.this, Constants.keySalonOpenTime);
        ArrayList<String> morningSlots = Utility.getFormattedTimeSlots(date, openTime, Constants.timeStartAfternoon, Constants.displayDateFormatWithTime);

        if (elapsedSlots.containsAll(morningSlots)) {

            tvMorning.setTextColor(getResources().getColor(R.color.colorGrey));
            tvMorning.setEnabled(false);

            ArrayList<String> afternoonSlots = Utility.getFormattedTimeSlots(date, Constants.timeStartAfternoon, Constants.timeEndAfternoon, Constants.displayDateFormatWithTime);

            if (elapsedSlots.containsAll(afternoonSlots)) {

                tvAfternoon.setTextColor(getResources().getColor(R.color.colorGrey));
                tvAfternoon.setEnabled(false);
                String closeTime = Utility.getPreferences(BookNowActivity.this, Constants.keySalonCloseTime);
                ArrayList<String> eveningSlots = Utility.getFormattedTimeSlots(date, Constants.timeEndAfternoon, closeTime, Constants.displayDateFormatWithTime);

                if (elapsedSlots.containsAll(eveningSlots)) {
                    tvEvening.setTextColor(getResources().getColor(R.color.colorGrey));
                    tvEvening.setEnabled(false);
                } else {
                    tvEvening.setEnabled(true);
                    tvEvening.setTextColor(getResources().getColor(R.color.colorBlack));
                    tvEvening.performClick();
                }
            } else {
                tvAfternoon.setEnabled(true);
                tvAfternoon.setTextColor(getResources().getColor(R.color.colorBlack));
                tvEvening.setEnabled(true);
                tvEvening.setTextColor(getResources().getColor(R.color.colorBlack));
                tvAfternoon.performClick();
            }

        } else {
            tvMorning.setEnabled(true);
            tvMorning.setTextColor(getResources().getColor(R.color.colorBlack));
            tvAfternoon.setEnabled(true);
            tvAfternoon.setTextColor(getResources().getColor(R.color.colorBlack));
            tvEvening.setEnabled(true);
            tvEvening.setTextColor(getResources().getColor(R.color.colorBlack));
            tvMorning.performClick();
        }
    }

    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Book Now");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        llMain = (LinearLayout) findViewById(R.id.book_now_ll_main);
        llDetails = (LinearLayout) findViewById(R.id.book_now_ll_details);

        tvName = (TextView) findViewById(R.id.tv_name);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvGender = (TextView) findViewById(R.id.tv_gender);
        tvMobile = (TextView) findViewById(R.id.tv_mobile);
        tvTotalVisits = (TextView) findViewById(R.id.tv_total_visits);
        tvTotalRevenue = (TextView) findViewById(R.id.tv_total_revenue);
        tvDesc = (TextView) findViewById(R.id.tv_description);

        tvBookNow = (TextView) findViewById(R.id.tv_bookNow);
        tvBookNow.setOnClickListener(this);

        tvDate = (TextView) findViewById(R.id.tv_date);
        tvDate.setOnClickListener(this);

        tvSelectService = (TextView) findViewById(R.id.tv_select_service);
        tvSelectService.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.actionbar_view_icon_left:
                finish();
                break;


            case R.id.tv_bookNow:
                if (isValidated()) {

                    dataAppointment.setTotalAmount("" + totalCost);
                    dataAppointment.setTotalTime("" + totalTime);
                    dataAppointment.setBookingDate(currentDate);
                    dataAppointment.setBookingTime(mAdapterSlots.getSelectedSlotList().get(0));
                    dataAppointment.setServices(rateInfoDatas);
                    dataAppointment.setEmployee(mAdapterStylist.getSelectedStylistDataList());
                    dataAppointment.setSlots(mAdapterSlots.getSelectedSlotList());


                    seatNum = String.valueOf(Utility.getAvailableSeat(BookNowActivity.this, dataAppointment.getSlots().get(0), dataAppointment.getSlots().get(dataAppointment.getSlots().size() - 1)));
                    dataAppointment.setSeatNumber(seatNum);

                    for (ResponseModelRateInfoData rateData : dataAppointment.getServices()) {
                        rateData.setEmployee_name(dataAppointment.getEmployee().getEmp_name());
                        rateData.setEmployee_id(dataAppointment.getEmployee().getEmp_id());
                    }

                    ResponseModelAppointments responseModelAppointments = Utility.getResponseModelAppointments(BookNowActivity.this, Constants.keySalonAppointmentsData);

                    // remove values
                    if (isEdit) {
                        Utility.releaseEmployeeSelectedSlots(this, previousAppointmentData.getEmployee().getEmp_id(), previousAppointmentData.getSlots());
                        Utility.releaseSeatReschedule(this, previousAppointmentData.getSeatNumber(), previousAppointmentData.getSlots());

                        if (responseModelAppointments != null) {
                            if (reschedulePos >= 0) {
                                responseModelAppointments.getData().remove(reschedulePos);
                            }

                        }
                    }


                    if (Utility.isCurrentBooking(mAdapterSlots.getSelectedSlotList().get(0))) {

                        dataAppointment.setBookingStatus(Constants.BOOKING_STATUS_CONFIRM);

                        Utility.bookSeat(BookNowActivity.this, dataAppointment);
                        Utility.updateSeatSlots(BookNowActivity.this, dataAppointment.getSeatNumber(), dataAppointment.getSlots());

                    } else {
                        dataAppointment.setBookingStatus(Constants.BOOKING_STATUS_WAITING);

                        if (responseModelAppointments != null) {
                            responseModelAppointments.getData().add(dataAppointment);
                        }

                    }

                    Utility.addPreferencesAppointmentsData(BookNowActivity.this, Constants.keySalonAppointmentsData, responseModelAppointments);

                    Utility.setEmployeeSelectedSlots(BookNowActivity.this, dataAppointment.getEmployee().getEmp_id(), dataAppointment.getSlots());

                    Utility.updateSeatSlots(BookNowActivity.this, dataAppointment.getSeatNumber(), dataAppointment.getSlots());

                    finish();
                }

                break;

            case R.id.tv_select_service:
                startActivityForResult(new Intent(BookNowActivity.this, SelectServiceActivity.class)
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

            case R.id.tv_date:
                Utility.datePickerDialog(BookNowActivity.this, this);
                break;

        }
    }

    private boolean isValidated() {

        if (rateInfoDatas == null) {
            Utility.showToast(BookNowActivity.this, "Please select services");
            return false;
        }

        if (rateInfoDatas.size() == 0) {
            Utility.showToast(BookNowActivity.this, "Please select services");
            return false;
        }

        if (mAdapterStylist.getSelectedStylistDataList() == null) {
            Utility.showToast(BookNowActivity.this, "Please select a Stylist");
            return false;
        }


        if (mAdapterSlots.getSelectedSlotList() == null) {
            Utility.showToast(BookNowActivity.this, "Please select Time Slots");
            return false;
        }

        if (mAdapterSlots.getSelectedSlotList().size() == 0) {
            Utility.showToast(BookNowActivity.this, "Please select Time Slots");
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
                String openTime = Utility.getPreferences(BookNowActivity.this, Constants.keySalonOpenTime);
                slots.addAll(Utility.getTimeSlots(openTime, Constants.timeStartAfternoon));
                mAdapterSlots.notifyDataSetChanged();
                activePosition = Utility.getActiveSlotPosition(currentDate, elapsedSlots, slots);
                rvSlots.scrollToPosition(activePosition);
                break;

            case 1:
                // Afternoon Slot
                tvMorning.setTextColor(getResources().getColor(R.color.colorBlack));
                tvAfternoon.setTextColor(getResources().getColor(R.color.colorBlue2));
                tvEvening.setTextColor(getResources().getColor(R.color.colorBlack));
                slots.addAll(Utility.getTimeSlots(Constants.timeStartAfternoon, Constants.timeEndAfternoon));
                mAdapterSlots.notifyDataSetChanged();
                activePosition = Utility.getActiveSlotPosition(currentDate, elapsedSlots, slots);
                rvSlots.scrollToPosition(activePosition);
                break;

            case 2:
                // Evening Slot
                tvMorning.setTextColor(getResources().getColor(R.color.colorBlack));
                tvAfternoon.setTextColor(getResources().getColor(R.color.colorBlack));
                tvEvening.setTextColor(getResources().getColor(R.color.colorBlue2));
                String closeTime = Utility.getPreferences(BookNowActivity.this, Constants.keySalonCloseTime);
                slots.addAll(Utility.getTimeSlots(Constants.timeEndAfternoon, closeTime));
                mAdapterSlots.notifyDataSetChanged();
                activePosition = Utility.getActiveSlotPosition(currentDate, elapsedSlots, slots);
                rvSlots.scrollToPosition(activePosition);
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


    public void setData(ResponseModelCustomerData responseModelCustomerData) {
        llMain.setVisibility(View.VISIBLE);
        llDetails.setVisibility(View.VISIBLE);

        tvName.setText(responseModelCustomerData.getEndUser_FirstName() + " " + responseModelCustomerData.getEndUser_LastName());
        tvMobile.setText(responseModelCustomerData.getContact_no());
        tvEmail.setText(responseModelCustomerData.getEmail());
        tvGender.setText(responseModelCustomerData.getGender());
        tvTotalVisits.setText("" + Utility.getTotalVisits(this, responseModelCustomerData.getCustomerId()) + "");
        tvTotalRevenue.setText("" + Utility.getTotalRevenue(this, responseModelCustomerData.getCustomerId()) + "");
        if (responseModelCustomerData.getAllergies().length() > 0) {
            tvDesc.setText("Allergic to :\n" + responseModelCustomerData.getAllergies() + "");
        }

        dataAppointment = new ResponseModelAppointmentsData();
        dataAppointment.setCustomerId(responseModelCustomerData.getCustomerId());
        dataAppointment.setBookingId(System.currentTimeMillis());
        dataAppointment.setCustomerEndUser_FirstName(responseModelCustomerData.getEndUser_FirstName());
        dataAppointment.setCustomerEndUser_LastName(responseModelCustomerData.getEndUser_LastName());
        dataAppointment.setBookingType(Constants.BOOKING_TYPE_OFFLINE);
        dataAppointment.setCustomerMobile(responseModelCustomerData.getContact_no());

        totalCost = 0;
        totalTime = 0;

    }

    public void setEditData(ResponseModelAppointmentsData responseModelAppointmentsData) {
        llMain.setVisibility(View.VISIBLE);
        llDetails.setVisibility(View.GONE);

        dataAppointment = new ResponseModelAppointmentsData();
        dataAppointment.setCustomerId(responseModelAppointmentsData.getCustomerId());
        dataAppointment.setBookingId(responseModelAppointmentsData.getBookingId());
        dataAppointment.setCustomerEndUser_FirstName(responseModelAppointmentsData.getCustomerEndUser_FirstName());
        dataAppointment.setCustomerEndUser_LastName(responseModelAppointmentsData.getCustomerEndUser_LastName());
        dataAppointment.setBookingType(responseModelAppointmentsData.getBookingType());
        dataAppointment.setCustomerMobile(responseModelAppointmentsData.getCustomerMobile());
        dataAppointment.setServices(responseModelAppointmentsData.getServices());

        totalCost = Integer.parseInt(responseModelAppointmentsData.getTotalAmount());
        totalTime = Integer.parseInt(responseModelAppointmentsData.getTotalTime());

    }

    public void setDisabledSlotList(ArrayList<String> bookedSlots) {
        this.bookedSlots.clear();
        this.bookedSlots.addAll(bookedSlots);
        mAdapterSlots.setDisabledSlotList(bookedSlots);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "0" + (view.getMonth() + 1)
                + "-" + view.getDayOfMonth()
                + "-" + view.getYear();

        currentDate = Utility.formatDateForDisplay(date, "MM-dd-yyyy", Constants.displayDateFormat);
        tvDate.setText(currentDate);
        mAdapterSlots.setDate(currentDate);
        String formattedDate = Utility.formatDateForDisplay(date, "MM-dd-yyyy", "dd/MM/yyyy");
        initSlotUi(formattedDate);
    }
}
