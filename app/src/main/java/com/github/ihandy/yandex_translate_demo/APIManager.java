package com.github.ihandy.yandex_translate_demo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Handy on 01/25/2018.
 */

public class APIManager {

    public static final String API_KEY = "YOUR_API_KEY";

    private static APIManager instance= null;
    private final YandexTranslatorAPI mYandexTranslatorAPI;

    public static APIManager getInstance() {
       if (instance == null){
           instance = new APIManager();
       }
        return instance;

    }

    public APIManager() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://translate.yandex.net/api/v1.5/tr.json/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        mYandexTranslatorAPI = retrofit.create(YandexTranslatorAPI.class);
    }

    public YandexTranslatorAPI getYandexTranslatorAPI() {
        return mYandexTranslatorAPI;
    }
}
