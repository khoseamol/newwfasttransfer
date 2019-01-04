package com.example.webplat.amoldesigning.helper;

import com.example.webplat.amoldesigning.Utils.ApplicationConstant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pc3 on 28/11/2016.
 */

public class ApiServiceGenerator {

    public static final String API_BASE_URL = ApplicationConstant.WEBSERVICEURL;
    private static OkHttpClient httpClient =  new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder().addHeader("test", "test").build();
                    return chain.proceed(request);
                }
            })
            //here we adding Interceptor for full level logging
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .build();



    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }

    public static Retrofit retrofit() { // For Error Handing when non-OK response is received from Server
        OkHttpClient httpClient = new OkHttpClient.Builder().build();
        OkHttpClient client = httpClient;
        return builder.client(client).build();
    }
}
