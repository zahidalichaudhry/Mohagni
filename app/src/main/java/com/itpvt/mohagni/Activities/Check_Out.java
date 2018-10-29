package com.itpvt.mohagni.Activities;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itpvt.mohagni.Config;
import com.itpvt.mohagni.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Check_Out extends AppCompatActivity {
    EditText fname,lastname,email,address,city,state,tele,country,fax;
    private String ufname,ulastname,uemail,ucompany,uaddress,ucity,ustate,uzip,utele,ucountry,ufax;
    private Button review,order;
    String cart_no=null;
    TextView price1,ship1,grand1,code2;
    Button done;
    private LinearLayout detail,place;
    String code1,sutotal,shipping,total,mobile;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_check__out);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check_Out.super.onBackPressed();
            }
        });
        FloatingActionButton whatsapp=(FloatingActionButton)findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri uri  =Uri.parse("smsto:"+"+923161433343");
//                Intent intent =newa Intent(Intent.ACTION_SENDTO,uri);
//                intent.setPackage("com.whatsapp");

//                startActivity(intent);
                String smsNumber = "92316143343";

                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");//phone number without "+" prefix
                startActivity(sendIntent);
            }
        });
        FloatingActionButton bag=(FloatingActionButton)findViewById(R.id.bag);
        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Check_Out.this,My_Cart.class);
                startActivity(intent);
            }
        });
        detail=(LinearLayout)findViewById(R.id.detail);
        place=(LinearLayout)findViewById(R.id.place);
        fname=(EditText)findViewById(R.id.cfname);
        lastname=(EditText)findViewById(R.id.clastname);
        email=(EditText)findViewById(R.id.uemail);
//        company=(EditText)findViewById(R.id.company);
        address=(EditText)findViewById(R.id.address);
        city=(EditText)findViewById(R.id.city);
        state=(EditText)findViewById(R.id.stat);
//        zip=(EditText)findViewById(R.id.zip);
        tele=(EditText)findViewById(R.id.tele);
        country=(EditText)findViewById(R.id.country);
        fax=(EditText)findViewById(R.id.fax);
        order=(Button)findViewById(R.id.order);
        price1=(TextView)findViewById(R.id.total_price1);
        ship1=(TextView)findViewById(R.id.shipping);
        grand1=(TextView)findViewById(R.id.Grand_Total);
        code2=(TextView)findViewById(R.id.code);
        done=(Button)findViewById(R.id.done);
        country.setEnabled(false);
        mobile="(Mobile)";
        detail.setVisibility(View.GONE);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences settings = Check_Out.this.getSharedPreferences(Config.SHARED_PREF_CART, Context.MODE_PRIVATE);
                settings.edit().clear().commit();
                //Getting out sharedpreferences
                SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_CART_NO, Context.MODE_PRIVATE);
                //Getting editor
                SharedPreferences.Editor editor = preferences.edit();

                //Putting blank value to email
                editor.putString(Config.SHARED_PREF_CART_NO, null);
                editor.clear();
                editor.apply();
                Intent intent =new Intent(Check_Out.this,Home_Categories.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_CART, Context.MODE_PRIVATE);
        cart_no=sharedPreferences.getString(Config.SHARED_PREF_CART_NO,null);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_email = email.getText().toString().trim();
                if (fname.getText().length()==0) {
                    fname.requestFocus();
                    fname.setError(Html.fromHtml("<font color='red'>Please Enter Your First Name</font>"));
                }
                else if (lastname.getText().length()==0) {
                    lastname.requestFocus();
                    lastname.setError(Html.fromHtml("<font color='red'>Please Enter LastName</font>"));
                }
                else if (email.getText().length()==0) {
                    email.requestFocus();
                    email.setError(Html.fromHtml("<font color='red'>Please Enter Your Email</font>"));
                }
                else if (user_email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(user_email).matches()) {
                    email.requestFocus();
                    email.setError(Html.fromHtml("<font color='red'>Invalid Email Email</font>"));
                }
//                else if (company.getText().length()==0) {
//                    company.requestFocus();
//                    company.setError(Html.fromHtml("<font color='red'>Please Enter A Password</font>"));
//                }
                else if (address.getText().length()==0) {
                    address.requestFocus();
                    address.setError(Html.fromHtml("<font color='red'>Please Enter A Address</font>"));
                }
                else if (city.getText().length()==0) {
                    city.requestFocus();
                    city.setError(Html.fromHtml("<font color='red'>Please Enter A City</font>"));
                }
                else if (state.getText().length()==0) {
                    state.requestFocus();
                    state.setError(Html.fromHtml("<font color='red'>Please Enter A State</font>"));
                }
                else if (tele.getText().length()==0) {
                    tele.requestFocus();
                    tele.setError(Html.fromHtml("<font color='red'>Please Enter A Phone</font>"));
                }
                else if (country.getText().length()==0) {
                    country.requestFocus();
                    country.setError(Html.fromHtml("<font color='red'>Please Enter A Country</font>"));
                }
//                else if (fax.getText().length()==0) {
//                    fax.requestFocus();
//                    fax.setError(Html.fromHtml("<font color='red'>Please Enter A Password</font>"));
//                }
                else if (v == order) {
                    //RegisterOrder();
                    ufname=mobile+fname.getText().toString();
                    ulastname=lastname.getText().toString();
                    uemail=email.getText().toString();
                    ucompany="Mobile";
                    uaddress=address.getText().toString();
                    ucity=city.getText().toString();
                    ustate=state.getText().toString();
                    uzip="Mobile";
                    utele=tele.getText().toString();
                    ucountry="PK";
                    ufax=fax.getText().toString();
                    placeorder();
                }
            }
        });

//        review=(Button)findViewById(R.id.review);
//        review.setOnClickListener(newa View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=newa Intent(Check_Out.this,MyCart.class);
//                startActivity(intent);
//            }
//        });
    }
    private void placeorder() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        RegisterOrder();

                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private void RegisterOrder()
    {
        loading = ProgressDialog.show(this,"Placing Order...","Please Wait...",false,false);
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_CREATE_ORDER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                try {
                    JSONArray main=new JSONArray(response);
                    JSONObject zero=main.getJSONObject(0);
                    code1=zero.getString("order code");
                    JSONArray payment=zero.getJSONArray("Payment");
                    JSONObject sub=payment.getJSONObject(0);
                    sutotal=sub.getString("amount");
                    JSONObject ship=payment.getJSONObject(1);
                    shipping=ship.getString("amount");
                    JSONObject grand=payment.getJSONObject(2);
                    total=grand.getString("amount");

                    place.setVisibility(View.GONE);
                    detail.setVisibility(View.VISIBLE);

                    price1.setText(sutotal);
                    code2.setText(code1);
                    ship1.setText(shipping);
                    grand1.setText(total);
                    //Getting out sharedpreferences
//                    SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_CART_NO, Context.MODE_PRIVATE);
//                    //Getting editor
//                    SharedPreferences.Editor editor = preferences.edit();
//
//                    //Putting blank value to email
//                    editor.putString(Config.SHARED_PREF_CART_NO, null);
//                    editor.clear();
//                    editor.apply();

                    done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //Getting out sharedpreferences
//                            SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_CART_NO, Context.MODE_PRIVATE);
//                            //Getting editor
//                            SharedPreferences.Editor editor = preferences.edit();
//
//                            //Putting blank value to email
//                            editor.putString(Config.SHARED_PREF_CART_NO, null);
//                            editor.clear();
//                            editor.apply();
                            SharedPreferences settings = Check_Out.this.getSharedPreferences(Config.SHARED_PREF_CART, Context.MODE_PRIVATE);
                            settings.edit().clear().commit();
                            Intent intent =new Intent(Check_Out.this,Home_Categories.class);
                            startActivity(intent);
                        }
                    });

//                    OrderDetails();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Check_Out.this,"Please Check Your Email",Toast.LENGTH_LONG).show();
                    detail.setVisibility(View.VISIBLE);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  Log.e("Error",error.printStackTrace());
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "Network Error" , Toast.LENGTH_SHORT).show();

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Config.CART_ID, cart_no);
                params.put(Config.LASTNAME, ulastname);
                params.put(Config.FIRSTNAME, ufname);
                params.put(Config.EMAIL, uemail);
                params.put(Config.COMPANY, ucompany);
                params.put(Config.STREET, uaddress);
                params.put(Config.CITY, ucity);
                params.put(Config.REGION, ustate);
                params.put(Config.POSTALCODE, uzip);
                params.put(Config.COUNTRY, ucountry);
                params.put(Config.PHONE, utele);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
    /////////////////END/////////////////////////////////////
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
