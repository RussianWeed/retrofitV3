package com.devking.retrofitv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView image_view = findViewById(R.id.image_view);

        Intent intent = getIntent();
        String url = intent.getStringExtra("Image_url");
        Picasso.get().load(url).into(image_view);
    }
}