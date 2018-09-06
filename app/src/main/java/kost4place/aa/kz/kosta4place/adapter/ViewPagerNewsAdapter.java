package kost4place.aa.kz.kosta4place.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import kost4place.aa.kz.kosta4place.R;

public class ViewPagerNewsAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;
    private Integer[] myImageList = {R.drawable.tux, R.drawable.tux,
            R.drawable.tux};

    public ViewPagerNewsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return myImageList.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.special_slide_layout, null);
        ImageView imgView = view.findViewById(R.id.imageView);
        imgView.setImageResource(myImageList[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position == 0) {
                    Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
                }else if (position == 1) {
                    Toast.makeText(context, "2", Toast.LENGTH_SHORT).show();
                }else if (position == 2) {
                    Toast.makeText(context, "3", Toast.LENGTH_SHORT).show();
                }


            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
