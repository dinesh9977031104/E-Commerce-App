package com.tekitsolutions.e_commerce_app.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static com.tekitsolutions.e_commerce_app.utils.Constants.IS_FIRST_TIME_LAUNCH;
import static com.tekitsolutions.e_commerce_app.utils.Constants.PREF_NAME;

public class PreferenceManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, false);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.apply();
    }
}
