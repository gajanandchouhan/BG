package com.gc.bhagavadgita.acivity;

import android.content.Intent;
import android.os.Bundle;

import com.gc.bhagavadgita.R;
import com.gc.bhagavadgita.contract.SplashContract;
import com.gc.bhagavadgita.presenter.SplashPresenter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.SplashView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        presenter.getAuthToken();
        presenter.setView(this);
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public SplashPresenter initializePresenter() {
        return new SplashPresenter(this);
    }



    @Override
    public void onTokenRefresh() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
