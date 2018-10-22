package kost4place.aa.kz.kosta4place.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kost4place.aa.kz.kosta4place.R;
import kost4place.aa.kz.kosta4place.repository.PlaceRepository;

public class ExampleActivity extends AppCompatActivity {

    EditText info;
    EditText titlePlace;
    EditText location;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        info = findViewById(R.id.et_firstname);
        titlePlace = findViewById(R.id.et_lastname);
        location = findViewById(R.id.et_location);
        recyclerView = findViewById(R.id.recycler);

        PlaceRepository placeRepository = new PlaceRepository();
        placeRepository.getPlaces(getApplicationContext())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}