package com.goyalzz.knowyournationhackathon.api;

import com.goyalzz.knowyournationhackathon.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    public static Api api;

    public static Api getInstance() {
        if (api == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            // add your other interceptors …

            // add logging as last interceptor
            httpClient.addInterceptor(logging);
            Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                    .baseUrl(BuildConfig.HOST)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());
            if (BuildConfig.DEBUG)
                retrofitBuilder.client(httpClient.build());
            Retrofit retrofit = retrofitBuilder.build();
            api = retrofit.create(Api.class);
        }
        return api;
    }

}
