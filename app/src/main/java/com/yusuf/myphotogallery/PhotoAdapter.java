package com.yusuf.myphotogallery;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yusuf.myphotogallery.databinding.RecyclerRowBinding;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {

    List<PhotoDao> photos= new ArrayList<>();

    public PhotoAdapter(List<PhotoDao> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PhotoHolder(recyclerRowBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        holder.binding.recyclerViewText.setText(photos.get(position).title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(),PhotoActivity.class);
                intent.putExtra("info","old");
                intent.putExtra("photoId",photos.get(position).id);
                holder.itemView.getContext().startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class PhotoHolder extends RecyclerView.ViewHolder{

        RecyclerRowBinding binding;
        public PhotoHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }


}
