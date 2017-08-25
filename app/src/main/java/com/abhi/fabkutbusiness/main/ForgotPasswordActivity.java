package com.abhi.fabkutbusiness.main;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.abhi.fabkutbusiness.R;

/**
 * Created by abhi on 15/04/17.
 */

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    View actionBarView;
    EditText etEmail;
    TextView tvActionbarTitle;
    TextView tvSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgot_password);

        setActionBarView();
        findViewById();
        initData();

    }


    private void findViewById() {
        tvActionbarTitle = (TextView) actionBarView.findViewById(R.id.actionbar_view_title);
        etEmail = (EditText) findViewById(R.id.forgot_password_etEmail);
        tvSubmit = (TextView) findViewById(R.id.forgot_password_tvSubmit);
        tvSubmit.setOnClickListener(this);

    }

    private void setActionBarView() {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar_view_custom);
        actionBarView = getSupportActionBar().getCustomView();
    }

    private void initData() {
        tvActionbarTitle.setText("Forgot Password");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.forgot_password_tvSubmit:
                break;
        }
    }
}
