package com.example.tony.myapplication.webservice_retrofit;

import com.example.tony.myapplication.ui.pojo.frag1.StackOverflowQuestions;
import com.example.tony.myapplication.util.Const;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by tony on 12/21/2015.
 */
public interface APIService {


@GET(Const.URL_FETCH_ONE)
    Call<StackOverflowQuestions> getList(@Query("tagged") String tagged);

}
