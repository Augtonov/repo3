package com.example.tony.myapplication.ui.main.tabs;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tony.myapplication.R;
import com.example.tony.myapplication.db.DatabaseHandler;
import com.example.tony.myapplication.ui.base.BaseFragment;
import com.example.tony.myapplication.ui.pojo.frag1.Item;
import com.example.tony.myapplication.ui.pojo.frag1.StackOverflowQuestions;
import com.example.tony.myapplication.util.Const;
import com.example.tony.myapplication.util.Util;
import com.example.tony.myapplication.webservice_retrofit.APIError;
import com.example.tony.myapplication.webservice_retrofit.APIService;
import com.example.tony.myapplication.webservice_retrofit.ErrorUtils;
import com.example.tony.myapplication.webservice_retrofit.ServiceGenerator;
import com.example.tony.myapplication.webservice_retrofit.WebServiceManager;
import com.google.gson.Gson;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by tony on 12/21/2015.
 */
public class FragmentTab1 extends BaseFragment{

    private RecyclerView recyclerView;
    private AdapterFragment1 adapter;
    private ArrayList<Item> list;
    DatabaseHandler db;
//    ProgressDialog pd;
    private boolean isExpired = false;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tab_1, null);
        initUi(v);
        list = new ArrayList<>();
        db = new DatabaseHandler(getActivity());
        setRecyclerView();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /**
         *  checking url exists in db ,If url is there then check time expired or not
         * if url is not in db, then call webservice otherwise data fetch from db.
         * if time is expired then call new webservice for data fetching.
         * The expiry time is 10 days.
         */

        if (db.ifURLexists(Const.URL_FETCH_ONE)) {

            long expiryTime = Long.parseLong(db.getExpiryTime(Const.URL_FETCH_ONE));

            if (Util.getUtils().getCurrentTime() > expiryTime){

                isExpired = true;
                if (isNetworkAvailable()){
//                    callWebService();
                    WebServiceManager.getInstance().getTab1List("android");
                }else {
                    showSnackBar(getResources().getString(R.string.no_network), false);
                }

            }else {
                Gson gson = new Gson();
                StackOverflowQuestions sss = gson.fromJson(db.getJsonTableList(Const.URL_FETCH_ONE), StackOverflowQuestions.class);
                list.addAll(sss.getItems());
                adapter.notifyDataSetChanged();
            }

        }else {
            if (isNetworkAvailable()){
//                callWebService();
                WebServiceManager.getInstance().getTab1List("android");
            }else {
                showSnackBar(getResources().getString(R.string.no_network), false);
            }
        }


    }


    private void initUi(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.sampleList);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    private void setRecyclerView() {
        adapter = new AdapterFragment1(getActivity(), list);
        recyclerView.setAdapter(adapter);
    }


    private void callWebService() {

//        showProgress();

//        pd = new ProgressDialog(getActivity());
//        pd.show();

//        APIService service = ServiceGenerator.createService(APIService.class);
//        final Call<StackOverflowQuestions> call = service.getList("android");
//        call.enqueue(this);

        // Synchronous call retrofit

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StackOverflowQuestions sample = call.execute().body();
                    threadMsg(sample);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            private void threadMsg(StackOverflowQuestions stack) {
                if (stack != null) {
                    Message msg = handler.obtainMessage();
                    Bundle b = new Bundle();
                    b.putSerializable("m", stack);
                    msg.setData(b);
                    handler.sendMessage(msg);

                }

            }

            private Handler handler = new Handler(){

                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    pd.dismiss();
                    StackOverflowQuestions s = (StackOverflowQuestions) msg.getData().getSerializable("m");
                    showLog(s.getItems().size() +"");
                    list.addAll(s.getItems());
                    adapter.notifyDataSetChanged();

                    EventBus.getDefault().post("Event Received");
                }
            };
        }).start();*/

    }

    /**
     * method call from webservicemanager class
     * @param response
     */

    @Subscriber
    public void getResponseTab1(Response<StackOverflowQuestions> response){

        showLog("response reached");
            showLog(response.body().getItems().size() +"");
            list.addAll(response.body().getItems());
            adapter.notifyDataSetChanged();

            Gson gson = new Gson();
            String json = gson.toJson(response.body());

            showLog("string json----"+json);

            new InsertToDb(json).execute();

    }


    /*@Override
    public void onResponse(Response<StackOverflowQuestions> response, Retrofit retrofit) {

       if (response.isSuccess()) {

           showLog(response.body().getItems().size() +"");
           list.addAll(response.body().getItems());
           adapter.notifyDataSetChanged();

//           EventBus.getDefault().post("Event Received");
           Gson gson = new Gson();
           String json = gson.toJson(response.body());

           showLog("string json----"+json);

           new InsertToDb(json).execute();


       }else {

           APIError error = ErrorUtils.parseError(response.errorBody(), retrofit);
           showLog("ERROR_____"+error.getErrorMessage());
       }
        *//*if (pd.isShowing())
        pd.dismiss();*//*

    }

    @Override
    public void onFailure(Throwable t) {

       *//* if (pd.isShowing())
            pd.dismiss();*//*

        showSnackBar("Connection Error !", false);
    }*/

    /**
     * insert data in to Data Base
     */


    private class InsertToDb extends AsyncTask<Void, Void, Void> {

        private String json;

        public InsertToDb(String json) {
            this.json = json;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                db.open();
                if (isExpired) {
                    db.update(Const.URL_FETCH_ONE, json, String.valueOf(Util.getUtils().getTimeWithExtraMin()));
                    isExpired = false;
                }else {
                    db.insertValueToTableList(json, Const.URL_FETCH_ONE, String.valueOf(Util.getUtils().getTimeWithExtraMin()));

                }
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


    }

    /*@Subscriber
    public void getObject(String string) {
    }*/

}
