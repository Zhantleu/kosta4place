package kost4place.aa.kz.kosta4place.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import kost4place.aa.kz.kosta4place.activities.R;
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
//        holder.txt_location.setText(String.valueOf(postList.get(position).getLocation()));
        holder.txt_title.setText(String.valueOf(postList.get(position).getPlaceTitle()));

        Glide.with(context)
                .load("http://3.120.172.55:8080/api-1.0-SNAPSHOT/image/" + postList.get(position).getUrlLocation())
                .asBitmap()
                .override(300,200)
                .into(holder.placeImage);

//        holder.placeImage.setImageBitmap(String.valueOf(postList.get(position).getPlaceTitle()));

//        holder.txt_content.setText(new StringBuilder(postList.get(position).getInfo().substring(0, 20)).append("...").toString());
//        holder.txt_content.setText(new StringBuilder(postList.get(position).getInfo()));
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
