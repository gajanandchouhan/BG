package com.gc.bhagavadgita;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

public class BhagavadGitaApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this, getString(R.string.admob_id));
    }
}
