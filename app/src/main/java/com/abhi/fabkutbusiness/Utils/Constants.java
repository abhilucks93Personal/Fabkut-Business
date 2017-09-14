package com.abhi.fabkutbusiness.Utils;

/**
 * Created by abhishekagarwal on 2/9/17.
 */

public class Constants {


    public static int BOOKING_STATUS_CONFIRM = 0;
    public static int BOOKING_STATUS_WAITING = 1;
    public static int BOOKING_STATUS_CANCEL = 2;

    public static int BOOKING_TYPE_OFFLINE = 0;
    public static int BOOKING_TYPE_ONLINE = 1;

    public static String errorMsdOffline = "Please connect to internet.";
    public static String errorMsgMandatory = "All the fields are mandatory";
    public static String errorMsgWrong = "Something went wrong! Please try again after sometime.";

    public static String keySalonProfileData = "keySalonProfileData";
    public static String keySalonCustomerData = "keySalonCustomerData";
    public static String keySalonEmployeeData = "keySalonEmployeeData";
    public static String keySalonRateInfoData = "keySalonRateInfoData";
    public static String keySalonAppointmentsData = "keySalonAppointmentsData";
    public static String keySalonBusinessId = "keySalonBusinessId";
    public static String keySalonBusinessName = "keySalonBusinessName";
    public static String keyLoginCheck = "keyLoginCheck";
    public static String keySalonSeatsNum = "keySalonSeatsNum";
    public static String keySalonOpenTime = "keySalonOpenTime";
    public static String keySalonSeatsStatusList = "keySalonSeatsStatusList";
    public static String keySalonBookingData = "keySalonBookingData";
    public static String keySalonCancelBookingData = "keySalonCancelBookingData";
    public static String keySalonTaxPercentage = "keySalonTaxPercentage";
    public static String keySalonPreviousBalance = "keySalonPreviousBalance";

    public static String keySalonCloseTime = "keySalonCloseTime";
    public static String timeStartAfternoon = "12:00:00.0000000";
    public static String timeEndAfternoon = "16:00:00.0000000";
    public static int slotDifference = 15; // in minutes


    public static String msgBillingUnpaidAmountSync = "Unable to sync outstanding amount with the server.\nPlease check your Internet Connection or ask your customer to make full payment of this billing only.";
    public static String displayDateFormat = "MMM dd, yyyy";
}
