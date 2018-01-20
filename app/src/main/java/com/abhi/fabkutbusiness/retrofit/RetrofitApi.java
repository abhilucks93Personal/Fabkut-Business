package com.abhi.fabkutbusiness.retrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.AdapterView;

import com.abhi.fabkutbusiness.Utils.Constants;
import com.abhi.fabkutbusiness.Utils.Utility;
import com.abhi.fabkutbusiness.crm.model.ResponseBasicInfo;
import com.abhi.fabkutbusiness.crm.model.ResponseBasicInfoUpdate;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmList;
import com.abhi.fabkutbusiness.crm.model.ResponsePersonalInfo;
import com.abhi.fabkutbusiness.crm.model.ResponsePersonalInfoUpdate;
import com.abhi.fabkutbusiness.crm.model.ResponseSocialInfo;
import com.abhi.fabkutbusiness.crm.model.ResponseSocialInfoUpdate;
import com.abhi.fabkutbusiness.main.LoginActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomer;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployee;
import com.abhi.fabkutbusiness.main.model.ResponseModelLogin;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfo;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.abhi.fabkutbusiness.Utils.Constants.errorMsgWrong;

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

        void _onNext1(Object obj);


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

    public void addCustomerApiMethod(final Activity activity, final ResponseListener _mlistener_response, final ResponseModelCustomerData responseModelCustomerData) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Uploading Data...");
        mProgressDialog.show();


        RetrofitClient.getClient().addCustomerApiMethod(responseModelCustomerData.getBusiness_id(),
                responseModelCustomerData.getEndUser_FirstName(),
                responseModelCustomerData.getGender(),
                responseModelCustomerData.getEmail(),
                responseModelCustomerData.getContact_no(),
                responseModelCustomerData.getAllergies(),
                responseModelCustomerData.getDob()
        ).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModel>() {
                    @Override
                    public void onCompleted() {
                        mProgressDialog.dismiss();
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {
                        mProgressDialog.dismiss();
                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseModel responseModel) {
                        mProgressDialog.dismiss();
                        if (responseModel.getSTATUS().equals("200")) {
                            Utility.showToast(activity, "Customer added successfully");
                            mlistener_response._onNext(responseModelCustomerData);
                        } else
                            Utility.showToast(activity, errorMsgWrong);


                    }

                });
    }

    public void bookingApiMethod(final Activity activity, final ResponseListener _mlistener_response, final ResponseModelAppointmentsData responseModelCustomerData, int assignBook) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Uploading Data...");
        mProgressDialog.show();

        String slots[] = responseModelCustomerData.getSlots().get(0).split("/");
        String slot = "";
        if (slots.length == 2)
            slot = slots[1];

        RetrofitClient.getClient().bookingApiMethod(
                responseModelCustomerData.getCustomerId(),
                responseModelCustomerData.getBusinessId(),
                slot,
                responseModelCustomerData.getServices().get(0).getEmployee_id(),
                getFormattedServicesIds(responseModelCustomerData.getServices()),
                responseModelCustomerData.getCustomerMobile(),
                "",
                "0",
                "",
                responseModelCustomerData.getSeatNumber(),
                "" + assignBook
        ).subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseModel>() {
                    @Override
                    public void onCompleted() {
                        mProgressDialog.dismiss();
                        mlistener_response._onCompleted();
                    }


                    @Override
                    public void onError(Throwable e) {
                        mProgressDialog.dismiss();
                        mlistener_response._onError(e);

                    }

                    @Override
                    public void onNext(ResponseModel responseModel) {
                        mProgressDialog.dismiss();
                        if (responseModel.getSTATUS().equals("200")) {
                            Utility.showToast(activity, "Booking made successfully");
                            mlistener_response._onNext(responseModelCustomerData);
                        } else
                            Utility.showToast(activity, errorMsgWrong);


                    }

                });
    }

    private String getFormattedServicesIds(ArrayList<ResponseModelRateInfoData> services) {
        String serviceIds = "";
        int index = 0;
        for (ResponseModelRateInfoData modelRateInfoData : services) {
            serviceIds += modelRateInfoData.getId();
            if (index < (services.size() - 1))
                serviceIds += ",";
            index++;
        }
        return serviceIds;
    }


    // --------------------- Crm List Show api  ----------------------------------------------------------


    public void CrmListShowApiMethod(final Activity activity, final ResponseListener mlistener_response, int business_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().CrmListShowApiMethod(business_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseCrmList>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseCrmList responseCrmList) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseCrmList);
            }
        });

    }


    // --------------------- Crm basic info Show api  ----------------------------------------------------------


    public void CrmBasicInfoShowApiMethod(final Activity activity, final ResponseListener mlistener_response, int business_id, int EndUser_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().CrmBasicInfoShowApiMethod(business_id, EndUser_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).     /*observeOn(AndroidSchedulers.mainThread())*/
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseBasicInfo>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseBasicInfo responseBasicInfo) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseBasicInfo);
            }
        });

    }
    // --------------------- Crm personal info Update api  ----------------------------------------------------------


    public void CrmPersonalInfoUpdateApiMethod(final Activity activity, final ResponseListener mlistener_response, int business_id, int EndUser_id, String dob, String anidate, int m_um, int profile) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().CrmPersonalInfoUpdateApiMethod(business_id, EndUser_id, dob, anidate, m_um, profile).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).     /*observeOn(AndroidSchedulers.mainThread())*/
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponsePersonalInfoUpdate>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponsePersonalInfoUpdate responsePersonalInfoUpdate) {
                mProgressDialog.dismiss();
                mlistener_response._onNext1(responsePersonalInfoUpdate);
            }
        });

    }

// --------------------- Crm Social info Update api  ----------------------------------------------------------


    public void CrmSocialInfoUpdateApiMethod(final Activity activity, final ResponseListener mlistener_response, int business_id, int EndUser_id, String homeAddress, String deliverAddress, String modeOfCommunication, String facebookId, String twitterId, String whatsappId, int social_p) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().CrmSocialInfoUpdateApiMethod(business_id, EndUser_id, homeAddress, deliverAddress, modeOfCommunication, facebookId, twitterId, whatsappId, social_p).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).     /*observeOn(AndroidSchedulers.mainThread())*/
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseSocialInfoUpdate>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseSocialInfoUpdate responseSocialInfoUpdate) {
                mProgressDialog.dismiss();
                mlistener_response._onNext1(responseSocialInfoUpdate);
            }
        });

    }


    // --------------------- Crm social info Show api  ----------------------------------------------------------


    public void CrmSocialInfoShowApiMethod(final Activity activity, final ResponseListener mlistener_response, int business_id, int EndUser_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().CrmSocialInfoShowApiMethod(business_id, EndUser_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).     /*observeOn(AndroidSchedulers.mainThread())*/
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseSocialInfo>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseSocialInfo responseSocialInfo) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responseSocialInfo);
            }
        });

    }


    // --------------------- Crm personal info Show api  ----------------------------------------------------------


    public void CrmPersonalInfoShowApiMethod(final Activity activity, final ResponseListener mlistener_response, int business_id, int EndUser_id) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().CrmPersonalInfoShowApiMethod(business_id, EndUser_id).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).     /*observeOn(AndroidSchedulers.mainThread())*/
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponsePersonalInfo>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponsePersonalInfo responsePersonalInfo) {
                mProgressDialog.dismiss();
                mlistener_response._onNext(responsePersonalInfo);
            }
        });

    }

// --------------------- Crm basic info update api  ----------------------------------------------------------


    public void CrmBasicInfoUpdateApiMethod(final Activity activity, final ResponseListener mlistener_response, int business_id, int EndUser_id, String fName, String Lname, String gender, String email, String contact_no, String phone1, String alergies, int profile_b) {
        this.mlistener_response = mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().CrmBasicInfoUpdateApiMethod(business_id, EndUser_id, fName, Lname, gender, email, contact_no, phone1, alergies, profile_b).subscribeOn(Schedulers.newThread()).
                subscribeOn(Schedulers.io()).     /*observeOn(AndroidSchedulers.mainThread())*/
                observeOn(AndroidSchedulers.mainThread()).
                unsubscribeOn(Schedulers.io()).subscribe(new Subscriber<ResponseBasicInfoUpdate>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                mlistener_response._onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                mProgressDialog.dismiss();
                mlistener_response._onError(e);
            }

            @Override
            public void onNext(ResponseBasicInfoUpdate responseBasicInfoUpdate) {
                mProgressDialog.dismiss();
                mlistener_response._onNext1(responseBasicInfoUpdate);
            }
        });

    }


}





