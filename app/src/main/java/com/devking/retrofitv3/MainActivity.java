package com.devking.retrofitv3;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements  ClickListener{
    public String url = "https://jsonplaceholder.typicode.com/";
    public ArrayList<Model> dataset;
    ClickListener clickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecylerViewAdapter adapter = new RecylerViewAdapter(dataset,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        Retrofit retrofit =new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();

        ApiCall call = retrofit.create(ApiCall.class);

        Call<List<Model>> call_response = call.getmodel();

        call_response.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if(response.isSuccessful() && response.body() != null){
                    dataset= (ArrayList<Model>) response.body();
                    adapter.updateData(dataset);
                }
            }


            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Log.d(TAG, "onFailure: error had occurred");
            }
        });
    }


    @Override
    public void onItemClick(int position) {
        if (position != RecyclerView.NO_POSITION){

            Intent intent = new Intent(this,MainActivity2.class);
            Model curentitem = dataset.get(position);
            intent.putExtra("Image_url", curentitem.getUrl());
            startActivity(intent);

        }
    }
}