package com.si.uinam.mymoviecatalogueapi.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LocaleHelper {

    //public static String localeId = "in";

    public static void applyLocale(Context context){
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(getLocale(context).toLowerCase()));
        res.updateConfiguration(conf, dm);
    }

    public static void setLocale(Context context, String localeId){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Locale_Id",localeId);
        editor.apply();
    }

    public static String getLocale(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String localeId = preferences.getString("Locale_Id", "");
        if(!localeId.equalsIgnoreCase(""))
        {
            return localeId;
        }

        return "en";
    }

}
