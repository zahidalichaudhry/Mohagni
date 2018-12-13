package com.itpvt.mohagni.Fragements;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.itpvt.mohagni.Activities.All_Products;
import com.itpvt.mohagni.Adapters.Recycler_Adapter_All_Products_new;
import com.itpvt.mohagni.Config;
import com.itpvt.mohagni.PojoClass.All_product_pojo;
import com.itpvt.mohagni.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements BaseSliderView.OnSliderClickListener {
    SliderLayout sliderLayout ;
  //  static String path0;
    LinearLayout footer;
    String id;
    ArrayList<All_product_pojo> arrayList=new ArrayList<>();
    ArrayList<All_product_pojo> arrayList2=new ArrayList<>();
    RecyclerView recyclerView,recyclerView2;
    String newArr_cat_id="88";
    String sale_cat_id="4";

    Recycler_Adapter_All_Products_new adapter;
    RecyclerView.LayoutManager layoutManager;
    private ProgressDialog loading;

    String menimage,womenimage,saleimage,bajiImage;
   // static String path1,path2;
    ImageView men,women,chifon,arena,mohagni;
    TextView new_a,new_a2,new_tx,sale_tx;
    HashMap<String, Integer> HashMapForURL ;
    int[] images={  R.drawable.ba, R.drawable.ban, R.drawable.bann};
    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home2, container, false);

        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        //path0 = Config.BANNER1;
       // path1= Config.BANNER2;
      //  path2=Config.BANNER3;
        saleimage=Config.HOMW_SALE;
        bajiImage  = Config.HOME_FOOTWARE;
//        sale=(ImageView)view.findViewById(R.id.img1);
//        New=(ImageView)view.findViewById(R.id.img2);
        chifon=(ImageView)view.findViewById(R.id.chifon);
        arena=(ImageView)view.findViewById(R.id.arena);
        mohagni=(ImageView)view.findViewById(R.id.mohagni);
        recyclerView=(RecyclerView)view.findViewById(R.id.model_recyclerView);
        recyclerView2=(RecyclerView)view.findViewById(R.id.model_recyclerView2);
        new_a=(TextView)view.findViewById(R.id.new_a);
        new_a2=(TextView)view.findViewById(R.id.new_a2);
        new_tx=(TextView)view.findViewById(R.id.new_tx);
        sale_tx=(TextView)view.findViewById(R.id.sale_tx);
        recyclerView.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);
       // footer=(LinearLayout)view.findViewById(R.id.footer);
        new_a=(TextView)view.findViewById(R.id.new_a);
//        footer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://itpvt.net/"));
//                startActivity(myIntent);
//            }
//        });
        chifon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),All_Products.class);
                intent.putExtra("id","85");
                startActivity(intent);
            }
        });
        mohagni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),All_Products.class);
                intent.putExtra("id","7");
                startActivity(intent);
            }
        });
        arena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),All_Products.class);
                intent.putExtra("id","5");
                startActivity(intent);
            }
        });
//        Glide.with(getActivity()).load(saleimage).into(New);
//        Glide.with(this).load(bajiImage).into(sale);
        GetAllProducts();
        GetAllProducts2();
//        sale.setOnClickListener(newa View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=newa Intent(getActivity(),All_Products.class);
//                intent.putExtra("id","5");
//                startActivity(intent);
//            }
//        });
//        New.setOnClickListener(newa View.OnClickListener() {
//            @Override
//            public void onClick(View v) {//60
//                Intent intent=newa Intent(getActivity(),All_Products.class);
//                intent.putExtra("id","6");
//                startActivity(intent);
//            }
//        });
        AddImagesUrlOnline();
        return view;
    }

    private void GetAllProducts2()
    {
        //        loading = ProgressDialog.show(getActivity(),"Loading...","Please wait...",false,false);
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_ALL_PRODUCTS, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


//                loading.dismiss();
                new_a2.setVisibility(View.GONE);




                try {
                    JSONObject abc= new JSONObject(response);
                    int j=abc.length();
                    for (int i=j;i>=1;i--)
                    {
                        String num= String.valueOf(i);
                        JSONObject data=abc.getJSONObject(num);
                        if (data.getString("product_quantity").equals("1"))
                        {
                            arrayList2.add(new All_product_pojo(data.getString("product_id"),data.getString("pro_name")
                                    ,data.getString("img_url").replace("localhost",Config.ip),data.getString("sku")
                                    ,data.getString("product_quantity"),data.getString("price").replace(".0000","Rs")));
                        }

                    }

//                        do {JSONObject data = newa getJSONObject.JSONObject("abc");
//                            String num= String.valueOf(i);
//                            obj_level1=data.getJSONObject(num);
//
//                            arrayList.add(newa All_product_pojo(obj_level1.getString("product_id"),obj_level1.getString("pro_name")
//                                    ,obj_level1.getString("img_url")));
//                            i++;
//
//                        }while (obj_level1.getJSONObject(String.valueOf(i))==null);
//                        {
//                            i++;
//
//                        }
                    adapter=new Recycler_Adapter_All_Products_new(arrayList2,getActivity());
                    recyclerView2.setAdapter(adapter);


                }

                catch (JSONException e) {
                    e.printStackTrace();
//                    loading.dismiss();
                    new_a2.setVisibility(View.GONE);
                    sale_tx.setVisibility(View.GONE);

                }



                //  tvSurah.setText("Response is: "+ response.substring(0,500));
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                loading.dismiss();
                //  Log.e("Error",error.printStackTrace());
                new_a2.setVisibility(View.GONE);
                sale_tx.setVisibility(View.GONE);
//                Toast.makeText(getActivity().getApplicationContext(), "Volley Error" + error, Toast.LENGTH_SHORT).show();

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("category_id", newArr_cat_id);
                return params;
            }
        };
        //////to stop reties and wait for respone more than regular time/////
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(request);
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
    private void AddImagesUrlOnline()

    {

        HashMapForURL = new HashMap<String, Integer>();

        HashMapForURL.put(" ", R.drawable.ba);
        HashMapForURL.put("   ", R.drawable.newin);
        HashMapForURL.put("  ", R.drawable.bann);

        callSlider();
    }


    private void callSlider() {

        for(String name : HashMapForURL.keySet()){

            TextSliderView textSliderView = new TextSliderView(getActivity().getApplicationContext());

            textSliderView
                    .description(name)
                    .image(HashMapForURL.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());

            textSliderView.getBundle()
                    .putString("extra",name);

            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(8000);
    }
    private void GetAllProducts()
    {

//        loading = ProgressDialog.show(getActivity(),"Loading...","Please wait...",false,false);
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_ALL_PRODUCTS, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


//                loading.dismiss();
                new_a.setVisibility(View.GONE);




                try {
                    JSONObject abc= new JSONObject(response);
                    int j=abc.length();
                    for (int i=j;i>=1;i--)
                    {
                        String num= String.valueOf(i);
                        JSONObject data=abc.getJSONObject(num);
                        if (data.getString("product_quantity").equals("1"))
                        {
                            arrayList.add(new All_product_pojo(data.getString("product_id"),data.getString("pro_name")
                                    ,data.getString("img_url").replace("localhost",Config.ip),data.getString("sku")
                                    ,data.getString("product_quantity"),data.getString("price").replace(".0000","Rs")));
                        }

                    }

//                        do {JSONObject data = newa getJSONObject.JSONObject("abc");
//                            String num= String.valueOf(i);
//                            obj_level1=data.getJSONObject(num);
//
//                            arrayList.add(newa All_product_pojo(obj_level1.getString("product_id"),obj_level1.getString("pro_name")
//                                    ,obj_level1.getString("img_url")));
//                            i++;
//
//                        }while (obj_level1.getJSONObject(String.valueOf(i))==null);
//                        {
//                            i++;
//
//                        }
                    adapter=new Recycler_Adapter_All_Products_new(arrayList,getActivity());
                    recyclerView.setAdapter(adapter);


                }

                catch (JSONException e) {
                    e.printStackTrace();
//                    loading.dismiss();
                    new_a.setVisibility(View.GONE);
                    new_tx.setVisibility(View.GONE);
                }



                //  tvSurah.setText("Response is: "+ response.substring(0,500));
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                loading.dismiss();
                //  Log.e("Error",error.printStackTrace());
                new_a.setVisibility(View.GONE);
                new_tx.setVisibility(View.GONE);
//                Toast.makeText(getActivity().getApplicationContext(), "Volley Error" + error, Toast.LENGTH_SHORT).show();

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("category_id", sale_cat_id);
                return params;
            }
        };
        //////to stop reties and wait for respone more than regular time/////
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(request);
    }
}
