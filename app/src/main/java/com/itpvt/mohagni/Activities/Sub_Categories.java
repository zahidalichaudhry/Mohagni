package com.itpvt.mohagni.Activities;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
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
import com.itpvt.mohagni.Adapters.Recycler_Adapter_Sub_catagories;
import com.itpvt.mohagni.Config;
import com.itpvt.mohagni.PojoClass.Sub_Categories_pojo;
import com.itpvt.mohagni.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sub_Categories extends AppCompatActivity {

    ArrayList<Sub_Categories_pojo> arrayList=new ArrayList<>();
    RecyclerView recyclerView;
    Recycler_Adapter_Sub_catagories adapter;
    RecyclerView.LayoutManager layoutManager;
    String WEB_URL;
    private ProgressDialog loading;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sub__categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        FloatingActionButton bag=(FloatingActionButton)findViewById(R.id.bag);
        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Sub_Categories.this,My_Cart.class);
                startActivity(intent);
            }
        });
        FloatingActionButton whatsapp=(FloatingActionButton)findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri=Uri.parse("smsto:"+"+923111101102");
//                Intent i =newa Intent(Intent.ACTION_SENDTO,uri);
//                i.setPackage("com.whatsapp");
//                startActivity(i);

                String smsNumber = "92316143343";

                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");//phone number without "+" prefix
                startActivity(sendIntent);
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.model_recyclerView);
        layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        Intent intent = getIntent();
         id = intent.getStringExtra("id");
        WEB_URL=intent.getStringExtra("weburl");
        GetSubCategories();
    }

    private void GetSubCategories()
    {
        loading = ProgressDialog.show(Sub_Categories.this,"Loading...","Please wait...",false,false);
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_Sub_Categories, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONArray data= null;
                try {
                    data = new JSONArray(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (data==null)
                {
                    Intent intent=new Intent(Sub_Categories.this,All_Products.class);
//                    Intent intent=newa Intent(Sub_Categories.this,Web_View.class);
//                    intent.putExtra("weburl",WEB_URL);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
                else {
                    try {


                            for (int j =0 ;j< data.length(); j++){

                                JSONObject cat = data.getJSONObject(j);
                                arrayList.add(new Sub_Categories_pojo(cat.getString("category_id"),cat.getString("name")));

                            }
                        adapter=new Recycler_Adapter_Sub_catagories(arrayList,Sub_Categories.this,WEB_URL);
                        recyclerView.setAdapter(adapter);
                        loading.dismiss();

                    }

                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                //  tvSurah.setText("Response is: "+ response.substring(0,500));
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                //  Log.e("Error",error.printStackTrace());
                Toast.makeText(Sub_Categories.this.getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

              params.put("categories", id);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(Sub_Categories.this.getApplicationContext());
        requestQueue.add(request);
        FloatingActionButton facebook=(FloatingActionButton)findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri = Uri.parse("fb-messenger://user/");
//                uri = ContentUris.withAppendedId(uri, Long.parseLong("rdtex2016"));
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

//                Uri uri = Uri.parse("fb-messenger://user/");
//
//                uri = ContentUris.withAppendedId(uri,Long.valueOf("rdtex2016"));
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
                Intent facebookIntent = openFacebook(Sub_Categories.this);
                startActivity(facebookIntent);
            }
        });
    }
    public static Intent openFacebook(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://page/1725972814353758"));
        } catch (Exception e){

            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/rdtex2016/"));
        }


    }
}
