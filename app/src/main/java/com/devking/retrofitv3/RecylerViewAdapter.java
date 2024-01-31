package com.devking.retrofitv3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.View_Hoder> {

    ArrayList<Model> data;
    ClickListener clickListener;

    public RecylerViewAdapter(ArrayList<Model> data,ClickListener clickListener) {
        this.data =data;
        this.clickListener = clickListener;
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
        holder.id.setText(currentitem.getId());
        holder.title.setText(currentitem.getTitle());
        Picasso.get().load(currentitem.getThumbnailUrl().toString()).into(holder.thumbnail);

    }



    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    public class View_Hoder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView id,title;


        public View_Hoder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnail);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        clickListener.onItemClick(getAdapterPosition());

                }
            });

             }
        }

    public void updateData(ArrayList<Model> newData) {
        data = newData;
        notifyDataSetChanged();
    }
}

