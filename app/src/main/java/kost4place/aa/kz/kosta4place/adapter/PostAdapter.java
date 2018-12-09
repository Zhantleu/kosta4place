package kost4place.aa.kz.kosta4place.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import kost4place.aa.kz.kosta4place.activities.AboutPlace;
import kost4place.aa.kz.kosta4place.activities.R;
import kost4place.aa.kz.kosta4place.model.Place;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {
    private static final String TAG = "RecycleViewAdapter";

    private Context context;
    private List<Place> postList;

    public PostAdapter(Context context, List<Place> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_layout, parent, false);

        view.setAnimation(AnimationUtils.loadAnimation(context,
                R.anim.item_animation_fall_down));
        view.setVisibility(View.VISIBLE);

        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        Glide.with(context)
                .load("http://3.120.172.55:8080/api-1.0-SNAPSHOT/image/" + postList.get(position).getUrlLocation())
                .asBitmap()
                .override(200, 200)
                .into(holder.getPlaceImage());

        holder.getTxtTitle().setText(postList.get(position).getPlaceTitle());

        holder.getParentLayout().setOnClickListener(view -> {
            Log.d(TAG, "onClick: clicked on: " + postList.get(position));

            Toast.makeText(context, postList.get(position).getPlaceTitle(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, AboutPlace.class);
            intent.putExtra("image_url", postList.get(position).getUrlLocation());

            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    private String saveImage(Bitmap image) {
        String savedImagePath = null;

        String imageFileName = "JPEG_" + "FILE_NAME" + ".jpg";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + "/YOUR_FOLDER_NAME");
        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }
        if (success) {
            File imageFile = new File(storageDir, imageFileName);
            savedImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fOut = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Add the image to the system gallery
            Toast.makeText(context, "IMAGE SAVED", Toast.LENGTH_LONG).show();
        }
        return savedImagePath;
    }
}
