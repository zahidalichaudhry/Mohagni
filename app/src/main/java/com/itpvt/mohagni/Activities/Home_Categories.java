package com.itpvt.mohagni.Activities;

import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.PhoneNumberUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.itpvt.mohagni.Adapters.Home_Pager_Adapter;
import com.itpvt.mohagni.Fragements.Catagories;
import com.itpvt.mohagni.Fragements.Home;
import com.itpvt.mohagni.R;


public class Home_Categories extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
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
        FloatingActionButton whatsapp=(FloatingActionButton) findViewById(R.id.whatsapp);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
                Intent intent=new Intent(Home_Categories.this,My_Cart.class);
                startActivity(intent);
            }
        });
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
                Intent facebookIntent = openFacebook(Home_Categories.this);
                startActivity(facebookIntent);
            }
        });
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);



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
    public void setupViewPager(ViewPager upViewPager) {

        Home_Pager_Adapter adapter = new Home_Pager_Adapter(getSupportFragmentManager());
        adapter.addFragment( new Home(), "HOME");
        adapter.addFragment( new Catagories(), "CATEGORIES");
        upViewPager.setAdapter(adapter);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.new_ar)
        {
            Intent intent=new Intent(Home_Categories.this,All_Products.class);
            intent.putExtra("id","4");
            startActivity(intent);
        } else if (id == R.id.Cart)
        {

            Intent intent=new Intent(Home_Categories.this,My_Cart.class);
            startActivity(intent);

        } else if (id == R.id.Whatsapp)
        {
            Uri uri  = Uri.parse("smsto:"+"+923161433343");
            Intent intent =new Intent(Intent.ACTION_SENDTO,uri);
            intent.setPackage("com.whatsapp");
            startActivity(intent);

        } else if (id == R.id.Innovators)
        {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://itpvt.net/"));
            startActivity(myIntent);

        }else if (id == R.id.web) {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mohagni.com/"));
            startActivity(myIntent);

        }
        else if (id == R.id.sale_a) {
            Intent intent=new Intent(Home_Categories.this,All_Products.class);
            intent.putExtra("id","88");
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
