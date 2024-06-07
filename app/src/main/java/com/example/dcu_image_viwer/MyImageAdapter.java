package com.example.dcu_image_viwer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyImageAdapter extends RecyclerView.Adapter<MyImageAdapter.MyImageViewHolder> {

    private final ArrayList<String> allImages;
    private final OnMyImageClickListener listener;

    public interface OnMyImageClickListener {
        void onMyImageClick(int position);
    }

    public MyImageAdapter(ArrayList<String> allImages, OnMyImageClickListener listener) {
        this.allImages = allImages;
        this.listener = listener;
    }

    public static class MyImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public MyImageViewHolder(View view, final OnMyImageClickListener listener) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            view.setOnClickListener(v -> listener.onMyImageClick(getAdapterPosition()));
        }
    }

    @NonNull
    @Override
    public MyImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_myimage_image, parent, false);
        return new MyImageViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyImageViewHolder holder, int position) {
        Glide.with(holder.imageView.getContext()).load(allImages.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return allImages.size();
    }
}