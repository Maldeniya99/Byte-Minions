package com.example.travelbee.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travelbee.MemoryPage;
import com.example.travelbee.R;
import com.example.travelbee.models.Memories;

import java.util.List;

public class Adapter extends RecyclerView.Adapter{
    private List<Memories> data;

    public Adapter(List<Memories> data) {
        this.data = data;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolderClass viewHolderClass =  new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass)holder;
        Memories memories = data.get(position);
        viewHolderClass.textTitle.setText(memories.getTitle());
        Glide.with(viewHolderClass.dbImage.getContext()).load(memories.getImageUrl()).into(viewHolderClass.dbImage);

//        viewHolderClass.dbImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context)
//            }
//        });

        viewHolderClass.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MemoryPage.class);
                i.putExtra("title", memories.getTitle());
                i.putExtra("location", memories.getLocation());
                i.putExtra("date", memories.getDate());
                i.putExtra("description", memories.getDescription());
                i.putExtra("image", memories.getImageUrl());
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder{

        TextView textTitle, textLocation, textDate, textDescription;
        ImageView dbImage;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.text_title_new);
            dbImage = itemView.findViewById(R.id.iv_uploaded_new);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(v.getContext(), MemoryPage.class);
//                    i.putExtra("title",data.get(getAdapterPosition()));
//                    v.getContext().startActivity(i);
//                }
//            });
        }
    }

}
