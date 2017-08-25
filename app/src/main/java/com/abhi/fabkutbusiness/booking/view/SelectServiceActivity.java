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
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

import java.util.ArrayList;

/**
 * Created by abhi on 17/04/17.
 */

public class SelectServiceActivity extends AppCompatActivity implements View.OnClickListener {

    View actionBarView;
    TextView tvTitle, tvRight;
    ImageView iconLeft, iconRight;
    RecyclerView rvServices;
    ServicesAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_services);

        setActionBarView();
        findViewById();
        initUi();

    }

    private void initUi() {

        ArrayList<ResponseModelRateInfoData> data = Utility.getResponseModelRateInfo(this, Constants.keySalonRateInfoData).getData();

        ArrayList<ResponseModelRateInfoData> selectedData = getIntent().getParcelableArrayListExtra("data");

        if (selectedData == null)
            selectedData = new ArrayList<>();

        mAdapter = new ServicesAdapter(data, selectedData, R.layout.item_service_list, false, this);
        rvServices.setAdapter(mAdapter);
    }

    private void findViewById() {

        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Select Services");

        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
        iconLeft.setImageDrawable(getResources().getDrawable(R.drawable.close));
        iconLeft.setVisibility(View.VISIBLE);
        iconLeft.setOnClickListener(this);

        iconRight = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_right);

        tvRight = (TextView) actionBarView.findViewById(R.id.actionbar_view_tv_right);
        tvRight.setTextColor(getResources().getColor(R.color.colorGreen));
        tvRight.setText("DONE");
        tvRight.setOnClickListener(this);

        rvServices = (RecyclerView) findViewById(R.id.rv_services);


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

            case R.id.actionbar_view_tv_right:
                ArrayList<ResponseModelRateInfoData> dataList = mAdapter.getSelectedRateDataList();
                Intent intent = new Intent();
                intent.putExtra("dataList", dataList);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
