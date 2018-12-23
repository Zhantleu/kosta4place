package kost4place.aa.kz.kosta4place;

import android.content.Context;
import android.util.Log;

import kost4place.aa.kz.kosta4place.local.data.PlaceORM;
import kost4place.aa.kz.kosta4place.remote.api.KostaApi;
import kost4place.aa.kz.kosta4place.remote.service.ServiceRetrofit;
import kost4place.aa.kz.kosta4place.utils.ConvertUtils;
import retrofit2.Retrofit;

public class Test {
    Context context;

    @org.junit.Test
    public void example() {
        PlaceORM.getInstance(context).getPlaceBy("cafe")
                .filter(places -> !places.isEmpty())
                .toObservable()
                .map(ConvertUtils::returnPlaceWithCategory)
                .doOnNext(places -> Log.d("from cache", String.valueOf(places.size())));
    }
}
