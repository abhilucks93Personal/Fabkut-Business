package com.abhi.fabkutbusiness.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.fabkutbusiness.R;
import com.abhi.fabkutbusiness.booking.view.BookNowActivity;
import com.abhi.fabkutbusiness.main.LoginActivity;
import com.abhi.fabkutbusiness.main.NavigationMainActivity;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointments;
import com.abhi.fabkutbusiness.main.model.ResponseModelAppointmentsData;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomer;
import com.abhi.fabkutbusiness.main.model.ResponseModelCustomerData;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployee;
import com.abhi.fabkutbusiness.main.model.ResponseModelEmployeeData;
import com.abhi.fabkutbusiness.main.model.ResponseModelLogin;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfo;
import com.abhi.fabkutbusiness.main.model.ResponseModelRateInfoData;
import com.abhi.fabkutbusiness.main.model.ResponseModelSeats;
import com.abhi.fabkutbusiness.main.model.ResponseModelSeatsData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;


/**
 * @author Wildnet technologies
 */
public class Utility {


    private static final int REQUEST_LOCATION = 2000;

    public static void datePickerDialog(Activity context, DatePickerDialog.OnDateSetListener listner) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(context, listner,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        dialog.show();
    }

    public static void datePickerDialogDob(Activity context, DatePickerDialog.OnDateSetListener listner) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(context, listner,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);

        dialog.show();
    }

    public static String formatDateForDisplay(String inputDate, String inputFormat, String outputFormat) {

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
            Log.e(TAG, "ParseException - dateFormat");
        }

        return outputDate;

    }

    public static Uri getImageUri(Context context, Bitmap mBitmap) {
        Uri uri = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            // Calculate inSampleSize
            // options.inSampleSize = calculateInSampleSize(options, 100, 100);
            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            Bitmap newBitmap = Bitmap.createScaledBitmap(mBitmap, 200, 200,
                    true);
            File file = new File(context.getFilesDir(), "Image"
                    + new Random().nextInt() + ".jpeg");
            FileOutputStream out = context.openFileOutput(file.getName(),
                    Context.MODE_WORLD_READABLE);

            newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            //Bitmap compressedImageBitmap = Compressor.getDefault(context).compressToBitmap(file1);
            out.flush();
            out.close();
            //get absolute path
            String realPath = file.getAbsolutePath();
            File f = new File(realPath);
            uri = Uri.fromFile(f);

        } catch (Exception e) {
            Log.e("Your Error Message", e.getMessage());
        }
        return uri;
    }


    public static String getRealPathFromUri(Context mContext, Uri mUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(mContext, mUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public static void setStatusBarTranslucent(Activity context, boolean makeTranslucent) {
        if (makeTranslucent) {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public static void showSnackBar(Activity context, String str) {
        View view = context.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view, str, Snackbar.LENGTH_LONG).show();

    }

    public static void showSnackBar(View view, String str) {
        Snackbar.make(view, str, Snackbar.LENGTH_LONG).show();

    }

    public static void displayAlert(final Context context, String title, String msg) {
        new AlertDialog.Builder(context).setMessage(msg).setTitle(title).setCancelable(true).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Do your code here
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();

            }
        }).show();
    }

    public static void showEditTextsAsMandatory(TextInputLayout... ets) {
        for (TextInputLayout et : ets) {


            String hint = et.getHint().toString() + "  ";

            et.setHint(Html.fromHtml(hint + "<font color=\"#ff0000\">" + "* " + "</font>"));
        }
    }

    public static void showEditTextsAsMandatory(EditText... ets) {
        for (EditText et : ets) {


            String hint = et.getHint().toString() + "  ";

            et.setHint(Html.fromHtml(hint + "<font color=\"#ff0000\">" + "* " + "</font>"));
        }
    }


    private static void call() {


    }

    public static boolean checkPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            int res = context.checkCallingOrSelfPermission(permissions[0]);
            if (res != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        //String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        String expression = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static String convertDate(String str) {

        String[] separated = str.split("T");
        String date = formatDateFromString("yyyy-MM-dd", "MMM d, yyyy", separated[0]);

        return date;
    }

    public static String convertDateOnly(String str) {

        String[] separated = str.split("T");
        String date = separated[0];

        return date;
    }


    public static String formatDateFromString(String inputFormat, String outputFormat, String inputDate) {

        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
            Log.e(TAG, "ParseException - dateFormat");
        }

        return outputDate;

    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static void showDevelopmentToast(Context context) {
        Toast.makeText(context, "Under Development", Toast.LENGTH_SHORT).show();
    }

    public static void addPreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static void addPreferences(Context context, String key, Boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static String getPreferences(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE);
        String text = prefs.getString(key, "");
        return text;
    }

    public static Double getPreferences(Context context, String key, Double defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE);
        String text = prefs.getString(key, "");
        if (text.equals(""))
            return defaultValue;
        return Double.valueOf(text);
    }

    public static int getPreferences(Context context, String key, int defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE);
        String text = prefs.getString(key, "");
        if (text.equals(""))
            return defaultValue;
        return Integer.parseInt(text);
    }

    public static Boolean getPreferences(Context context, String key, boolean defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE);
        Boolean text = prefs.getBoolean(key, defaultValue);
        return text;
    }


    //------------------ offline data ------------------------------------

    // User Profile Data

    public static void addPreferencesUserData(Context context, String key, ResponseModelLogin responseModelLogin) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(responseModelLogin);
        editor.putString(key, json);
        editor.commit();
    }

    public static ResponseModelLogin getResponseModelLogin(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        ResponseModelLogin responseModelLogin = gson.fromJson(json, ResponseModelLogin.class);

        return responseModelLogin;

    }

    // User Customer Data

    public static void addPreferencesCustomerData(Context context, String key, ResponseModelCustomer responseModelCustomer) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(responseModelCustomer);
        editor.putString(key, json);
        editor.commit();
    }

    public static ResponseModelCustomer getResponseModelCustomer(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        ResponseModelCustomer responseModelCustomer = gson.fromJson(json, ResponseModelCustomer.class);

        return responseModelCustomer;

    }

    // User Employee Data

    public static void addPreferencesEmployeeData(Context context, String key, ResponseModelEmployee responseModelEmployee) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(responseModelEmployee);
        editor.putString(key, json);
        editor.commit();
    }

    public static ResponseModelEmployee getResponseModelEmployee(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        ResponseModelEmployee responseModelEmployee = gson.fromJson(json, ResponseModelEmployee.class);

        return responseModelEmployee;

    }

    // User Rate Info Data

    public static void addPreferencesRateInfoData(Context context, String key, ResponseModelRateInfo responseModelRateInfo) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(responseModelRateInfo);
        editor.putString(key, json);
        editor.commit();
    }

    public static ResponseModelRateInfo getResponseModelRateInfo(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        ResponseModelRateInfo responseModelRateInfo = gson.fromJson(json, ResponseModelRateInfo.class);

        return responseModelRateInfo;

    }

    // Active Booking Data

    public static void addPreferencesBookingData(Context context, String key, ResponseModelAppointments responseModelAppointments) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(responseModelAppointments);
        editor.putString(key, json);
        editor.commit();
    }

    public static ResponseModelAppointments getResponseModelBookings(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        ResponseModelAppointments responseModelAppointments = gson.fromJson(json, ResponseModelAppointments.class);

        return responseModelAppointments;

    }

    // Canceled Booking Data

    public static void addPreferencesCancelBookingData(Context context, String key, ResponseModelAppointments responseModelAppointments) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(responseModelAppointments);
        editor.putString(key, json);
        editor.commit();
    }

    public static ResponseModelAppointments getResponseModelCancelBookings(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        ResponseModelAppointments responseModelAppointments = gson.fromJson(json, ResponseModelAppointments.class);

        return responseModelAppointments;

    }


    public static void addPreferencesSeatsData(Context context, String key, ResponseModelSeats responseModelSeats) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(responseModelSeats);
        editor.putString(key, json);
        editor.commit();
    }

    public static ResponseModelSeats getResponseModelSeats(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        ResponseModelSeats responseModelSeats = gson.fromJson(json, ResponseModelSeats.class);

        return responseModelSeats;

    }

    // Advance Appointments Data

    public static void addPreferencesAppointmentsData(Context context, String key, ResponseModelAppointments responseModelAppointments) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(responseModelAppointments);
        editor.putString(key, json);
        editor.commit();
    }

    public static ResponseModelAppointments getResponseModelAppointments(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        ResponseModelAppointments responseModelAppointments = gson.fromJson(json, ResponseModelAppointments.class);

        return responseModelAppointments;

    }


    // Seat Status List

    public static void addPreferencesSeatStatusData(Context context, String key, ArrayList<String> seatStatusData) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(seatStatusData);
        editor.putString(key, json);
        editor.commit();
    }

    public static ArrayList<String> getSeatStatusData(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, "");
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> arrays = gson.fromJson(json, type);

        return arrays;

    }


    public static void clearPreferenceData(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences("Preferences_", Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.commit();
    }

    public static boolean lengthValidation(String str) {
        if (str.length() > 0)
            return true;
        else
            return false;
    }

    public static boolean isInternetConnected(Activity mContext) {

		/*
         * final ConnectivityManager connec = (ConnectivityManager)
		 * mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		 *
		 * if (connec != null&& (connec.getNetworkInfo(1).getState() ==
		 * NetworkInfo.State.CONNECTED)|| (connec.getNetworkInfo(0).getState()
		 * == NetworkInfo.State.CONNECTED)) { return true; } return false;
		 */
        hideKeyboard(mContext);
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            showSnackBar(mContext, Constants.errorMsdOffline);
        }

        return false;
    }

    public static void showToast(Context mContext, String string) {
        Toast t = Toast.makeText(mContext, string, Toast.LENGTH_SHORT);
        t.show();

    }

    public static boolean isLocationEnabled(Context context) {

        LocationManager lm = null;
        boolean gps_enabled = false, network_enabled = false;
        if (lm == null) {
            lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        }
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {

        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        if (gps_enabled == true && network_enabled == true) {
            return true;
        } else {
            return false;
        }

    }

    public static String printnotificationDifference(Date endDate) {

        //milliseconds
        StringBuilder updatedTime = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        Date startDate = null;
        try {
            startDate = dateFormat.parse(dateFormat.format(cal.getTime()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long different = startDate.getTime() - endDate.getTime();

        //       System.out.println("startDate : " + startDate);
        //       System.out.println("endDate : "+ endDate);
        //       System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays,
                elapsedHours, elapsedMinutes, elapsedSeconds);
        if (elapsedDays > 0) {
            updatedTime.append(elapsedDays + " days");
            return updatedTime.toString();

        }
        if (elapsedHours > 0) {
            updatedTime.append(elapsedHours + " hour");
            return updatedTime.toString();


        }
        if (elapsedMinutes > 0) {
            updatedTime.append(elapsedMinutes + " minutes");
            return updatedTime.toString();


        }
        if (elapsedSeconds > 0) {
            updatedTime.append(elapsedSeconds + " second");
            return updatedTime.toString();


        }
        return updatedTime.toString();

    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static String getEncoded64ImageStringFromBitmap(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

        return imgString;
    }

    public static Bitmap getBitmapFromUri(Context context, Uri imageUri) {

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static String getEncoded64ImageStringFromUri(Context context, Uri mImageUri) {

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), mImageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

        return imgString;

    }


    public static String getLoginErrorMessage(int loginType) {
        switch (loginType) {
            case 0:
                return "This email ID is already registered.";
            case 1:
                return "This email ID is already registered. Login with Facebook";
            case 2:
                return "This email ID is already registered. Login with Gmail";
        }
        return "This email ID is already registered.";
    }


    public static void showTextViewsAsMandatory(TextView... tvs) {
        for (TextView tv : tvs) {
            String hint = tv.getHint().toString() + " ";
            tv.setHint(Html.fromHtml(hint + "<font color=\"#ff0000\">" + "* " + "</font>"));
        }
    }

    public static void showDialog(Activity activity, String alert, String message) {
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(activity);

        // set title
        //alertDialogBuilder.setTitle(alert);

        // set dialog message
        alertDialogBuilder
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        // create alert dialog
        android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }


    public static void shareImageOnly(Activity activity, ImageView imageView) {
        try {
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            if (drawable != null) {
                Bitmap bitmap = drawable.getBitmap();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_STREAM, Utility.getLocalBitmapUri(activity.getApplicationContext(), bitmap));
                activity.startActivity(Intent.createChooser(i, "Share with"));
            }
        } catch (Exception e) {
            Utility.showToast(activity.getApplicationContext(), "Cannot share this post");
        }

    }

    public static void shareImagesOnly(Activity activity, ArrayList<ImageView> imageViews) {
        try {
            ArrayList<Uri> files = new ArrayList<>();
            int index = 0;
            for (ImageView iv : imageViews) {
                BitmapDrawable drawable = (BitmapDrawable) iv.getDrawable();
                if (drawable != null) {
                    Bitmap bitmap = drawable.getBitmap();
                    Uri uri = Utility.getLocalBitmapUri2(activity.getApplicationContext(), bitmap, index);
                    files.add(uri);
                }
                index++;
            }

            Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
            i.setType("image/*");

            i.putParcelableArrayListExtra(Intent.EXTRA_STREAM, files);
            activity.startActivity(Intent.createChooser(i, "Share with"));

        } catch (Exception e) {
            Utility.showToast(activity.getApplicationContext(), "Cannot share this post");
        }

    }

    public static Intent shareImageIntent(Context context, ImageView imageView) {
        Intent i = null;
        try {
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            if (drawable != null) {
                Bitmap bitmap = drawable.getBitmap();
                i = new Intent(Intent.ACTION_SEND);
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_STREAM, Utility.getLocalBitmapUri(context, bitmap));

            }
        } catch (Exception e) {
            Utility.showToast(context, "Cannot share this post");
        }

        return i;
    }

    public static Uri getLocalBitmapUri(Context context, Bitmap bmp) {
        Uri bmpUri = null;
        try {
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "image.png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    public static Uri getLocalBitmapUri2(Context context, Bitmap bmp, int i) {
        Uri bmpUri = null;
        try {
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "" + i + "image.png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    public static void buildAlertMessageNoGps(final Activity _activity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(_activity);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        _activity.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        if (!alert.isShowing())
            alert.show();
    }

    public static boolean isGpsEnabled(Activity activity) {
        boolean isEnabled = false;
        LocationManager locationManager = (LocationManager) activity.getApplicationContext().getSystemService(activity.getApplicationContext().LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // Check Permissions Now
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION);
                isEnabled = false;
            } else {
                isEnabled = true;
            }
        }

        return isEnabled;
    }

    public static void getAddressFromLocation(final double latitude, final double longitude,
                                              final Context context, final Handler handler) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;
                try {
                    List<Address> addressList = geocoder.getFromLocation(
                            latitude, longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);
                        StringBuilder sb = new StringBuilder();
                        sb.append(address.getLocality()).append(", ");
                        sb.append(address.getAdminArea()).append(", ");
                        sb.append(address.getCountryName());
                        result = sb.toString();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Unable to connect Geocoder", e);
                } finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if (result != null) {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("address", result);
                        message.setData(bundle);
                    } else {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        result = "";
                        bundle.putString("address", result);
                        message.setData(bundle);
                    }
                    message.sendToTarget();
                }
            }
        };
        thread.start();
    }


    public static ArrayList<String> getTimeSlots(String _openTime, String _closeTime) {

        ArrayList<String> slots = new ArrayList<>();
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            String openTime = getCurrentDate("dd/MM/yyyy") + " " + _openTime.split("\\.")[0];
            Date dateOpenTime = sdf.parse(openTime);

            String closeTime = getCurrentDate("dd/MM/yyyy") + " " + _closeTime.split("\\.")[0];
            Date dateCloseTime = sdf.parse(closeTime);

            long dif = dateOpenTime.getTime();

            while (dif < dateCloseTime.getTime()) {
                Date slot = new Date(dif);

                SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
                String time = localDateFormat.format(slot);
                slots.add(time);
                System.out.println("slot" + time);
                dif += (Constants.slotDifference * 60 * 1000);
            }


            return slots;

        } catch (ParseException e) {
            e.printStackTrace();
            slots = new ArrayList<>();
            return slots;

        }

    }


    public static ArrayList<String> getSelectedSlotList(String date, String _slot, int slotSelection, ArrayList<String> bookedSlots) {
        ArrayList<String> slots = new ArrayList<>();
        try {

            SimpleDateFormat sdf = new SimpleDateFormat(Constants.displayDateFormat + " HH:mm");

            String startTime = date + " " + _slot;
            Date dateStartTime = sdf.parse(startTime);


            long dif = dateStartTime.getTime();

            for (int i = 0; i <= slotSelection; i++) {
                Date slot = new Date(dif);
                SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
                String time = localDateFormat.format(slot);

                if (bookedSlots.contains(date + "/" + time)) {
                    slots = null;
                    break;
                } else {
                    slots.add(date + "/" + time);
                }
                System.out.println("slot" + time);
                dif += (Constants.slotDifference * 60 * 1000);
            }

            return slots;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;

        }
    }

    public static String getCurrentDate(String format) {

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat(format);

        return df.format(c.getTime());
    }

    public static String getFormattedServiceList(ArrayList<ResponseModelRateInfoData> services) {

        String formattedService = "";
        if (services != null)
            for (ResponseModelRateInfoData data : services) {
                formattedService += data.getSub_service_name() + " ,";
            }

        if (formattedService.length() > 0)
            return formattedService.substring(0, formattedService.length() - 1);
        else
            return "";
    }

    public static String getFormattedSlotTime(ArrayList<String> slots, String bookingDate) {

        String slotTime = "";


        String[] slotArray = slots.get(0).split("/");
        slotTime += slotArray[1] + " - ";

        try {

            SimpleDateFormat sdf = new SimpleDateFormat(Constants.displayDateFormat + " HH:mm");

            String _time = slots.get(slots.size() - 1);
            Date dateTime = sdf.parse(_time);

            long _lastTime = dateTime.getTime() + (Constants.slotDifference * 60 * 1000);

            Date slot = new Date(_lastTime);

            SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
            String lastTime = localDateFormat.format(slot);

            slotArray = lastTime.split("/");
            slotTime += slotArray[1];

            //  slotTime += lastTime;
        } catch (Exception e) {

            slotArray = slots.get(slots.size() - 1).split("/");
            slotTime += slotArray[1];

            //
            //
            //
            //
            // slotTime += slots.get(slots.size() - 1);
        }
        return slotTime;
    }

    public static ArrayList<ImageView> getCustomSeatImageViews(final Activity activity, LinearLayout llSeats) {

        String seatNum = getPreferences(activity, Constants.keySalonSeatsNum);

        int seats = Integer.parseInt(seatNum);

        ArrayList<ImageView> seatImageViews = new ArrayList<>();

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        );

        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        for (int i = 0; i < seats; i++) {
            final String _seatNum = "" + i;
            final ImageView imageView = new ImageView(activity);
            imageView.setTag("0");
            imageView.setLayoutParams(param);
            imageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.gray_sheet));
           /* imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imageView.getTag().equals("0")) {
                        activity.startActivity(new Intent(activity, BookNowActivity.class)
                                .putExtra("seatNum", _seatNum));
                    }
                }
            });*/
            seatImageViews.add(imageView);
            layout.addView(imageView);
        }
        llSeats.removeAllViews();
        llSeats.addView(layout);

        return seatImageViews;
    }

    public static ArrayList<ImageView> refreshSeats(Activity activity, LinearLayout llSeats, ArrayList<ImageView> seatImageViews) {
        ArrayList<String> seatStatus = getSeatStatusData(activity, Constants.keySalonSeatsStatusList);

        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        if (seatStatus.size() == seatImageViews.size()) {
            int pos = 0;
            for (ImageView imageView : seatImageViews) {
                imageView.setTag(seatStatus.get(pos));
                if (seatStatus.get(pos).equals("0")) {
                    imageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.gray_sheet));
                } else if (seatStatus.get(pos).equals("1")) {
                    imageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.red_sheet));
                }
                if (imageView.getParent() != null)
                    ((ViewGroup) imageView.getParent()).removeView(imageView);
                layout.addView(imageView);
                pos++;
            }
        }

        llSeats.removeAllViews();
        llSeats.addView(layout);


        return seatImageViews;

    }

    public static String getEmptySeatNum(Activity activity) {

        ArrayList<String> seatStatus = getSeatStatusData(activity, Constants.keySalonSeatsStatusList);

        int pos = 0;
        for (String seat : seatStatus) {
            if (seat.equals("0")) {
                return "" + pos;
            }
            pos++;
        }

        return "-1";
    }

    public static String getBookingTypeText(int bookingType) {

        if (bookingType == 0)
            return "Walkin Booking";
        else
            return "Online Booking";

    }

    public static void bookSeat(Activity activity, ResponseModelAppointmentsData data) {
        ArrayList<String> seatStatusList = Utility.getSeatStatusData(activity, Constants.keySalonSeatsStatusList);

        int seat = Integer.parseInt(data.getSeatNumber());

        if (seat >= 0) {
            seatStatusList.set(seat, "1");

            Utility.addPreferencesSeatStatusData(activity, Constants.keySalonSeatsStatusList, seatStatusList);


            ResponseModelAppointments responseModelBookings = Utility.getResponseModelBookings(activity, Constants.keySalonBookingData);
            if (responseModelBookings != null) {
                responseModelBookings.getData().add(data);
            }
            Utility.addPreferencesBookingData(activity, Constants.keySalonBookingData, responseModelBookings);

            ResponseModelSeats seatsData = Utility.getResponseModelSeats(activity, Constants.keySalonSeatsData);
            if (seatsData.getData().get(seat) != null) {
                seatsData.getData().get(seat).setBookingData(data);

            }
            Utility.addPreferencesSeatsData(activity, Constants.keySalonSeatsData, seatsData);


        } else {
            Utility.showToast(activity, "Sorry! Could not book");
        }


    }

    public static void releaseSeat(Activity activity, String seatNum, int dataPos) {
        if (dataPos >= 0) {
            ArrayList<String> seatStatusList = Utility.getSeatStatusData(activity, Constants.keySalonSeatsStatusList);
            int seat = Integer.parseInt(seatNum);
            if (seat >= 0) {
                seatStatusList.set(seat, "0");

                Utility.addPreferencesSeatStatusData(activity, Constants.keySalonSeatsStatusList, seatStatusList);

                ResponseModelAppointments responseModelBookings = Utility.getResponseModelBookings(activity, Constants.keySalonBookingData);
                if (responseModelBookings != null) {

                    ResponseModelSeats seatsData = Utility.getResponseModelSeats(activity, Constants.keySalonSeatsData);

                    if (seatsData.getData().get(seat) != null) {
                        seatsData.getData().get(seat).getSlots().removeAll(responseModelBookings.getData().get(dataPos).getSlots());
                    }
                    Utility.addPreferencesSeatsData(activity, Constants.keySalonSeatsData, seatsData);
                    responseModelBookings.getData().remove(dataPos);
                }
                Utility.addPreferencesBookingData(activity, Constants.keySalonBookingData, responseModelBookings);

            } else {
                Utility.showToast(activity, "Sorry! Something went wrong in releasing respective seat.");
            }
        }
    }


    public static void cancelBooking(Activity activity, ResponseModelAppointmentsData appointmentsData, int pos) {
        appointmentsData.setBookingStatus(Constants.BOOKING_STATUS_CANCEL);
        ResponseModelAppointments responseModelCancelBookings = Utility.getResponseModelCancelBookings(activity, Constants.keySalonCancelBookingData);

        if (responseModelCancelBookings != null) {
            responseModelCancelBookings.getData().add(appointmentsData);
        }
        Utility.addPreferencesCancelBookingData(activity, Constants.keySalonCancelBookingData, responseModelCancelBookings);

        ResponseModelAppointments responseModelAppointments = Utility.getResponseModelAppointments(activity, Constants.keySalonAppointmentsData);

        if (responseModelAppointments != null) {

            responseModelAppointments.getData().remove(pos);
            Utility.addPreferencesAppointmentsData(activity, Constants.keySalonAppointmentsData, responseModelAppointments);

        }

    }


    public static void setEmployeeSelectedSlots(Activity activity, String emp_id, ArrayList<String> slots) {
        ResponseModelEmployee responseModelEmployees = Utility.getResponseModelEmployee(activity, Constants.keySalonEmployeeData);

        for (ResponseModelEmployeeData data : responseModelEmployees.getData()) {
            if (data.getEmp_id().equals(emp_id)) {
                data.getBookedSlots().addAll(slots);
                break;
            }
        }

        Utility.addPreferencesEmployeeData(activity, Constants.keySalonEmployeeData, responseModelEmployees);


    }

    public static void releaseEmployeeSelectedSlots(Activity activity, String emp_id, ArrayList<String> slots) {
        ResponseModelEmployee responseModelEmployees = Utility.getResponseModelEmployee(activity, Constants.keySalonEmployeeData);

        for (ResponseModelEmployeeData data : responseModelEmployees.getData()) {
            if (data.getEmp_id().equals(emp_id)) {
                for (String slot : slots) {
                    data.getBookedSlots().remove(slot);
                }
                break;
            }
        }

        Utility.addPreferencesEmployeeData(activity, Constants.keySalonEmployeeData, responseModelEmployees);


    }

    public static int getTaxAmount(int subTotal, Double taxPercentage) {

        int taxAmount;

        try {
            taxAmount = (int) (subTotal * (taxPercentage / 100));
        } catch (Exception e) {
            return 0;
        }
        return taxAmount;
    }

    public static int getBillingSubTotalAmount(ArrayList<ResponseModelRateInfoData> services) {

        int subTotal = 0;

        for (ResponseModelRateInfoData data : services) {
            subTotal += Integer.parseInt(data.getRate());
        }

        return subTotal;
    }

    public static void updateOutstandingAmount(Activity activity, int unpaidAmount) {

        int previousBalance = getPreferences(activity, Constants.keySalonPreviousBalance, 0);
        previousBalance += unpaidAmount;
        addPreferences(activity, Constants.keySalonPreviousBalance, "" + previousBalance);
    }

    public static int getPreviousBalance(Activity activity, String customerId) {


        ResponseModelCustomer dataCustomer = Utility.getResponseModelCustomer(activity, Constants.keySalonCustomerData);

        for (ResponseModelCustomerData data : dataCustomer.getData()) {

            if (data.getCustomerId().equals(customerId)) {

                return data.getPrevious_balance();
            }
        }

        return 0;
    }

    public static void setPreviousBalance(Activity activity, String customerId, int unpaidAmount) {


        ResponseModelCustomer dataCustomer = Utility.getResponseModelCustomer(activity, Constants.keySalonCustomerData);

        for (ResponseModelCustomerData data : dataCustomer.getData()) {

            if (data.getCustomerId().equals(customerId)) {

                data.setPrevious_balance(data.getPrevious_balance() + unpaidAmount);
                break;
            }
        }


        Utility.addPreferencesCustomerData(activity, Constants.keySalonCustomerData, dataCustomer);
    }

    public static long getTotalRevenue(Activity activity, String customerId) {


        ResponseModelCustomer dataCustomer = Utility.getResponseModelCustomer(activity, Constants.keySalonCustomerData);

        for (ResponseModelCustomerData data : dataCustomer.getData()) {

            if (data.getCustomerId().equals(customerId)) {

                return data.getTotal_revenue();
            }
        }

        return 0;
    }

    public static void setTotalRevenue(Activity activity, String customerId, long totalAmount) {


        ResponseModelCustomer dataCustomer = Utility.getResponseModelCustomer(activity, Constants.keySalonCustomerData);

        for (ResponseModelCustomerData data : dataCustomer.getData()) {

            if (data.getCustomerId().equals(customerId)) {

                data.setTotal_revenue(data.getTotal_revenue() + totalAmount);
                break;
            }
        }


        Utility.addPreferencesCustomerData(activity, Constants.keySalonCustomerData, dataCustomer);
    }

    public static int getTotalVisits(Activity activity, String customerId) {


        ResponseModelCustomer dataCustomer = Utility.getResponseModelCustomer(activity, Constants.keySalonCustomerData);

        for (ResponseModelCustomerData data : dataCustomer.getData()) {

            if (data.getCustomerId().equals(customerId)) {

                return data.getTotal_visits();
            }
        }

        return 0;
    }

    public static void setTotalVisits(Activity activity, String customerId) {


        ResponseModelCustomer dataCustomer = Utility.getResponseModelCustomer(activity, Constants.keySalonCustomerData);

        for (ResponseModelCustomerData data : dataCustomer.getData()) {

            if (data.getCustomerId().equals(customerId)) {

                data.setTotal_visits(data.getTotal_visits() + 1);
                break;
            }
        }


        Utility.addPreferencesCustomerData(activity, Constants.keySalonCustomerData, dataCustomer);
    }


    public static boolean isSeatAvailable(Activity activity, String startSlot, String endSlot) {

        ResponseModelSeats seatsData = Utility.getResponseModelSeats(activity, Constants.keySalonSeatsData);
        if (seatsData.getData() != null) {

            for (ResponseModelSeatsData data : seatsData.getData()) {

                if (!(data.getSlots().contains(startSlot) || data.getSlots().contains(endSlot))) {
                    return true;
                }

            }

        }

        return false;
    }


    public static int getAvailableSeat(Activity activity, String startSlot, String endSlot) {

        ResponseModelSeats seatsData = Utility.getResponseModelSeats(activity, Constants.keySalonSeatsData);
        if (seatsData.getData() != null) {

            int index = 0;
            for (ResponseModelSeatsData data : seatsData.getData()) {

                if (!(data.getSlots().contains(startSlot) || data.getSlots().contains(endSlot))) {
                    return index;
                }

                index++;
            }

        }

        return -1;
    }

    public static boolean isCurrentBooking(String bookingDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.displayDateFormatWithTime);

        String currDate = getCurrentDate(Constants.displayDateFormatWithTime);

        try {
            Date date_current = simpleDateFormat.parse(currDate);
            Date date_booking = simpleDateFormat.parse(bookingDate);

            // in milliseconds
            long difference = date_booking.getTime() - date_current.getTime();

            // in minutes
            long minutes = difference / (1000 * 60);

            if (minutes < 15)
                return true;


        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

    public static void updateSeatSlots(Activity activity, String seatNumber, ArrayList<String> slots) {

        int seat = Integer.parseInt(seatNumber);
        if (seat >= 0) {
            ResponseModelSeats seatsData = Utility.getResponseModelSeats(activity, Constants.keySalonSeatsData);
            if (seatsData.getData().get(seat) != null) {
                seatsData.getData().get(seat).getSlots().addAll(slots);
            }
            Utility.addPreferencesSeatsData(activity, Constants.keySalonSeatsData, seatsData);
        }

    }

    public static ArrayList<String> getElapsedSlots(Activity activity) {

        String _openTime = Utility.getPreferences(activity, Constants.keySalonOpenTime);

        ArrayList<String> slots = new ArrayList<>();
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            String currDate = getCurrentDate(Constants.displayDateFormat);
            String openTime = getCurrentDate("dd/MM/yyyy") + " " + _openTime.split("\\.")[0];
            Date dateOpenTime = sdf.parse(openTime);

            String closeTime = getCurrentDate("dd/MM/yyyy HH:mm:ss");
            Date dateCloseTime = sdf.parse(closeTime);

            long dif = dateOpenTime.getTime();

            while (dif < dateCloseTime.getTime()) {
                Date slot = new Date(dif);

                SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
                String time = localDateFormat.format(slot);
                slots.add(currDate + "/" + time);
                System.out.println("slot" + time);
                dif += (Constants.slotDifference * 60 * 1000);
            }

            slots.remove(slots.size() - 1);
            return slots;

        } catch (ParseException e) {
            e.printStackTrace();
            slots = new ArrayList<>();
            return slots;

        }
    }

    public static boolean checkSlot(Activity activity, ArrayList<String> slot) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.displayDateFormatWithTime);

        String currDate = getCurrentDate(Constants.displayDateFormatWithTime);

        try {
            Date date_current = simpleDateFormat.parse(currDate);
            Date date_booking_start = simpleDateFormat.parse(slot.get(0));
            Date date_booking_end = simpleDateFormat.parse(slot.get(slot.size() - 1));

            if (date_current.getTime() > date_booking_start.getTime() && date_current.getTime() < date_booking_end.getTime()) {
                return true;
            } else if (date_current.getTime() < date_booking_start.getTime()) {
                Utility.showToast(activity, "Please wait till the booking time or Reschedule booking.");
                return false;
            } else {
                Utility.showToast(activity, "Booking slot is expired. Please reschedule.");
                return false;
            }


        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }


    }

    public static boolean isSeatAvailable(Activity activity, int seatNum) {


        ArrayList<String> seatStatus = getSeatStatusData(activity, Constants.keySalonSeatsStatusList);

        if (seatStatus.size() > seatNum && seatStatus.get(seatNum).equals("0")) {
            return true;
        }


        return false;
    }
}
