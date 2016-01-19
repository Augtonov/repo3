package com.example.tony.myapplication.util;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by tony on 12/21/2015.
 */
public class Util {
//    Retrofit retrofit;

    private static Util instance;

    public static Util getUtils() {

        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    public Util() {
    }

    public long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public long getTimeWithExtraMin() {
        return System.currentTimeMillis()+minInMilliSeconds(10);
    }

    public long minInMilliSeconds(int min) {
        return (long) (min*60*1000);
    }


    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void showSnackBar(String msg, boolean isSuccess, View contentView){

        try {
            Snackbar snackbar = Snackbar.make(contentView, msg, Snackbar.LENGTH_SHORT);
            View view  = snackbar.getView();
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            if (isSuccess){
                view.setBackgroundColor(Color.GREEN);
            }else {
                view.setBackgroundColor(Color.RED);
            }

            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showLog(String msg){

        Log.d(Const.LOG_TAG, "---------" + msg);
    }

}
