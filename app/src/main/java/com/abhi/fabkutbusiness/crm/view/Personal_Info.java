package com.abhi.fabkutbusiness.crm.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.crm.model.ResponsePersonalInfo;
import com.abhi.fabkutbusiness.crm.model.ResponsePersonalInfoUpdate;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;

import java.util.Calendar;

/**
 * Created by SIDDHARTH on 9/5/2017.
 */

public class Personal_Info extends Fragment implements RetrofitApi.ResponseListener, View.OnClickListener {

    View v;
    TextView etDob;
    TextView etaniDate;
    TextView update;
    int ab=3;
    int User_id;
    RadioButton married,unmarried;
    int profile_p = 0;
    int getProfile=0;
    int T_profile=0;
    int profiles_p1=0;
    int profiles_p2=0;
    int profiles_p3=0;
    private int day;
    private int month;
    private int year;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_personal_info, container, false);


        CrmTab crmTab=(CrmTab) getActivity();
        User_id=crmTab.user_id;

        findViewById();

        RetrofitApi.getInstance().CrmPersonalInfoShowApiMethod(getActivity(), this,ab,User_id);
        return v;
    }

    private void findViewById() {
        etDob=(TextView)v.findViewById(R.id.personal_dob);
        etDob.setOnClickListener(this);
        etaniDate=(TextView)v.findViewById(R.id.personal_anviDob);
        etaniDate.setOnClickListener(this);
        unmarried=(RadioButton)v.findViewById(R.id.basicinfo_genderFmale);
        married=(RadioButton)v.findViewById(R.id.basicinfo_genderMale);
        update=(TextView)v.findViewById(R.id.personal_info_save);
        update.setOnClickListener(this);

    }

    @Override
    public void _onCompleted() {

    }

    @Override
    public void _onError(Throwable e) {

    }

    @Override
    public void _onNext(Object obj) {

        ResponsePersonalInfo responsePersonalInfo=(ResponsePersonalInfo) obj;
        Log.d("abc", "" + responsePersonalInfo);


        if (responsePersonalInfo.getSTATUS().equalsIgnoreCase("200")) {
            etaniDate.setText(responsePersonalInfo.getData().get(0).getAnidate());
            etDob.setText(responsePersonalInfo.getData().get(0).getDob());
            if (responsePersonalInfo.getData().get(0).getM_um()==0){
                married.setChecked(true);
            }else if(responsePersonalInfo.getData().get(0).getM_um()==1){
                unmarried.setChecked(true);
            }
            if (etaniDate.length()!=0){
                profiles_p3=10;

            }
            if (etDob.length()!=0){
                profiles_p2=10;

            }
            if (married.isChecked() || unmarried.isChecked()){
                profiles_p1=10;

            }


            T_profile=responsePersonalInfo.getData().get(0).getProfile_Comp_total();
            getProfile=responsePersonalInfo.getData().get(0).getProfile_Comp_Personal();

            //maritalStatus.setText(responsePersonalInfo.getData().get(0).g);

        }else{
            Toast.makeText(getContext(),responsePersonalInfo.getMESSAGE(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void _onNext1(Object obj) {

        ResponsePersonalInfoUpdate responsePersonalInfoUpdate=(ResponsePersonalInfoUpdate) obj;
        if (responsePersonalInfoUpdate.getData().get(0).getStatus().equalsIgnoreCase("Success")){
            Toast.makeText(getActivity(),"update Succesfully",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.personal_info_save:
                update_personal_info();
                break;

            case R.id.personal_anviDob:
                anidate(etaniDate);
                break;

            case R.id.personal_dob:
                anidate(etDob);
                break;

        }
    }

    private void anidate(final TextView textView) {
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
            {

                textView.setText(monthOfYear+"/"+dayOfMonth+"/"+year);

            }};

        DatePickerDialog dpDialog=new DatePickerDialog(getActivity(), listener, year, month, day);
        dpDialog.show();

    }



    private void update_personal_info() {

        int M_status = 0;
        String  Dob=etDob.getText().toString();

        String ani= etaniDate.getText().toString();

        if (married.isChecked()){
           M_status=0;

        }else  if (unmarried.isChecked()){
            M_status=1;
        }

        if (married.isChecked() || unmarried.isChecked()){
               profiles_p1=10;
            }else {
                profiles_p1=0;
            }



        if (etaniDate.getText().length()!=0){
                profiles_p3=10;

        }else {
            profiles_p3=0;
        }
        if (etDob.getText().length()!=0){
               profiles_p2=10;
        }else {
            profiles_p2=0;
        }
        profile_p=profiles_p1+profiles_p2+profiles_p3;

       /*
Toast.makeText(getActivity(),""+M_status,Toast.LENGTH_LONG).show();
Toast.makeText(getActivity(),""+etDob.getText().toString().trim(),Toast.LENGTH_LONG).show();
Toast.makeText(getActivity(),""+etaniDate.getText().toString().trim(),Toast.LENGTH_LONG).show();
Toast.makeText(getActivity(), " "+ab+"  "+User_id,Toast.LENGTH_LONG).show();*/
        RetrofitApi.getInstance().CrmPersonalInfoUpdateApiMethod(getActivity(), this,ab,User_id, Dob,ani,M_status,profile_p);
       // Toast.makeText(getContext(),""+T_profile,Toast.LENGTH_LONG).show();


    }
}
