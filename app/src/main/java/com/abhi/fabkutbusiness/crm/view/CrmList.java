package com.abhi.fabkutbusiness.crm.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.crm.controller.CrmAdapter;
import com.abhi.fabkutbusiness.crm.model.CrmList_model;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmList;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 9/10/2017.
 */

public class CrmList extends AppCompatActivity implements RetrofitApi.ResponseListener {

    RecyclerView lv;
    EditText Search;
    ArrayList<CrmList_model> crmList_models = new ArrayList<CrmList_model>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    View actionBarView;
    ImageView iconLeft;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_vendor_contract_details);
        setActionBarView();
        findViewById();
        int businessId = Integer.parseInt(Utility.getPreferences(this, Constants.keySalonBusinessId));

        RetrofitApi.getInstance().CrmListShowApiMethod(this, this, businessId);

        // itemsAdapter = new PendingAdapter(getActivity(), dataModelList);

        // use a linear layout manager

        // specify an adapter (see also next example)

        //lv.setAdapter(itemsAdapter);

    }


    private void setActionBarView() {

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        View customView = getLayoutInflater().inflate(R.layout.actionbar_view_custom, null);
        getSupportActionBar().setCustomView(customView);
        Toolbar parent = (Toolbar) customView.getParent();
        parent.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0, 0);

        /*getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);*/
        actionBarView = getSupportActionBar().getCustomView();
    }

    private void findViewById() {
        lv = (RecyclerView) findViewById(R.id.lv);
        lv.setBackgroundColor(Color.parseColor("#dedede"));
        Search = (EditText) findViewById(R.id.et_search);
        RelativeLayout crmSearch = (RelativeLayout) findViewById(R.id.crmList_search_layout);
        crmSearch.setVisibility(View.VISIBLE);
        tvTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        tvTitle.setText("Customer");
        iconLeft = (ImageView) actionBarView.findViewById(R.id.actionbar_view_icon_left);
    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {

        ResponseCrmList responseCrmList = (ResponseCrmList) obj;
        Log.d("abc", "" + responseCrmList);

        if (responseCrmList.getSTATUS().equalsIgnoreCase("200")) {
            for (int i = 0; i < responseCrmList.getData().size(); i++) {
                //SalonData data = new SalonData();
                crmList_models.add(new CrmList_model(
                        responseCrmList.getData().get(i).getEnduser_id()
                        , responseCrmList.getData().get(i).getEnduser_name(),
                        responseCrmList.getData().get(i).getContact_no()
                        , responseCrmList.getData().get(i).getEmail(),
                        responseCrmList.getData().get(i).getTotalVisit(),
                        responseCrmList.getData().get(i).getProfie_pic(),
                        responseCrmList.getData().get(i).getProfile_Comp_total()
                ));

            }

            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            lv.setLayoutManager(llm);
            CrmAdapter ca = new CrmAdapter(getApplicationContext(), crmList_models);
            lv.setAdapter(ca);

        } else {
            Toast.makeText(this, responseCrmList.getMESSAGE(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void _onNext1(Object obj) {

    }
}
