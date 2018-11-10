package kost4place.aa.kz.kosta4place.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import kost4place.aa.kz.kosta4place.R;
import kost4place.aa.kz.kosta4place.utils.SocketConnection;

public class ExampleActivity extends AppCompatActivity {

    //todo Сделать вывод картинки Base64
    ImageView response;
    Button buttonConnect, buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socket_example);

        buttonConnect = findViewById(R.id.connectButton);
        buttonClear = findViewById(R.id.clearButton);
        response = findViewById(R.id.responseTextView);

        SocketConnection myClient = new SocketConnection(response,this);
        myClient.execute();

        buttonConnect.setOnClickListener(arg0 -> {

        });

//        buttonClear.setOnClickListener(v -> response.clearColorFilter());
    }
}