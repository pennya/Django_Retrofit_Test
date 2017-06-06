package com.example.kjh.retrofit_test;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KJH on 2017-06-06.
 */

public class ApplicationController extends Application {
    public final static String TAG = "KJH";
    private static ApplicationController instance;
    public static ApplicationController getInstance(){return instance;}

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationController.instance = this;
    }

    private NetworkService networkService;
    public NetworkService getNetworkService() {return networkService;}

    private String baseUrl;

    public void buildNetworkService(String ip, int port){
        synchronized (ApplicationController.class){
            if (networkService == null){
                //baseUrl = String.format("http://%s:%d/", ip, port);
                baseUrl = String.format("http://%s/", ip);
                Log.i(TAG, baseUrl);
                Gson gson = new GsonBuilder().create();

                GsonConverterFactory factory = GsonConverterFactory.create(gson);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(factory)
                        .build();

                networkService = retrofit.create(NetworkService.class);
            }
        }

    }
}
