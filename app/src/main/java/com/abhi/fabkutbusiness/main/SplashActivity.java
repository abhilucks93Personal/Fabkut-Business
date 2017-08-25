package com.abhi.fabkutbusiness.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;


public class SplashActivity extends Activity {

    private boolean loginCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.setStatusBarTranslucent(this, true);
        setContentView(R.layout.activity_splash);

        loginCheck = Utility.getPreferences(this, Constants.keyLoginCheck, false);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    Intent intent;
                    if (loginCheck)
                        intent = new Intent(SplashActivity.this, NavigationMainActivity.class);
                    else
                        intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        };

        timerThread.start();

    }


}
