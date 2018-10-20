package kost4place.aa.kz.kosta4place.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import kost4place.aa.kz.kosta4place.R;
import kost4place.aa.kz.kosta4place.cache.LocalCacheManager;
import kost4place.aa.kz.kosta4place.dao.DatabaseCallback;
import kost4place.aa.kz.kosta4place.model.Place;

public class ExampleActivity extends AppCompatActivity implements DatabaseCallback {

    private static final String DB_NAME = "database-name";

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

    }

    public void add(View view) {
        if (!TextUtils.isEmpty(info.getText().toString()) && !TextUtils.isEmpty(titlePlace.getText().toString()) && !TextUtils.isEmpty(location.getText().toString())) {
            LocalCacheManager.getInstance(this).addUser(this, titlePlace.getText().toString(), info.getText().toString(), location.getText().toString());
            info.setText("");
            titlePlace.setText("");
            location.setText("");
        }
    }

    public void fetch(View view) {
        LocalCacheManager.getInstance(this).getPlaces(this);
    }

    @Override
    public void onPlacesLoaded(List<Place> placeList) {
        for (Place place : placeList) {
            Log.d("from db", place.toString());
        }
    }

    @Override
    public void onPlaceAdded() {
        Log.d("room", "onUserAdded");
    }

    @Override
    public void onDataNotAvailable() {
        Log.d("room", "onDataNotAvailable");
    }

}