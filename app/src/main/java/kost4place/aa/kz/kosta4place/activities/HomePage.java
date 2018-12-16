package kost4place.aa.kz.kosta4place.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    private CardView mCrdRestaurant;
    private CardView mCrdCafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mCrdRestaurant.setOnClickListener(this);
        mCrdCafe.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.card_restaurant:
                intent = new Intent(this, CategoryPage.class);
                intent.putExtra("category", "restaurant");
                startActivity(intent);
                break;
            case R.id.card_cafe:
                intent = new Intent(this, CategoryPage.class);
                intent.putExtra("category", "cafe");
                startActivity(intent);
            default:
                break;
        }
    }


    private void init() {
        mCrdRestaurant = findViewById(R.id.card_restaurant);
        mCrdCafe = findViewById(R.id.card_cafe);
    }
}