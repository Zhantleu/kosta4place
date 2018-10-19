package kost4place.aa.kz.kosta4place.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kost4place.aa.kz.kosta4place.R;
import kost4place.aa.kz.kosta4place.model.Place;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {
    Context context;
    List<Place> postList;

    public PostAdapter(Context context, List<Place> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_layout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.txt_location.setText(String.valueOf(postList.get(position).getLocation()));
        holder.txt_title.setText(String.valueOf(postList.get(position).getPlaceTitle()));
//        holder.txt_content.setText(new StringBuilder(postList.get(position).getInfo().substring(0, 20)).append("...").toString());
        holder.txt_content.setText(new StringBuilder(postList.get(position).getInfo()));
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
