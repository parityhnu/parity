package com.lufan.parityproject.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.lufan.parityproject.ParityApplication;

import java.util.Set;

public class PreferenceUtil {

    private static final String SP_NAME = "parity_sp_file";

    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void setLong(Context context, String key, long value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void setString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setFloat(Context context, String key, float value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static void setInt(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setStringSet(Context context, String key, Set<String> value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public static long getLong(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, 0);
    }

    public static int getInt(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public static float getFloat(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, 0);
    }

    public static Set<String> getStringSet(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getStringSet(key, null);
    }

    public static void setUserBoolean(Context context, String key, boolean value) {
        int userId = ParityApplication.getInstance().getUserId();
        if (userId == 0) {
            setBoolean(context, key, value);
            return;
        }
        key = userId + key;
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void setUserLong(Context context, String key, long value) {
        int userId = ParityApplication.getInstance().getUserId();
        if (userId == 0) {
            setLong(context, key, value);
            return;
        }
        key = userId + key;
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void setUserString(Context context, String key, String value) {
        int userId = ParityApplication.getInstance().getUserId();
        if (userId == 0) {
            setString(context, key, value);
            return;
        }
        key = userId + key;
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setUserFloat(Context context, String key, float value) {
        int userId = ParityApplication.getInstance().getUserId();
        if (userId == 0) {
            setFloat(context, key, value);
            return;
        }
        key = userId + key;
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static void setUserInt(Context context, String key, int value) {
        int userId = ParityApplication.getInstance().getUserId();
        if (userId == 0) {
            setInt(context, key, value);
            return;
        }
        key = userId + key;
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setUserStringSet(Context context, String key, Set<String> value) {
        int userId = ParityApplication.getInstance().getUserId();
        if (userId == 0) {
            setStringSet(context, key, value);
            return;
        }
        key = userId + key;
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

    public static boolean getUserBoolean(Context context, String key) {
        int userId = ParityApplication.getInstance().getUserId();
        if (userId == 0) {
            return getBoolean(context, key);
        }
        key = userId + key;
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public static long getUserLong(Context context, String key) {
        int userId = ParityApplication.getInstance().getUserId();
        if (userId == 0) {
            return getLong(context, key);
        }
        key = userId + key;
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, 0);
    }

    public static int getUserInt(Context context, String key) {
        int userId = ParityApplication.getInstance().getUserId();
        if (userId == 0) {
            return getInt(context, key);
        }
        key = userId + key;
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }

    public static String getUserString(Context context, String key) {
        int userId = ParityApplication.getInstance().getUserId();
        if (userId == 0) {
            return getString(context, key);
        }
        key = userId + key;
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public static float getUserFloat(Context context, String key) {
        int userId = ParityApplication.getInstance().getUserId();
        if (userId == 0) {
            return getFloat(context, key);
        }
        key = userId + key;
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, 0);
    }

    public static Set<String> getUserStringSet(Context context, String key) {
        int userId = ParityApplication.getInstance().getUserId();
        if (userId == 0) {
            return getStringSet(context, key);
        }
        key = userId + key;
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getStringSet(key, null);
    }

}
