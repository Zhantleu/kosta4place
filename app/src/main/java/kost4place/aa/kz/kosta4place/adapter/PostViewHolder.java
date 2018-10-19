package kost4place.aa.kz.kosta4place.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kost4place.aa.kz.kosta4place.R;
import kost4place.aa.kz.kosta4place.model.Place;

public class PostViewHolder extends RecyclerView.ViewHolder {
    TextView txt_title, txt_content, txt_location;

    public PostViewHolder(View itemView) {
        super(itemView);

        txt_content = itemView.findViewById(R.id.info);
        txt_title = itemView.findViewById(R.id.placeTitle);
        txt_location = itemView.findViewById(R.id.location);
    }
}
