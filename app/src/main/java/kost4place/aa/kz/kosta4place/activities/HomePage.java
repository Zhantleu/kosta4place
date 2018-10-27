package kost4place.aa.kz.kosta4place.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import kost4place.aa.kz.kosta4place.R;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBtnRestaurant;
    private ImageView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mBtnRestaurant.setOnClickListener(this);
        test.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_restaurant:
                Intent intent = new Intent(this, RestaurantCategory.class);
                startActivity(intent);
                break;
            case R.id.testbut:
                Intent intent1 = new Intent(this, ExampleActivity.class);
                startActivity(intent1);
                break;
        }
    }


    private void init() {
        mBtnRestaurant = findViewById(R.id.button_restaurant);
        test = findViewById(R.id.testbut);
    }
}