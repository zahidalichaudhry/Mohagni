package com.itpvt.mohagni.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itpvt.mohagni.Activities.Product_Details;
import com.itpvt.mohagni.PojoClass.Products_pojo;
import com.itpvt.mohagni.R;


import java.util.ArrayList;

/**
 * Created by CH-Hamza on 2/21/2018.
 */




public class All_Products_Adapter extends RecyclerView.Adapter<All_Products_Adapter.MyViewHolder> {

    ArrayList<Products_pojo> arrayList = new ArrayList<>();
    Activity activity;

    public All_Products_Adapter(ArrayList<Products_pojo> arrayList, Context context) {
        this.arrayList = arrayList;
        activity = (Activity) context;
    }

    @Override
    public All_Products_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_product_items, parent, false);
        return new All_Products_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(All_Products_Adapter.MyViewHolder holder, final int position) {

        holder.name.setText(arrayList.get(position).getProduct_name());
        Glide.with(activity).load(arrayList.get(position).getProduct_uri()).into(holder.imageView);
        holder.price.setText(arrayList.get(position).getPrice());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Product_Details.class);
                intent.putExtra("product_id",arrayList.get(position).getProduct_id());
                intent.putExtra("SKU",arrayList.get(position).getSKU());
                activity.startActivity(intent);
            }
        });

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Product_Details.class);
                intent.putExtra("product_id",arrayList.get(position).getProduct_id());
                intent.putExtra("SKU",arrayList.get(position).getSKU());
                activity.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,price;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.products);
            imageView = (ImageView) itemView.findViewById(R.id.thumbnail);
            price = (TextView) itemView.findViewById(R.id.tvprice);
        }

    }
}


