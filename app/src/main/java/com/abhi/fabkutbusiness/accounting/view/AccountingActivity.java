package com.abhi.fabkutbusiness.accounting.view;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.accounting.controller.AccountingPagerAdapter;
import com.abhi.fabkutbusiness.billing.model.BillingData;

/**
 * Created by abhi on 16/06/17.
 */

public class AccountingActivity extends AppCompatActivity implements View.OnClickListener {

    View actionBarView;
    TextView tvTitle;
    ImageView iconLeft;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AccountingPagerAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_accounting);

        setActionBarView();
        findViewById();
        initData();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.actionbar_view_icon_left:
                finish();
                break;
        }
    }

    private void setActionBarView() {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }

    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Accounting");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.rectangle4));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.pager);

    }

    private void initData() {
        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("EXPENSE TRACKER"));
        tabLayout.addTab(tabLayout.newTab().setText("ADVANCE PAY"));
        tabLayout.addTab(tabLayout.newTab().setText("TODAY'S STATEMENT"));
        tabLayout.addTab(tabLayout.newTab().setText("REPORT"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Creating our pager adapter
        adapter = new AccountingPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);

    }
}
