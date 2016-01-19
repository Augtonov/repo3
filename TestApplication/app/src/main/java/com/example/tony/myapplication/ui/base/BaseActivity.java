package com.example.tony.myapplication.ui.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.tony.myapplication.util.HideDialogEvent;
import com.example.tony.myapplication.util.ShowDialogEvent;
import com.example.tony.myapplication.util.Util;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

/**
 * Created by tony on 12/21/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {

    ProgressDialog pDialog;

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscriber
    public void showProgressDialog(ShowDialogEvent showDialogEvent) {
        Log.d("hai", "show progress dialog");
        pDialog = ProgressDialog.show(this, "Loading", "loading...", true);

    }

    @Subscriber
    public void hideProgressDialog(HideDialogEvent hideDialogEvent) {

        Log.d("hai", "hide progress dialog");
        if (pDialog == null) {
            return;
        }
        pDialog.dismiss();
        pDialog = null;
    }

    public void showLog(String msg){

        Util.getUtils().showLog(msg);
    }

    public void showSnackBar(String msg, boolean isSuccess){ //showing snackbar
        Util.getUtils().showSnackBar(msg, isSuccess, findViewById(android.R.id.content));
    }

    public boolean isNetworkAvailable() { // check network availability
        return Util.getUtils().isNetworkAvailable(this);
    }



}
