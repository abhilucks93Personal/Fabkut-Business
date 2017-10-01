package com.abhi.fabkutbusiness.crm.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.crm.model.ResponseSocialInfo;
import com.abhi.fabkutbusiness.crm.model.ResponseSocialInfoUpdate;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.ArrayList;

/**
 * Created by SIDDHARTH on 9/5/2017.
 */

public class Social_Info extends Fragment implements RetrofitApi.ResponseListener,View.OnClickListener{

    View v;
    EditText homeAddress,deliverAddress,facebookId,twitterId,whatsappId;
    Spinner modeOfCommunication,PrefEmpName;
    ArrayList<String> modeOfCummnicationList;
    ArrayAdapter<String> spinnerArrayAdapter;
    TextView save;
    int User_id;
    int ab=3;
    int getProfile=0;
    int profile_s = 0;
    int T_profile=0;
    int profiles_s1=0;
    int profiles_s2=0;
    int profiles_s3=0;
    int profiles_s4=0;
    int profiles_s5=0;
    int profiles_s7=0;
    int profiles_s6=0;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_social_info, container, false);
        modeOfCummnicationList= new ArrayList<String> ();
        modeOfCummnicationList.add("Call");
        modeOfCummnicationList.add("Sms");
        modeOfCummnicationList.add("Email");
        modeOfCummnicationList.add("None");



        findViewById();
        CrmTab crmTab=(CrmTab) getActivity();
        User_id = crmTab.user_id;


        spinnerArrayAdapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, modeOfCummnicationList);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        modeOfCommunication.setAdapter(spinnerArrayAdapter);



        RetrofitApi.getInstance().CrmSocialInfoShowApiMethod(getActivity(), this,ab,User_id);


        return v;
    }

    private void findViewById() {
        homeAddress=(EditText)v.findViewById(R.id.social_homeAddress);
        deliverAddress=(EditText)v.findViewById(R.id.social_deliver_address);
        facebookId=(EditText)v.findViewById(R.id.social_facebookId);
        twitterId=(EditText)v.findViewById(R.id.social_twitterId);
        whatsappId=(EditText)v.findViewById(R.id.social_whatsaapId);
        modeOfCommunication=(Spinner) v.findViewById(R.id.social_moc);
        PrefEmpName=(Spinner)v.findViewById(R.id.social_preferredEmployeeName);
        save=(TextView)v.findViewById(R.id.social_info_save);
        save.setOnClickListener(this);

    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {



        ResponseSocialInfo responseSocialInfo=(ResponseSocialInfo) obj;
        if (responseSocialInfo.getSTATUS().equalsIgnoreCase("200")){
            homeAddress.setText(responseSocialInfo.getData().get(0).getSocial_Home_address());
            T_profile=responseSocialInfo.getData().get(0).getProfile_Comp_total();
            getProfile=responseSocialInfo.getData().get(0).getProfile_Comp_Social();
            deliverAddress.setText(responseSocialInfo.getData().get(0).getSocial_Delivery_Address());
            facebookId.setText(responseSocialInfo.getData().get(0).getSocial_FB_ID());
            twitterId.setText(responseSocialInfo.getData().get(0).getSocial_Twitter_ID());
            whatsappId.setText(responseSocialInfo.getData().get(0).getSocial_whatsApp());


            if (responseSocialInfo.getData().get(0).getSocial_Mode_Commincation().equals("Call")){
                modeOfCommunication.setSelection(0);
            }else if (responseSocialInfo.getData().get(0).getSocial_Mode_Commincation().equalsIgnoreCase("Sms")){
                modeOfCommunication.setSelection(1);
            }else if(responseSocialInfo.getData().get(0).getSocial_Mode_Commincation().equalsIgnoreCase("Email")){
                modeOfCommunication.setSelection(2);
            }else {
                modeOfCommunication.setSelection(3);
            }


        }else{
            Toast.makeText(getContext(),responseSocialInfo.getMESSAGE(),Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void _onNext1(Object obj) {
        ResponseSocialInfoUpdate responseSocialInfoUpdate=(ResponseSocialInfoUpdate) obj;
        if (responseSocialInfoUpdate.getData().get(0).getStatus().equalsIgnoreCase("Success")){
            Toast.makeText(getContext(),"update Successfully",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.social_info_save:
                social_update();
                break;

        }
    }

    private void social_update() {


        if (homeAddress.getText().length() != 0) {
            profiles_s1 = 5;
        } else {
            profiles_s1 = 0;
        }



        if (deliverAddress.getText().length()!=0){
                profiles_s2=5;
            }else {
                profiles_s2=0;
            }



        if (facebookId.getText().length()!=0){
            profiles_s3=4;
         }else {
                profiles_s3=0;
            }


        if (twitterId.getText().length()!=0){
                profiles_s4=4;
            }
            else {
                profiles_s4=0;
            }


        if (whatsappId.getText().length()!=0){
                profiles_s5=4;
            }
            else {
                profiles_s5=0;
            }

        if (modeOfCommunication.getSelectedItem().equals("None")){
            profiles_s6=0;
        }
        else {
            profiles_s6=4;
        }


        profile_s=profiles_s1+profiles_s2+profiles_s3+profiles_s4+profiles_s5+profiles_s6;

    /*Toast.makeText(getContext(),homeAddress.getText().toString().trim(),Toast.LENGTH_LONG).show();
        Toast.makeText(getContext(),facebookId.getText().toString().trim(),Toast.LENGTH_LONG).show();
        Toast.makeText(getContext(),modeOfCommunication.getSelectedItem().toString().trim(),Toast.LENGTH_LONG).show();*/
        RetrofitApi.getInstance().CrmSocialInfoUpdateApiMethod(getActivity(), this,ab,User_id,homeAddress.getText().toString().trim(),deliverAddress.getText().toString().trim(),modeOfCommunication.getSelectedItem().toString().trim(),facebookId.getText().toString().trim(), twitterId.getText().toString().trim(),whatsappId.getText().toString().trim(),profile_s);
      Toast.makeText(getContext(),whatsappId.getText().toString().trim(),Toast.LENGTH_LONG).show();
    }
}
