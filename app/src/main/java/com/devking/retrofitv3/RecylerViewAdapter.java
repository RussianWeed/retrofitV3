package com.devking.retrofitv3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.View_Hoder> {

    ArrayList<Model> data;

    public RecylerViewAdapter(ArrayList<Model> data) {
        this.data =data;
    }

    @NonNull
    @Override
    public View_Hoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
        return new View_Hoder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull View_Hoder holder, int position) {

        Model currentitem = data.get(position);
        Picasso.get().load(currentitem.getThumbnailUrl().toString()).into(holder.imageView);

    }



    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public class View_Hoder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public View_Hoder(@NonNull View itemView) {
            super(itemView);
            // Corrected: Assign the itemView's ImageView to the class variable
            imageView = itemView.findViewById(R.id.image_view);

             }
        }

    public void updateData(ArrayList<Model> newData) {
        data = newData;
        notifyDataSetChanged();
    }
}

