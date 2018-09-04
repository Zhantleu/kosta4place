package kost4place.aa.kz.kosta4place.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import kost4place.aa.kz.kosta4place.R;
import kost4place.aa.kz.kosta4place.adapter.ViewPagerNewsAdapter;

public class MainActivity extends AppCompatActivity {

    private static ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.viewPager);

        ViewPagerNewsAdapter viewPagerNewsAdapter = new ViewPagerNewsAdapter(this);

        mPager.setAdapter(viewPagerNewsAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTaskViewPager(), 2000, 4000);
    }

    public class TimerTaskViewPager extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < mPager.getScrollBarSize(); i++) {

                        if (i != mPager.getScrollBarSize()) {
                            mPager.setCurrentItem(++i);
                        } else {
                            mPager.setCurrentItem(0);
                        }
                    }
//                    for (int i = 0; i < mPager.getScrollBarSize(); i++) {
//                        if (i == mPager.getScrollBarSize()){
//                            i = 0;
//                        } else {
//                            mPager.setCurrentItem(++i);
//                        }
//                    }
                }
            });
        }
    }
}
