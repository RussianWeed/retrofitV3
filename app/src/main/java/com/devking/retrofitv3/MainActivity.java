package com.devking.retrofitv3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public String url = "https://jsonplaceholder.typicode.com/";
    public ArrayList<Model> dataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.tv);

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
                    for(int i=0;i<dataset.size();i++) {
                        tv.append(dataset.get(i).getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

            }
        });
    }
}