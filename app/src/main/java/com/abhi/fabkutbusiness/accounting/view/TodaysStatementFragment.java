package com.abhi.fabkutbusiness.accounting.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.accounting.controller.TodaysStatementAdapter;
import com.abhi.fabkutbusiness.billing.controller.BillingAdapter;
import com.abhi.fabkutbusiness.billing.model.ResponseModelBillingList;
import com.abhi.fabkutbusiness.billing.view.BillNowActivity;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;

import java.util.ArrayList;

/**
 * Created by abhi on 16/06/17.
 */

public class TodaysStatementFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    ListView lvStatements;
    TodaysStatementAdapter adapter;
    ArrayList<ResponseModelAppointmentsData> billingData;
    TextView tvDate;
    TextView tvTotalSale, tvTotalServices;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todays_statement, container, false);

        findViewById(view);

        initUi();

        initData(Utility.getCurrentDate(Constants.displayDateFormat));

        return view;
    }

    private void initUi() {
        billingData = new ArrayList<>();

        adapter = new TodaysStatementAdapter(getActivity(), billingData);
        lvStatements.setAdapter(adapter);
    }

    private void findViewById(View view) {
        lvStatements = (ListView) view.findViewById(R.id.list_statements);
        tvDate = (TextView) view.findViewById(R.id.tv_date);
        tvDate.setOnClickListener(this);
        tvTotalSale = (TextView) view.findViewById(R.id.tv_total_sale);
        tvTotalServices = (TextView) view.findViewById(R.id.tv_total_services);
    }

    private void initData(String currDate) {

        tvDate.setText(currDate);
        tvTotalSale.setText(Utility.getTotalSale(getActivity(), currDate));
        tvTotalServices.setText(Utility.getTotalService(getActivity(), currDate));

        billingData.clear();

        ResponseModelBillingList _data = Utility.getResponseModelBillingListData(getActivity(), Constants.keySalonBillingListData);

        if (_data.getDATA().containsKey(currDate)) {
            billingData.addAll(_data.getDATA().get(currDate));
        }

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_date:
                Utility.datePickerDialogBackDate(getActivity(), this);
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "0" + (view.getMonth() + 1)
                + "-" + view.getDayOfMonth()
                + "-" + view.getYear();

        initData(Utility.formatDateForDisplay(date, "MM-dd-yyyy", Constants.displayDateFormat));
    }

}