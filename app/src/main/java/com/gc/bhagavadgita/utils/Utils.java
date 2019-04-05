package com.gc.bhagavadgita.utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static void showToast(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
