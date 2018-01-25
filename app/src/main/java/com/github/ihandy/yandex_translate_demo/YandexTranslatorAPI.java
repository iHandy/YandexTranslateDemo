package com.github.ihandy.yandex_translate_demo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Handy on 01/25/2018.
 */

public interface YandexTranslatorAPI {

    @GET("getLangs")
    Call<LangsListResponse> getLangs(@Query("key") String apiKey, @Query("ui") String languageCode);

    @GET("translate")
    Call<TranslateResponse> translate(@Query("key") String apiKey, @Query("text") String text, @Query("lang") String lang);
}
