package kost4place.aa.kz.kosta4place.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import kost4place.aa.kz.kosta4place.R;
import kost4place.aa.kz.kosta4place.adapter.PostAdapter;
import kost4place.aa.kz.kosta4place.remote.api.KostaApi;
import kost4place.aa.kz.kosta4place.local.data.PlaceORM;
import kost4place.aa.kz.kosta4place.model.Place;
import kost4place.aa.kz.kosta4place.repository.PlaceRepository;

public class RestaurantCategory extends AppCompatActivity {

    private KostaApi kostaApi;
    private PlaceORM placeORM;
    private RecyclerView recyclerView_posts;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_category);

        //View
        recyclerView_posts = findViewById(R.id.recycler_posts);
        recyclerView_posts.setHasFixedSize(true);
        recyclerView_posts.setLayoutManager(new LinearLayoutManager((this)));

//        fetchData();

        PlaceRepository placeRepository = new PlaceRepository();
        placeRepository.getPlaces(getApplicationContext())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(place -> fetchData())
                .subscribeWith(new DisposableObserver<List<Place>>() {
                    @Override
                    public void onNext(List<Place> places) {
                        displayData(places);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void fetchData() {
        compositeDisposable.add(kostaApi.getPlace()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayData));
    }

    private void displayData(List<Place> places) {
        PostAdapter adapter = new PostAdapter(this, places);
        recyclerView_posts.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
