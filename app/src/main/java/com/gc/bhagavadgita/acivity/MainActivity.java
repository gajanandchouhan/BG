package com.gc.bhagavadgita.acivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gc.bhagavadgita.BuildConfig;
import com.gc.bhagavadgita.R;
import com.gc.bhagavadgita.databinding.ActivityMainBinding;
import com.gc.bhagavadgita.fragment.HomeFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ActivityMainBinding mainBinding;

    int currentId;
    private String APP_URL = "https://play.google.com/store/apps/details?id=com.gc.bhagavadgita";
    String shareText = "नमस्ते, अब इस यूआरएल का उपयोग करके श्रीमद्भगवद्\u200Cगीता एप्लिकेशन डाउनलोड करें:\n" +
            APP_URL;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mainBinding.drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mainBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        currentId = R.id.nav_home;
        AdRequest adRequest = new AdRequest.Builder().build();
        AdView adView = findViewById(R.id.adView);
        adView.loadAd(adRequest);
        repaceFragment(HomeFragment.newInstance());
    }

    @Override
    public Object initializePresenter() {
        return null;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (currentId == id) {
            mainBinding.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        currentId = id;
        if (id == R.id.nav_home) {

            // Handle the camera action
            repaceFragment(HomeFragment.newInstance());
        } else if (id == R.id.nav_share) {
            Intent intent2 = new Intent();
            intent2.setAction(Intent.ACTION_SEND);
            intent2.setType("text/plain");
            intent2.putExtra(Intent.EXTRA_TEXT, shareText);
            startActivity(Intent.createChooser(intent2, "Share via"));
        } else if (id == R.id.nav_rate) {

            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID));
                startActivity(intent);
            } catch (Exception e) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(APP_URL));
                startActivity(intent);
            }

        }

        mainBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void repaceFragment(Fragment homeFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
    }

    @Override
    public void onTokenRefresh() {

    }
}
