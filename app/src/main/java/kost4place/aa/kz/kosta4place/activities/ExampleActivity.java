package kost4place.aa.kz.kosta4place.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kost4place.aa.kz.kosta4place.R;
import kost4place.aa.kz.kosta4place.repository.PlaceRepository;
import kost4place.aa.kz.kosta4place.utils.SocketConnection;

public class ExampleActivity extends AppCompatActivity {

    EditText info;
    EditText titlePlace;
    EditText location;
    RecyclerView recyclerView;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main1);
//        info = findViewById(R.id.et_firstname);
//        titlePlace = findViewById(R.id.et_lastname);
//        location = findViewById(R.id.et_location);
//        recyclerView = findViewById(R.id.recycler);
//
//        PlaceRepository placeRepository = new PlaceRepository();
//        placeRepository.getPlaces(getApplicationContext())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe();
//    }

    TextView response;
    EditText editTextAddress, editTextPort;
    Button buttonConnect, buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socket_example);

        editTextAddress = findViewById(R.id.addressEditText);
        editTextPort = findViewById(R.id.portEditText);
        buttonConnect = findViewById(R.id.connectButton);
        buttonClear = findViewById(R.id.clearButton);
        response = findViewById(R.id.responseTextView);

        buttonConnect.setOnClickListener(arg0 -> {
            SocketConnection myClient = new SocketConnection(editTextAddress.getText()
                    .toString(), Integer.parseInt(editTextPort
                    .getText().toString()), response);
            myClient.execute();
        });

        buttonClear.setOnClickListener(v -> response.setText(""));
    }
}