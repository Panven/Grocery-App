package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.example.myapplication.R;
import com.example.myapplication.models.NavCategoryDetailedModel;

import java.util.List;

public class NavCategoryDetailedAdapter extends RecyclerView.Adapter<NavCategoryDetailedAdapter.ViewHolder>  {

    Context context;
    List<NavCategoryDetailedModel> navCategoryDetailedModelList;

    public NavCategoryDetailedAdapter(Context context, List<NavCategoryDetailedModel> navCategoryDetailedModelList) {
        this.context = context;
        this.navCategoryDetailedModelList = navCategoryDetailedModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_category_detailed_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(navCategoryDetailedModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(navCategoryDetailedModelList.get(position).getName());
        holder.price.setText(navCategoryDetailedModelList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return navCategoryDetailedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.det_cat_nav_img);
            name = itemView.findViewById(R.id.det_nav_cat_name);
            price = itemView.findViewById(R.id.price);
        }
    }
}
