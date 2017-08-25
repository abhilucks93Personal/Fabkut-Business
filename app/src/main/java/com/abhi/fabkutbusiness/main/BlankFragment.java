package com.abhi.fabkutbusiness.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhi.fabkutbusiness.R;


/**
 * Created by ng on 2/12/2017.
 */
public class BlankFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        findViewById(view);

        return view;
    }

    private void findViewById(View view) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        }
    }

}
