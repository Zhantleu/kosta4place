package kost4place.aa.kz.kosta4place.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

import kost4place.aa.kz.kosta4place.R;
import kost4place.aa.kz.kosta4place.adapter.ViewPagerNewsAdapter;

public class HomePage extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private static ViewPager mPager;
    private ViewPagerNewsAdapter viewPagerNewsAdapter;
    private LinearLayout sliderDots;
    private int dotsCount;
    private ImageView[] dots;

    private Button mBtnRestaurant;
    private Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        mBtnRestaurant.setOnClickListener(this);
        test.setOnClickListener(this);

        viewPagerNewsAdapter = new ViewPagerNewsAdapter(this);
        mPager.setAdapter(viewPagerNewsAdapter);

        dotsCount = viewPagerNewsAdapter.getCount();
        dots = new ImageView[dotsCount];

        //Method which does paging sliders dots
        setSliderDots();

        mPager.addOnPageChangeListener(this);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTaskViewPager(), 2000, 4000);
    }

    private void setSliderDots() {
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.noactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDots.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
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

    public class TimerTaskViewPager extends TimerTask {
        @Override
        public void run() {
            HomePage.this.runOnUiThread(() -> {
                if (mPager.getCurrentItem() == 0) {
                    mPager.setCurrentItem(1);
                } else if (mPager.getCurrentItem() == 1) {
                    mPager.setCurrentItem(2);
                } else if (mPager.getCurrentItem() == 2) {
                    mPager.setCurrentItem(0);
                }
            });
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dots.length; i++) {
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.noactive_dot));
        }

        dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void init() {
        sliderDots = findViewById(R.id.sliderDots);
        mPager = findViewById(R.id.viewPager);
        mBtnRestaurant = findViewById(R.id.button_restaurant);
        test = findViewById(R.id.testbut);
    }
}