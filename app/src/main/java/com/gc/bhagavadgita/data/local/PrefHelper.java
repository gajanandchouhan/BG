package com.gc.bhagavadgita.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class PrefHelper {
    private static PrefHelper prefHelper;
    private static final String PREF_NAME = "bhagvad_gita_prefs";
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editer;

    public PrefHelper(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        editer = preferences.edit();
    }

    public static PrefHelper getInstance(Context context) {
        if (prefHelper == null) {
            prefHelper = new PrefHelper(context);
        }
        return prefHelper;
    }

    public void setAccessToken(String token) {
        editer.putString("access_token", token).apply();
    }

    public String getAccessToken() {
        return preferences.getString("access_token", null);
    }
}
