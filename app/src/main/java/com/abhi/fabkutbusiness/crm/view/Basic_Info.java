package com.abhi.fabkutbusiness.crm.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.crm.model.ResponseBasicInfo;
import com.abhi.fabkutbusiness.crm.model.ResponseBasicInfoUpdate;
import com.abhi.fabkutbusiness.retrofit.RetrofitApi;


/**
 * Created by SIDDHARTH on 9/5/2017.
 */

public class Basic_Info extends Fragment implements RetrofitApi.ResponseListener,View.OnClickListener{

    View v;
    EditText  fName,Lname, email,phone1,phone2,alergies;
    RadioGroup gender;
    RadioButton male,female,other;
    Switch customerType;
    TextView save,phone_tv;
    int User_id;
    int getProfile=0;
    int ab=3;
    int profile = 0;
    int T_profile=0;
    int profiles_b1=0;
    int profiles_b2=0;
    int profiles_b3=0;
    int profiles_b4=0;
    int profiles_b5=0;
    int profiles_b7=0;
    int profiles_b6=0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_basic_info, container, false);


        findViewById();
        CrmTab crmTab=(CrmTab) getActivity();
        User_id=crmTab.user_id;
        RetrofitApi.getInstance().CrmBasicInfoShowApiMethod(getActivity(), this,ab,User_id);


        return v;
    }

    private void findViewById() {
        fName=(EditText)v.findViewById(R.id.basicinfo_fname);

        Lname=(EditText)v.findViewById(R.id.basicinfo_lname);
        email=(EditText)v.findViewById(R.id.basicinfo_email);
        phone1=(EditText)v.findViewById(R.id.basicinfo_phone1);
        phone2=(EditText)v.findViewById(R.id.basicinfo_phone2);
        alergies=(EditText)v.findViewById(R.id.allergies);
        //gender=(RadioGroup)v.findViewById(R.id.basicinfo_gender);
        male=(RadioButton)v.findViewById(R.id.basicinfo_genderMale);
        female=(RadioButton)v.findViewById(R.id.basicinfo_genderFmale);
        other=(RadioButton)v.findViewById(R.id.basicinfo_genderTransgender);
        save=(TextView)v.findViewById(R.id.basicinfo_save);
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
        ResponseBasicInfo responseBasicInfo=(ResponseBasicInfo) obj;
        Log.d("abc", "" + responseBasicInfo);

        if (responseBasicInfo.getSTATUS().equalsIgnoreCase("200")) {
            fName.setText(responseBasicInfo.getData().get(0).getEnduser_name());

            Lname.setText(responseBasicInfo.getData().get(0).getLname());

            email.setText(responseBasicInfo.getData().get(0).getEmail());

            if (responseBasicInfo.getData().get(0).getContact_no().equalsIgnoreCase("")){
                phone1.setEnabled(true);
                phone1.setText(responseBasicInfo.getData().get(0).getContact_no());
            }
            else{
                phone1.setText(responseBasicInfo.getData().get(0).getContact_no());
                phone1.setEnabled(false);
            }

            phone2.setText(responseBasicInfo.getData().get(0).getAlternetContact());



            alergies.setText(responseBasicInfo.getData().get(0).getAllergies());

            if (responseBasicInfo.getData().get(0).getGender().equalsIgnoreCase("Male")){
                male.setChecked(true);
            }else if(responseBasicInfo.getData().get(0).getGender().equalsIgnoreCase("Female")){
                female.setChecked(true);
            }else {
                other.setChecked(true);
            }

            T_profile=responseBasicInfo.getData().get(0).getProfile_Comp_total();
            getProfile=responseBasicInfo.getData().get(0).getProfile_Comp_Basic();



        }else{
            Toast.makeText(getContext(),responseBasicInfo.getMESSAGE(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void _onNext1(Object obj) {

        ResponseBasicInfoUpdate responseBasicInfoUpdate=(ResponseBasicInfoUpdate) obj;
        if (responseBasicInfoUpdate.getSTATUS().equals("200")){
            if (responseBasicInfoUpdate.getData().get(0).getStatus().equalsIgnoreCase("Success")){
                Toast.makeText(getContext(),"update successfully",Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.basicinfo_save:
                saveBasic_info();
                break;
        }
    }

    private void saveBasic_info() {
        String gender = null;
        if (male.isChecked()){
            gender="male";
        }else  if (female.isChecked()){
            gender= "female";
        } else  if (other.isChecked()){
            gender= "Other";
        }

        if (male.isChecked() || female.isChecked()|| other.isChecked()){
            profiles_b1=6;

        }else {
            profiles_b1=0;
        }
        if (alergies.getText().length()!=0){

            profiles_b2=5;

        }else {
            profiles_b2=0;
        }
        if (phone1.getText().length()!=0){
            profiles_b3=6;
        }else {
            profiles_b3=0;
        }
        if (phone2.getText().length()!=0){
                profiles_b4=5;
        }else {
            profiles_b4=0;
        }
        if (email.getText().length()!=0){
            profiles_b5=6;
        }else {
            profiles_b5=0;
        }
        if (Lname.getText().length()!=0){
            profiles_b6=6;
        }else {
            profiles_b6=0;
        }
        if (fName.getText().length()!=0){
           profiles_b7=6;
        }else {
            profiles_b7=0;
        }
        profile = profiles_b1+profiles_b2+profiles_b3+profiles_b4+profiles_b5+profiles_b6+profiles_b7;

        if (fName.getText().length()==0){
            Toast.makeText(getContext(),"Please Enter first Name ",Toast.LENGTH_LONG).show();

        }else {
            RetrofitApi.getInstance().CrmBasicInfoUpdateApiMethod(getActivity(), this, ab,User_id,fName.getText().toString().trim(),Lname.getText().toString().trim(),gender,email.getText().toString().trim(),  phone1.getText().toString().trim(),phone2.getText().toString().trim(),alergies.getText().toString().trim(),profile);
        }




       // Toast.makeText(getContext(),""+T_profile,Toast.LENGTH_LONG).show();


    }
}