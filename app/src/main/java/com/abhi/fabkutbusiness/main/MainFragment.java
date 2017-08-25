package com.abhi.fabkutbusiness.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.billing.view.BillNowActivity;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;


/**
 * Created by ng on 2/12/2017.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    Button btnBook, btnBill;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        findViewById(view);

        return view;
    }

    private void findViewById(View view) {

        btnBook = (Button) view.findViewById(R.id.button);
        btnBook.setOnClickListener(this);
        btnBill = (Button) view.findViewById(R.id.button2);
        btnBill.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button:
                startActivity(new Intent(getActivity(),BookNowActivity.class));
                break;

            case R.id.button2:
                startActivity(new Intent(getActivity(),BillNowActivity.class));
                break;

        }
    }

}
