package com.example.tony.myapplication.ui.base;

import android.support.v4.app.Fragment;

import com.example.tony.myapplication.util.Util;

import org.simple.eventbus.EventBus;

/**
 * Created by tony on 12/21/2015.
 */
public abstract class BaseFragment extends Fragment{

//    ProgressDialog pDialog;


    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
//        RefWatcher refWatcher = TestApplication.getRefWatcher(getActivity());
//        refWatcher.watch(this);
    }



    public void showSnackBar(String msg, boolean isSuccess){

        Util.getUtils().showSnackBar(msg, isSuccess, getActivity().findViewById(android.R.id.content));

    }

    public void showLog(String msg){
        Util.getUtils().showLog(msg);
    }

    public boolean isNetworkAvailable() {
        return Util.getUtils().isNetworkAvailable(getActivity());
    }

}
