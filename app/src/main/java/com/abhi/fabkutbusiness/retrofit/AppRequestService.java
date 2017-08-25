package com.abhi.fabkutbusiness.retrofit;


import com.abhi.fabkutbusiness.main.model.ResponseModelCustomer;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployee;
import com.abhi.fabkutbusiness.main.model.ResponseModelLogin;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfo;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * * Interface through which all the api calls will be performed
 */
public interface AppRequestService {


    @POST("booking/index.php?tag=salonlogin")
    Observable<ResponseModelLogin> loginApiMethod(@Query("email") String user, @Query("password") String pass);

    @POST("booking/index.php?tag=customer")
    Observable<ResponseModelCustomer> customerApiMethod(@Query("business_id") String business_id);

    @POST("booking/index.php?tag=salonemp")
    Observable<ResponseModelEmployee> employeeApiMethod(@Query("business_id") String business_id);

    @POST("booking/index.php?tag=rateinfo")
    Observable<ResponseModelRateInfo> rateInfoApiMethod(@Query("business_id") String business_id);




  /*  @POST("fabkut/admin/index.php?tag=dataentry")
    Observable<ResponseModelDataEntry> dataEntryApiMethod(@Query("user_id") int user_id, @Query("city_Id") int city_id, @Query("location_Id") int location_id, @Query("business_Name") String business_name, @Query("Contact_Person") String contact_persion, @Query("contact_No") String contact_no, @Query("business_email_id") String business_email, @Query("contact_No1") String contact_no1, @Query("address1") String address1, @Query("Land_mark") String landmark);


    //tags are key on server...

    @POST("fabkut/admin/index.php?tag=MarketingSalonEmployeeInsert")
    Observable<ResponseLogin> salonEmployeeApiMethod(@Query("business_id") String business_id, @Query("emp_name") String emp_name, @Query("emp_contact_no") String emp_contact, @Query("spcst") String emp_spec, @Query("adress1") String emp_addr);

    @POST("fabkut/admin/index.php?tag=city")
    Observable<ResponseCity> cityApiMethod();

    @POST("fabkut/admin/index.php?tag=location")
    Observable<ResponseLocation> locationApiMethod(@Query("city_id") int city_id);

    @POST("fabkut/admin/index.php?tag=GetSalonFacilityList")
    Observable<ResponseSalonFacility> salonfacilityShowApiMethod();

    @POST("fabkut/admin/index.php?tag=marketinglist")
    Observable<ResponseMarketinList> marketinglistApiMethod(@Query("user_id") String user_id);


    //tags are key on server...*/
}