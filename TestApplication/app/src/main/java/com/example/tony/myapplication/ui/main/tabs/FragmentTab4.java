package com.example.tony.myapplication.ui.main.tabs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tony.myapplication.R;
import com.example.tony.myapplication.ui.base.BaseFragment;
import com.example.tony.myapplication.ui.pojo.frag1.Item;
import com.example.tony.myapplication.ui.pojo.frag1.StackOverflowQuestions;
import com.example.tony.myapplication.webservice_retrofit.APIService;
import com.example.tony.myapplication.webservice_retrofit.ServiceGenerator;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.io.IOException;
import java.util.ArrayList;

import retrofit.Call;

/**
 * Created by tony on 12/21/2015.
 */
public class FragmentTab4 extends BaseFragment {

    private RecyclerView recyclerView;
    private AdapterFragment1 adapter;
    private ArrayList<Item> list;

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
        setRecyclerView();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (isNetworkAvailable()){
            callWebService();
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
        StackOverflowQuestions object = null;

        APIService service = ServiceGenerator.createService(APIService.class);


        Call<StackOverflowQuestions> call = service.getList("android");

        try {
            object = call.execute().body();
        } catch (IOException e) {
//            hideProgress();
            showSnackBar("Connectin Error !", false);
            e.printStackTrace();
        }

//        hideProgress();
        showLog(object.getItems().size() +"");
//        list.addAll(object.getItems());
//        adapter.notifyDataSetChanged();
//        BusProvider.getInstance().post("Event recieved");
        EventBus.getDefault().post("Event Received");

    }

    @Override
    public void onResume() {
        super.onResume();
//        BusProvider.getInstance().register(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
//        BusProvider.getInstance().unregister(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

  /*  public void onEvent(String string) {
        showSnackBar(string, false);
    }*/

    @Subscriber
    public void getObject(String string) {
//        showSnackBar(string, false);
        showLog("From fragment--------"+string);
    }

    /*@Subscribe
    public void getObject(String string) {
        showLog("from fragment------------------"+string);
    }*/
}
