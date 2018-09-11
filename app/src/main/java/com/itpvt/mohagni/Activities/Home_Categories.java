package com.itpvt.mohagni.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.itpvt.mohagni.Adapters.Home_Pager_Adapter;
import com.itpvt.mohagni.Fragements.Catagories;
import com.itpvt.mohagni.Fragements.Home;
import com.itpvt.mohagni.R;


public class Home_Categories extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    Home_Pager_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(),R.color.black));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
                Intent intent=new Intent(Home_Categories.this,My_Cart.class);
                startActivity(intent);
            }
        });
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);



    }

    public void setupViewPager(ViewPager upViewPager) {

        Home_Pager_Adapter adapter = new Home_Pager_Adapter(getSupportFragmentManager());
        adapter.addFragment( new Home(), "HOME");
        adapter.addFragment( new Catagories(), "CATEGORIES");
        upViewPager.setAdapter(adapter);
    }
}
