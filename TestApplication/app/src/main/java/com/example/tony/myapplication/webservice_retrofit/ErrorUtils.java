package com.example.tony.myapplication.webservice_retrofit;

import com.example.tony.myapplication.ui.pojo.frag1.StackOverflowQuestions;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.annotation.Annotation;

import retrofit.Converter;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by tony on 12/23/2015.
 */
public class ErrorUtils {
    public static APIError parseError(ResponseBody responseBody, Retrofit retrofit) {
        Converter<ResponseBody, APIError> converter =
                retrofit.responseConverter(APIError.class, new Annotation[0]);

        APIError error;

        try {
            error = converter.convert(responseBody);
        } catch (IOException e) {
            return new APIError();
        }

        return error;
    }


    /*public static APIError parseError(Response<StackOverflowQuestions> response, Retrofit retrofit) {
        Converter<ResponseBody, APIError> converter =
                retrofit.responseConverter(APIError.class, new Annotation[0]);

        APIError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError();
        }

        return error;
    }*/
}
