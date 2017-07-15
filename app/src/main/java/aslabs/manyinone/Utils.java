package aslabs.manyinone;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by asif on 15/07/2017.
 */

public class Utils {

    public static String READ_APP_PREFERENCE = "ReadAppPreference";
    public static String READ_NEWS_MODE = "news_mode";
    public static String FIRST_OPEN = "first_open";
    public static int READ_TOP_NEWS = 1;
    public static int READ_AREA_NEWS = 2;
    public static int READ_LANG_NEWS = 3;

    public static void setPreferenceString(Context context, String name, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(READ_APP_PREFERENCE, 0).edit();
        editor.putString(name, value);
        editor.apply();
    }

    public static String getPreferenceString(Context context, String name) {
        SharedPreferences prefs = context.getSharedPreferences(READ_APP_PREFERENCE, 0);
        return prefs.getString(name, "null");
    }

    public static int getPreferenceInt(Context context, String name) {
        SharedPreferences prefs = context.getSharedPreferences(READ_APP_PREFERENCE, 0);
        return prefs.getInt(name, 0);
    }

    public static void setPreferenceInt(Context context, String name, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(READ_APP_PREFERENCE, 0).edit();
        editor.putInt(name, value);
        editor.apply();
    }
}
