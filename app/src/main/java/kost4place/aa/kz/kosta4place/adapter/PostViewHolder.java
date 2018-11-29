package kost4place.aa.kz.kosta4place.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kost4place.aa.kz.kosta4place.activities.R;


public class PostViewHolder extends RecyclerView.ViewHolder {
    TextView txt_title;
    ImageView placeImage;

    public PostViewHolder(View itemView) {
        super(itemView);

        placeImage = itemView.findViewById(R.id.placeImage);
        txt_title = itemView.findViewById(R.id.placeTitle);
    }
}
