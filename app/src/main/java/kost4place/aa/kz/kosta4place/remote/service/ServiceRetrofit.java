package kost4place.aa.kz.kosta4place.remote.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceRetrofit {
//    private static final String BASE_URL = "http://3.120.172.55:8080/api-1.0-SNAPSHOT/";
    private static final String BASE_URL = "http://192.168.1.245:8080/";
    private static Retrofit ourInstance;

    public static Retrofit getInstance() {
        if (ourInstance == null) {
            ourInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return ourInstance;
    }

    private ServiceRetrofit() {

    }
}
