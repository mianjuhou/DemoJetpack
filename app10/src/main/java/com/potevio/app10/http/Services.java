
package com.potevio.app10.http;

import retrofit2.Retrofit;

public class Services {
    public static <S> S createService(Class<S> serviceClass) {
        return RetrofitUtil.getRetrofit().create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, Retrofit retrofit) {
        return retrofit.create(serviceClass);
    }
}