package com.example.tony.myapplication.webservice_retrofit;

import android.content.Context;
import android.util.Log;

import com.example.tony.myapplication.ui.pojo.frag1.StackOverflowQuestions;
import com.example.tony.myapplication.util.HideDialogEvent;
import com.example.tony.myapplication.util.ShowDialogEvent;

import org.simple.eventbus.EventBus;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by tony on 1/7/2016.
 */
public class WebServiceManager {

    public static WebServiceManager webServiceManager;
    private APIService service;

    public static WebServiceManager getInstance() {
        if (webServiceManager == null) {
            webServiceManager = new WebServiceManager();
        }

        return webServiceManager;
    }
    public WebServiceManager() {
        service = ServiceGenerator.createService(APIService.class);

    }

    public void getTab1List(String tag) {
        EventBus.getDefault().post(new ShowDialogEvent());

        final Call<StackOverflowQuestions> call = service.getList(tag);
        call.enqueue(new Callback<StackOverflowQuestions>() {
            @Override
            public void onResponse(Response<StackOverflowQuestions> response, Retrofit retrofit) {
                EventBus.getDefault().post(new HideDialogEvent());
                if (response.isSuccess()) {
                    EventBus.getDefault().post(response);
                }else {
                    APIError error = ErrorUtils.parseError(response.errorBody(), retrofit);
//                    showLog("ERROR_____"+error.getErrorMessage());
                    Log.d("hai",  error.getErrorMessage());
                }

            }

            @Override
            public void onFailure(Throwable t) {
                EventBus.getDefault().post(new HideDialogEvent());
            }


        });
    }
}
