package com.itpvt.mohagni.Adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.itpvt.mohagni.Activities.My_Cart;
import com.itpvt.mohagni.Config;
import com.itpvt.mohagni.PojoClass.Cart_item_pojo;
import com.itpvt.mohagni.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2/26/2018.
 */

public class Recyler_Cart_Item extends RecyclerView.Adapter<Recyler_Cart_Item.MyViewHolder>{
    ArrayList<Cart_item_pojo> arrayList = new ArrayList<>();
    Activity activity;
    String Cartid;
    String itemid;
    Float price;
    String Dis;

    public Recyler_Cart_Item(ArrayList<Cart_item_pojo> arrayList, Context context, String Cartid){
        this.arrayList=arrayList;
        this.Cartid=Cartid;
        this.activity=(Activity)context;

    }

    public Recyler_Cart_Item(String product_id, String name, String replace, String item_qty, String total, String item_id, String price, String discount_price) {
    }


    @Override
    public Recyler_Cart_Item.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent,false);
        return new Recyler_Cart_Item.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Recyler_Cart_Item.MyViewHolder holder, final int position) {
        Glide.with(activity).load(arrayList.get(position).getImg_url()).into(holder.imageView);
        holder.p_name.setText(arrayList.get(position).getName());
        holder.p_qty.setText(arrayList.get(position).getItem_qty());
        Dis = arrayList.get(position).getDis();
        if (Dis.equals("null")){
            holder.total_price.setText(arrayList.get(position).getTotal());
            holder.item_price.setText(arrayList.get(position).getPrice());
    }else {
            price=Float.valueOf(arrayList.get(position).getDis())*Float.valueOf(arrayList.get(position).getItem_qty());
            holder.total_price.setText(String.valueOf(price));
            holder.item_price.setText(arrayList.get(position).getDis());
        }
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemid=arrayList.get(position).getItem_id();
                arrayList.remove(position);
                notifyDataSetChanged();
                removeitem();
            }
        });
    }
    private void removeitem(){
        final ProgressDialog loading;
        loading = ProgressDialog.show(activity,"loading","pleasewait",false, false);
        StringRequest request= new StringRequest(Request.Method.POST, Config.URL_REMOVE_ITEM_CART, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                if (response.equals("removed")){
                    Toast.makeText(activity,"item removed",Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(activity, My_Cart.class);
                    activity.startActivity(intent);
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(activity.getApplicationContext(),"Network Connection Error",Toast.LENGTH_SHORT).show();

            }
        }

        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("quoteId", Cartid);
                params.put("item_id", itemid);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue= Volley.newRequestQueue(activity.getApplication());
        requestQueue.add(request);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView p_name,p_qty, item_price, total_price;
        Button remove;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            p_name=(TextView)itemView.findViewById(R.id.p_name);
            item_price=(TextView)itemView.findViewById(R.id.item_price);
            p_qty=(TextView)itemView.findViewById(R.id.p_qty);
            total_price=(TextView)itemView.findViewById(R.id.total_price);
            imageView=(ImageView)itemView.findViewById(R.id.thumbnail);
            remove=(Button)itemView.findViewById(R.id.remove);
        }
    }
}
