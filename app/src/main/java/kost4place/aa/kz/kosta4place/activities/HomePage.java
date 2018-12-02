package kost4place.aa.kz.kosta4place.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    private CardView mCrdrestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mCrdrestaurant.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_restaurant:
                Intent intent = new Intent(this, RestaurantCategory.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    private void init() {
        mCrdrestaurant = findViewById(R.id.card_restaurant);
    }
}