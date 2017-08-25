package com.abhi.fabkutbusiness.main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.view.AccountingActivity;
import com.abhi.fabkutbusiness.billing.view.BillNowActivity;
import com.abhi.fabkutbusiness.booking.view.AddBookingServiceActivity;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.customer.view.AddCustomerActivity;
import com.abhi.fabkutbusiness.main.controller.AppointmentsAdapter;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointments;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;

import java.util.ArrayList;

public class NavigationMainActivity extends AppCompatActivity implements View.OnClickListener {


    boolean doubleBackToExitPressedOnce = false;
    NavigationView navigationView;
    DrawerLayout drawer;
    View navHeader;
    TextView txtName, txtViewProfile;
    Toolbar toolbar;
    FloatingActionButton fab;
    ImageView ivAppointments;
    EditText etSearch;
    ListView listAppointments;
    ArrayList<ResponseModelAppointmentsData> appointmentsData = new ArrayList<>();
    TextView tvAccounting, tvInventory;
    private AppointmentsAdapter adapter;
    private ArrayList<ImageView> seatImageViews;
    private LinearLayout llSeats;
    private int reschedulePos = -1;

    @Override
    protected void onResume() {
        super.onResume();
        Utility.hideKeyboard(NavigationMainActivity.this);

        refreshAppointmentData();

        seatImageViews = Utility.refreshSeats(this, llSeats, seatImageViews);

    }


    public void refreshAppointmentData() {

        Utility.hideKeyboard(this);

        ResponseModelAppointments responseModelAppointments = Utility.getResponseModelAppointments(NavigationMainActivity.this, Constants.keySalonAppointmentsData);

        if (responseModelAppointments != null) {
            appointmentsData.clear();
            appointmentsData.addAll(responseModelAppointments.getData());
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._activity_navigation);


        initToolbar();
        initNavigationDrawer();
        initFab();
        //loadNavHeader();
        setUpNavigationView();

        findViewById();
        initUi();
        initData();

    }

    private void initUi() {

        seatImageViews = Utility.getCustomSeatImageViews(this, llSeats);
        seatImageViews = Utility.refreshSeats(this, llSeats, seatImageViews);

    }

    private void initData() {


        adapter = new AppointmentsAdapter(NavigationMainActivity.this, appointmentsData);
        listAppointments.setAdapter(adapter);
    }

    private void findViewById() {
        etSearch = (EditText) findViewById(R.id.et_search);
        etSearch.setOnClickListener(this);

        tvAccounting = (TextView) findViewById(R.id.tv_accounting);
        tvAccounting.setOnClickListener(this);

        listAppointments = (ListView) findViewById(R.id.list_appointments);

        tvInventory = (TextView) findViewById(R.id.tv_inventory);
        tvInventory.setOnClickListener(this);

        llSeats = (LinearLayout) findViewById(R.id.ll_seats);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ivAppointments = (ImageView) toolbar.findViewById(R.id.app_bar_iv_appointments);
        ivAppointments.setOnClickListener(this);
    }


    private void initNavigationDrawer() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.nav_header_tv_customer_name);
        txtViewProfile = (TextView) navHeader.findViewById(R.id.nav_header_tv_viewProfile);
    }

    private void initFab() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }


    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {
        // name, website
        txtName.setText("");
        txtViewProfile.setText("");

    }


    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_inventory:
                        Utility.showToast(NavigationMainActivity.this, "Inventory");
                        break;

                    case R.id.nav_emp:
                        Utility.showToast(NavigationMainActivity.this, "Emp");
                        break;

                    case R.id.nav_status:
                        Utility.showToast(NavigationMainActivity.this, "Status");
                        break;

                    case R.id.nav_my_salon:
                        Utility.showToast(NavigationMainActivity.this, "My Salon");
                        break;

                    case R.id.nav_logout:
                        Utility.showToast(NavigationMainActivity.this, "Logout");
                        break;


                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.menu, getTheme());

        actionBarDrawerToggle.setHomeAsUpIndicator(drawable);
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });


        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }


        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                startActivity(new Intent(NavigationMainActivity.this, BillNowActivity.class));
                break;

            case R.id.app_bar_iv_appointments:
                startActivity(new Intent(NavigationMainActivity.this, AddCustomerActivity.class));
                break;

            case R.id.et_search:

                String seatNum = Utility.getEmptySeatNum(NavigationMainActivity.this);

                startActivity(new Intent(NavigationMainActivity.this, BookNowActivity.class)
                        .putExtra("seatNum", seatNum));
                break;

            case R.id.tv_accounting:
                startActivity(new Intent(NavigationMainActivity.this, AccountingActivity.class));
                break;


            case R.id.tv_inventory:

                Utility.clearPreferenceData(NavigationMainActivity.this);
                finish();

                break;


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case 201:

                if (resultCode == RESULT_OK) {

                    ResponseModelAppointmentsData _dataAppointment = data.getParcelableExtra("data");

                    ResponseModelAppointments responseModelAppointments = Utility.getResponseModelAppointments(NavigationMainActivity.this, Constants.keySalonAppointmentsData);

                    if (responseModelAppointments != null) {

                        responseModelAppointments.getData().set(reschedulePos, _dataAppointment);
                        Utility.addPreferencesAppointmentsData(NavigationMainActivity.this, Constants.keySalonAppointmentsData, responseModelAppointments);
                    }


                }

                break;
        }

    }


    public void bookWaitingCustomer(int position) {
        String seatNum = Utility.getEmptySeatNum(NavigationMainActivity.this);

        if (seatNum.equals("-1")) {
            Utility.showToast(NavigationMainActivity.this, "No seat available");
        } else {

            ResponseModelAppointments responseModelAppointments = Utility.getResponseModelAppointments(NavigationMainActivity.this, Constants.keySalonAppointmentsData);

            if (responseModelAppointments != null) {

                ResponseModelAppointmentsData _dataAppointment = responseModelAppointments.getData().get(position);
                _dataAppointment.setSeatNumber(seatNum);
                Utility.bookSeat(NavigationMainActivity.this, _dataAppointment);
                responseModelAppointments.getData().remove(position);
                Utility.addPreferencesAppointmentsData(NavigationMainActivity.this, Constants.keySalonAppointmentsData, responseModelAppointments);

                refreshAppointmentData();

                seatImageViews = Utility.refreshSeats(this, llSeats, seatImageViews);
            }

        }


    }

    public void rescheduleWaitingCustomer(int position) {

        reschedulePos = position;

        ResponseModelAppointments responseModelAppointments = Utility.getResponseModelAppointments(NavigationMainActivity.this, Constants.keySalonAppointmentsData);

        if (responseModelAppointments != null) {

            ResponseModelAppointmentsData _dataAppointment = responseModelAppointments.getData().get(position);

            startActivityForResult(new Intent(NavigationMainActivity.this, AddBookingServiceActivity.class)
                    .putExtra("data", _dataAppointment)
                    .putExtra("isEdit", true), 201);

        }
    }
}