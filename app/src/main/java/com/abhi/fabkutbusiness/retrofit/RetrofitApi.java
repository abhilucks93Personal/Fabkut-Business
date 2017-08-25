package com.abhi.fabkutbusiness.retrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.AdapterView;

import com.abhi.fabkutbusiness.main.LoginActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomer;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployee;
import com.abhi.fabkutbusiness.main.model.ResponseModelLogin;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfo;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by abhishekagarwal on 3/21/17.
 */

public class RetrofitApi {

    private ProgressDialog mProgressDialog;
    private static RetrofitApi retrofitApi = null;
    private ResponseListener mlistener_response;


    public static RetrofitApi getInstance() {

        if (retrofitApi != null)
            return retrofitApi;
        else
            return new RetrofitApi();
    }


    public interface ResponseListener {

        void _onCompleted();

        void _onError(Throwable e);

        void _onNext(Object obj);


    }


    // --------------------- Retrofit Api Methods ----------------------------------------------------------


    public void loginApiMethod(final Activity activity, final ResponseListener _mlistener_response, String email, String password) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().loginApiMethod(email, password).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModelLogin>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseModelLogin responseModelLogin) {

                        mlistener_response._onNext(responseModelLogin);

                    }

                });
    }

    public void customerApiMethod(final Activity activity, final ResponseListener _mlistener_response, String business_id) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().customerApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModelCustomer>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseModelCustomer responseModelCustomer) {

                        mlistener_response._onNext(responseModelCustomer);

                    }

                });

    }

    public void employeeApiMethod(final Activity activity, final ResponseListener _mlistener_response, String business_id) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().employeeApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModelEmployee>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseModelEmployee responseModelEmployee) {

                        mlistener_response._onNext(responseModelEmployee);

                    }

                });

    }

    public void rateInfoApiMethod(final Activity activity, final ResponseListener _mlistener_response, String business_id) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().rateInfoApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModelRateInfo>() {
                    @Override
                    public void onCompleted() {
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {

                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseModelRateInfo responseModelRateInfo) {

                        mlistener_response._onNext(responseModelRateInfo);

                    }

                });

    }

}