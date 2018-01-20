package com.abhi.fabkutbusiness.retrofit;


import com.abhi.fabkutbusiness.crm.model.ResponseBasicInfo;
import com.abhi.fabkutbusiness.crm.model.ResponseBasicInfoUpdate;
import com.abhi.fabkutbusiness.crm.model.ResponseCrmList;
import com.abhi.fabkutbusiness.crm.model.ResponsePersonalInfo;
import com.abhi.fabkutbusiness.crm.model.ResponsePersonalInfoUpdate;
import com.abhi.fabkutbusiness.crm.model.ResponseSocialInfo;
import com.abhi.fabkutbusiness.crm.model.ResponseSocialInfoUpdate;
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


    @POST("salon/booking/index.php?tag=salonlogin")
    Observable<ResponseModelLogin> loginApiMethod(@Query("email") String user, @Query("password") String pass);

    @POST("salon/booking/index.php?tag=customer")
    Observable<ResponseModelCustomer> customerApiMethod(@Query("business_id") String business_id);

    @POST("salon/booking/index.php?tag=salonemp")
    Observable<ResponseModelEmployee> employeeApiMethod(@Query("business_id") String business_id);

    @POST("salon/booking/index.php?tag=rateinfo")
    Observable<ResponseModelRateInfo> rateInfoApiMethod(@Query("business_id") String business_id);


    @POST("crm/index.php?tag=cstList")
    Observable<ResponseCrmList> CrmListShowApiMethod(@Query("business_id") int business_id);

    @POST("crm/index.php?tag=cstbasic")
    Observable<ResponseBasicInfo> CrmBasicInfoShowApiMethod(@Query("business_id") int business_id, @Query("EndUser_id") int Enduser_id);

    @POST("crm/index.php?tag=cstsocial")
    Observable<ResponseSocialInfo> CrmSocialInfoShowApiMethod(@Query("business_id") int business_id, @Query("EndUser_id") int Enduser_id);

    @POST("crm/index.php?tag=cstpersonal")
    Observable<ResponsePersonalInfo> CrmPersonalInfoShowApiMethod(@Query("business_id") int business_id, @Query("EndUser_id") int Enduser_id);

    @POST("crm/index.php?tag=cstbasicUP")
    Observable<ResponseBasicInfoUpdate> CrmBasicInfoUpdateApiMethod(@Query("business_id") int business_id, @Query("EndUser_id") int EndUser_id, @Query("enduser_name") String enduser_name, @Query("lname") String lname, @Query("gender") String gender, @Query("email") String email, @Query("contact_no") String contact_no, @Query("alternetContact") String alternetContact, @Query("allergies") String allergies, @Query("Profile_Comp_Basic") int Profile_Comp_Basic);

    @POST("crm/index.php?tag=cstPerUP")
    Observable<ResponsePersonalInfoUpdate> CrmPersonalInfoUpdateApiMethod(@Query("business_id") int business_id, @Query("EndUser_id") int EndUser_id, @Query("dob") String dob, @Query("anidate") String anidate, @Query("m_um") int m_um, @Query("Profile_Comp_Personal") int Profile_Comp_Personal);

    @POST("crm/index.php?tag=cstsocialUP")
    Observable<ResponseSocialInfoUpdate> CrmSocialInfoUpdateApiMethod(@Query("business_id") int business_id, @Query("EndUser_id") int EndUser_id, @Query("Social_Home_address") String Social_Home_address, @Query("Social_Delivery_Address") String Social_Delivery_Address, @Query("Social_Mode_Commincation") String Social_Mode_Commincation, @Query("Social_FB_ID") String Social_FB_ID, @Query("Social_Twitter_ID") String Social_Twitter_ID, @Query("Social_whatsApp") String Social_whatsApp, @Query("Profile_Comp_Social") int Profile_Comp_Social);

    @POST("booking/index.php?tag=syncuser1")
    Observable<ResponseModel> addCustomerApiMethod(@Query("business_id") String business_id,
                                                   @Query("enduser_name") String endUser_firstName,
                                                   @Query("gender") String gender,
                                                   @Query("email") String email,
                                                   @Query("contact_no") String contact_no,
                                                   @Query("allergies") String allergies,
                                                   @Query("dob") String dob);

    @POST("booking/index.php?tag=synBooking")
    Observable<ResponseModel> bookingApiMethod(@Query("EnduserID") String EnduserID,
                                               @Query("Salon_ID") String salonId,
                                               @Query("TimeSlot") String timeSlot,
                                               @Query("Empid") String employeeId,
                                               @Query("Services") String services,
                                               @Query("MobileNo") String customerMobile,
                                               @Query("Remark") String remark,
                                               @Query("PrevBooking") String prevBooking,
                                               @Query("BookingDate") String bookingDate,
                                               @Query("SeatNo") String seatNumber,
                                               @Query("Assign_book") String assignBook);



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