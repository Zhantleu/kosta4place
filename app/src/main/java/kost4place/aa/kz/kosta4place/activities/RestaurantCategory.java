package kost4place.aa.kz.kosta4place.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import kost4place.aa.kz.kosta4place.adapter.PostAdapter;
import kost4place.aa.kz.kosta4place.adapter.RecyclerItemClickListener;
import kost4place.aa.kz.kosta4place.model.Place;
import kost4place.aa.kz.kosta4place.repository.PlaceRepository;

public class RestaurantCategory extends AppCompatActivity {

    private RecyclerView recyclerView_posts;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private PlaceRepository placeRepository = new PlaceRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_category);

        //View
        recyclerView_posts = findViewById(R.id.recycler_posts);
        recyclerView_posts.setHasFixedSize(true);
        recyclerView_posts.setLayoutManager(new LinearLayoutManager((this)));

        recyclerView_posts.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView_posts , new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
//                        Toast.makeText(this,position,Toast.LENGTH_SHORT).show();
                        System.out.println(position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }) {

                }
        );

        fetchData();
    }

    //Error when the activity try to get a information without API
    private void fetchData() {
        compositeDisposable.add(placeRepository.getPlaces(getApplicationContext())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::displayData)
                .subscribe());
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
