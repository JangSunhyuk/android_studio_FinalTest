package com.example.dcu_image_viwer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> jpgFiles;
    MyGalleryAdapter adapter;
    String jpgPath = Environment.getExternalStorageDirectory().getPath() + "/";

    private static final int REQUEST_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, REQUEST_PERMISSION_CODE);
            } else {
                loadImages();
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
            } else {
                loadImages();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadImages();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadImages() {
        jpgFiles = new ArrayList<>();
        File[] files = new File(jpgPath).listFiles();
        String filename, ext;

        if (files != null) {
            for (File file : files) {
                filename = file.getName();
                ext = filename.substring(filename.length() - 3);
                if (ext.equals("jpg")) {
                    jpgFiles.add(jpgPath + filename);
                }
            }
        } else {
            Log.e("MainActivity", "No files found in the directory");
            Toast.makeText(this, "No images found", Toast.LENGTH_SHORT).show();
        }

        if (jpgFiles.isEmpty()) {
            Toast.makeText(this, "No JPG images found", Toast.LENGTH_SHORT).show();
        }

        GridView gridView = findViewById(R.id.gridview);
        adapter = new MyGalleryAdapter(this, jpgFiles);
        gridView.setAdapter(adapter);
    }
}