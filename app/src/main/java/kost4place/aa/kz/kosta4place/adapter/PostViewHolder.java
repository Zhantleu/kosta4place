package kost4place.aa.kz.kosta4place.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kost4place.aa.kz.kosta4place.activities.R;


public class PostViewHolder extends RecyclerView.ViewHolder {
    private TextView txtTitle;
    private ImageView placeImage;
    private ConstraintLayout parentLayout;

    public PostViewHolder(View itemView) {
        super(itemView);

        placeImage = itemView.findViewById(R.id.placeImage);
        txtTitle = itemView.findViewById(R.id.placeTitle);
        parentLayout = itemView.findViewById(R.id.parent_layout);
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public ImageView getPlaceImage() {
        return placeImage;
    }

    public ConstraintLayout getParentLayout() {
        return parentLayout;
    }
}
