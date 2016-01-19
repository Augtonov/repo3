package com.example.tony.myapplication.webservice_retrofit;

import android.util.Base64;

import com.example.tony.myapplication.util.Const;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by tony on 12/21/2015.
 */
public class ServiceGenerator {
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor(); // for logging and debugging
    private static OkHttpClient httpClient = new OkHttpClient();


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(Const.BAASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        // add logging as last interceptor
        httpClient.interceptors().add(logging);

        /*Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);*/
        return createService(serviceClass, null, null);
    }


    public static <S> S createService(Class<S> serviceClass, String username, String password) { // basic authentication
        if (username != null && password != null) {
            String credentials = username + ":" + password;
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            httpClient.interceptors().clear();
            httpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", basic)
                    .header("Accept", "applicaton/json")
                    .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }


    /*public static <S> S createService(Class<S> serviceClass, final String authToken) { // token authentication (get access token by refresh token)
        if (authToken != null) {
            httpClient.interceptors().clear();
            httpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", authToken)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }*/


    public static <S> S createService(Class<S> serviceClass, final AccessToken token) { // fetching webservice with access token
        if (token != null) {
            httpClient.interceptors().clear();
            httpClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Accept", "applicaton/json")
                            .header("Authorization",
                                    token.getTokenType() + " " + token.getAccessToken())
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}

