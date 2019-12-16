package com.potevio.app10.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class RetrofitUtil {

    public static String baseUrl;
    private static Retrofit retrofit;
    private static Retrofit longRetrofit;

    public static String getBaseUrl() {
        if (baseUrl == null) {
            String ip = "localhost:9001";
            if (ip != null) {
                baseUrl = "http://" + ip + "/jetpack/";
            }
        }
        return baseUrl;
    }

    public static Retrofit createRetrofit(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .client(client)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = createRetrofit(OkHttpUtil.getClient());
        }
        return retrofit;
    }

    public static Retrofit getLongRetrofit() {
        if (longRetrofit == null) {
            longRetrofit = createRetrofit(OkHttpUtil.getLongClient());
        }
        return longRetrofit;
    }

}
