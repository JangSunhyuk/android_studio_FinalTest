package com.example.dcu_image_viwer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.content.Intent;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private DetailAdapter detailAdapter;
    private RecyclerView horizontalRecyclerView;
    private MyImageAdapter myImageAdapter;
    private ArrayList<String> allImages;
    private String selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        viewPager = findViewById(R.id.viewPager);
        horizontalRecyclerView = findViewById(R.id.horizontalRecyclerView);

        Intent intent = getIntent();
        allImages = intent.getStringArrayListExtra("allImages");
        selectedImage = intent.getStringExtra("selectedImage");

        detailAdapter = new DetailAdapter(allImages);
        viewPager.setAdapter(detailAdapter);

        myImageAdapter = new MyImageAdapter(allImages, this::onMyImageClick);
        horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        horizontalRecyclerView.setAdapter(myImageAdapter);

        int initialPosition = allImages.indexOf(selectedImage);
        viewPager.setCurrentItem(initialPosition, false);
    }

    private void onMyImageClick(int position) {
        viewPager.setCurrentItem(position, true);
    }
}