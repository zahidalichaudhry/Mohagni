package com.itpvt.mohagni.Fragements;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itpvt.mohagni.Adapters.Main_Catagory_Adapter;
import com.itpvt.mohagni.Config;
import com.itpvt.mohagni.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Catagories extends Fragment {


    ArrayList<com.itpvt.mohagni.PojoClass.Catagories> arrayList=new ArrayList<>();
    RecyclerView recyclerView;
    Main_Catagory_Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private ProgressDialog loading;
    public Catagories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_catagories, container, false);
        final View view = inflater.inflate(R.layout.fragment_catagories, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.model_recyclerView);
        layoutManager=new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        MainCategories();
        return view;
    }
    private void MainCategories()

    {

        loading = ProgressDialog.show(getActivity(),"Loading...","Please wait...",false,false);
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_All_Categories, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                try {
                    JSONObject obj_level0 = new JSONObject(response);

                    JSONArray data_level0 = obj_level0.getJSONArray("children");
                    for (int i=0;i<data_level0.length();i++)
                    {
                        JSONObject obj_level1=data_level0.getJSONObject(i);
                        JSONArray data_level1 =obj_level1.getJSONArray("children");

                        for (int j =0 ;j< data_level1.length(); j++){

                            JSONObject cat = data_level1.getJSONObject(j);
                            JSONArray data=cat.getJSONArray("children");
                            int childs=data.length();

                            if (cat.getString("is_active").equals("1"))
                            {
                                arrayList.add(new com.itpvt.mohagni.PojoClass.Catagories(cat.getString("category_id"),cat.getString("parent_id"),
                                        cat.getString("name"),
                                        cat.getString("is_active"),cat.getString("position"),cat.getString("level"),childs));}
                        }
                    }
                    adapter=new Main_Catagory_Adapter(arrayList,getActivity());
                    recyclerView.setAdapter(adapter);


                }

                catch (JSONException e) {
                    e.printStackTrace();
                    loading.dismiss();
                    //  Log.e("Error",error.printStackTrace());
                    //Toast.makeText(getActivity().getApplicationContext(), "Volley Error" + error, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity().getApplicationContext(), "Network Connection Error" , Toast.LENGTH_SHORT).show();
                }
                //  tvSurah.setText("Response is: "+ response.substring(0,500));
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                //  Log.e("Error",error.printStackTrace());
                //Toast.makeText(getActivity().getApplicationContext(), "Volley Error" + error, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity().getApplicationContext(), "Network Connection Error" , Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(request);
    }
}
