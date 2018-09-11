package com.itpvt.mohagni.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itpvt.mohagni.Adapters.FUll_Image_Adapter_Pager;
import com.itpvt.mohagni.Config;
import com.itpvt.mohagni.PojoClass.Full_Image_URL;
import com.itpvt.mohagni.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FullScreenImage extends AppCompatActivity {
    ImageView fullImage;
    ViewPager viewPager;
    private ProgressDialog loading;
    FUll_Image_Adapter_Pager adapter;
    ArrayList<Full_Image_URL> arrayList=new ArrayList<>();

    private ScaleGestureDetector scaleGestureDetector;
    private Matrix matrix = new Matrix();
    String URl;
    private boolean exit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        final Intent intent = getIntent();
        URl = intent.getStringExtra("URL");
        viewPager=(ViewPager)findViewById(R.id.view_pager);
//        Glide.with(FullScreenImage.this).load(URl).into(fullImage);
        loading = ProgressDialog.show(FullScreenImage.this,"Loading...","Please wait...",false,false);
//        scaleGestureDetector = new ScaleGestureDetector(this,new ScaleListener());
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_REMOVE_HD_IMAGE, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray axyz = new JSONArray(response);
                    for (int i=0; i<axyz.length(); i ++)
                    {
                        JSONObject data=axyz.getJSONObject(i);
                        arrayList.add(new Full_Image_URL(data.getString("img").replace("localhost",Config.ip)));

                    }

                    adapter=new FUll_Image_Adapter_Pager(FullScreenImage.this,arrayList);//now we send the name urls to adapter
                    viewPager.setAdapter(adapter);//we set that adapter to the recycerView
                    loading.dismiss();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(FullScreenImage.this,"Nothing is Available For Time Being",Toast.LENGTH_LONG).show();
                    loading.dismiss();
                    onBackPressed();
                }


                //  tvSurah.setText("Response is: "+ response.substring(0,500));
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                loading.dismiss();
                //  Log.e("Error",error.printStackTrace());
                Toast.makeText(FullScreenImage.this,"Nothing is Available For Time Being",Toast.LENGTH_LONG).show();
                loading.dismiss();

//                Toast.makeText(getActivity().getApplicationContext(), "Volley Error" + error, Toast.LENGTH_SHORT).show();

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("product_id", URl);
                return params;
            }
        };
        //////to stop reties and wait for respone more than regular time/////
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        scaleGestureDetector.onTouchEvent(ev);
//        return true;
//    }
//
//    private class ScaleListener extends ScaleGestureDetector.
//            SimpleOnScaleGestureListener {
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//            float scaleFactor = detector.getScaleFactor();
//            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));
//            matrix.setScale(scaleFactor, scaleFactor);
//            fullImage.setImageMatrix(matrix);
//            return true;
//        }
//    }
}
