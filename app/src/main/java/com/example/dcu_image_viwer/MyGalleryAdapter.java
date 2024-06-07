package com.example.dcu_image_viwer;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyGalleryAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> jpgFiles;

    public MyGalleryAdapter(Context context, ArrayList<String> jpgFiles) {
        this.context = context;
        this.jpgFiles = jpgFiles;
    }

    @Override
    public int getCount() {
        return jpgFiles.size();
    }

    @Override
    public Object getItem(int position) {
        return jpgFiles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        Glide.with(context)
                .load(jpgFiles.get(position))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(imageView);

        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("selectedImage", jpgFiles.get(position));
            intent.putStringArrayListExtra("allImages", jpgFiles);
            context.startActivity(intent);
        });

        return imageView;
    }
}