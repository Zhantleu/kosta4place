package kost4place.aa.kz.kosta4place.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class KostaServiceRetrofit {
    private static final String BASE_URL = "http://52.59.190.133:8080/api-1.0-SNAPSHOT/";
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

    private KostaServiceRetrofit() {

    }
//    public Observable<String> returnPlace() { // Преобразовать и вернуть стринг
//        return api.getPlace()
//                .flatMapIterable(x -> x)
//                .distinct();
//    }
}
