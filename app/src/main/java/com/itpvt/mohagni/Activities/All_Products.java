package com.itpvt.mohagni.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itpvt.mohagni.Adapters.All_Products_Adapter;
import com.itpvt.mohagni.Config;
import com.itpvt.mohagni.PojoClass.Products_pojo;
import com.itpvt.mohagni.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class All_Products extends AppCompatActivity {
    ArrayList<Products_pojo> arrayList=new ArrayList<>();
    RecyclerView recyclerView;
    All_Products_Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private ProgressDialog loading;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_all__products);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(All_Products.this,Home_Categories.class);
                startActivity(intent);
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.model_recyclerView);
        layoutManager=new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        ImageView whatsapp=(ImageView) findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri  =Uri.parse("smsto:"+"+923161433343");
                Intent intent =new Intent(Intent.ACTION_SENDTO,uri);
                intent.setPackage("com.whatsapp");
                startActivity(intent);
            }
        });
        ImageView bag=(ImageView)findViewById(R.id.bag);
        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(All_Products.this,My_Cart.class);
                startActivity(intent);
            }
        });
        Intent intent =getIntent();
        id = intent.getStringExtra("id");
        GetAllProducts();





    }
    /////getting all product by vollay/////
    private void GetAllProducts()
    {
        loading = ProgressDialog.show(All_Products.this,"Loading...","Please wait...",false,false);
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_ALL_PRODUCTS, new com.android.volley.Response.Listener<String>()
        {

             @Override
            public void onResponse(String response) {
                try
                {
                    loading.dismiss();
                    JSONObject abc = new JSONObject(response);
                    int j=abc.length();
                    for(int i=j; i>= 1; i--) {
                        String num = String.valueOf(i);
                        JSONObject data = abc.getJSONObject(num);
                        arrayList.add(new Products_pojo(data.getString("product_id"), data.getString("pro_name")
                                , data.getString("img_url").replace("localhost", Config.ip),data.getString("sku")
                                ,data.getString("product_quantity"),data.getString("price").replace(".0000","Rs")));

                    }
                adapter=new All_Products_Adapter(arrayList,All_Products.this);
                recyclerView.setAdapter(adapter);
                }
                catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(All_Products.this,"Nothing is Available For Time Being",Toast.LENGTH_LONG).show();
                loading.dismiss();
                onBackPressed();
            }

            }
    }, new com.android.volley.Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            loading.dismiss();
            //  Log.e("Error",error.printStackTrace());
            Toast.makeText(All_Products.this.getApplicationContext(), "Network Error" , Toast.LENGTH_SHORT).show();
            onBackPressed();

        }
    }

        ){
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<String, String>();

            params.put("category_id", id);
            return params;
        }
    };    request.setRetryPolicy(new DefaultRetryPolicy(
            0,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(All_Products.this.getApplicationContext());
        requestQueue.add(request);

}

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(All_Products.this,Home_Categories.class);
        startActivity(intent);
    }
}


