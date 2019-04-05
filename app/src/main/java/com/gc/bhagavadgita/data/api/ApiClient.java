package com.gc.bhagavadgita.data.api;

import com.gc.bhagavadgita.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private static final boolean ADD_LOG = BuildConfig.showLog;
    private static final String BASE_URL = BuildConfig.BASE_URL;

    public static final String clientId = "0szCOpjQqcE7OqRTxCUqr7SurVem4o2ypUrtXXIZ";
    public static final String clientSecret = "bWbzZJcTuJAleukLHQLiZcocufVQsLc7pe9S81sX8CW8CBgXEK";

    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS);
            if (ADD_LOG)
                httpClient.addInterceptor(logging);
            // <-- this is the important line!
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}
