package com.ecommerce.kharidlo_ui.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {

    public static SharedPreferences appPreference;
    public static SharedPreferences.Editor editor;
    public static Context appContext;
    private static String TOKEN;
    private static String USER_ROLE;

    private static final String APP_SETTINGS = "APP_SETTINGS";
    public static final String IS_LOGGED_IN = "IS_LOGGED_IN";
    public static final String IS_ADMIN = "IS_ADMIN";


    public static void setContext(Context context) {
        appContext = context;
        appPreference = appContext.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
        editor = appPreference.edit();
    }


    public static boolean isLoggedIn() {
        //TODO: change boolean value to false
        return appPreference.getBoolean(IS_LOGGED_IN, true);
    }

    public static void setLoggedIn(boolean loginState) {
        appPreference = appContext.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
        editor.putBoolean(IS_LOGGED_IN, loginState);
        editor.apply();
    }

    public static boolean isAdmin() {
        //TODO: change boolean value to false
        return appPreference.getBoolean(IS_ADMIN, false);
    }

    public static void setIfAdmin(boolean isAdmin) {
        appPreference = appContext.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
        editor.putBoolean(IS_ADMIN, isAdmin);
    }

    public static String getTOKEN() {
        return TOKEN;
    }

    public static void setTOKEN(String TOKEN) {
        SharedPreferenceUtil.TOKEN = TOKEN;
    }

    public static String getUserRole() {
        return USER_ROLE;
    }

    public static void setUserRole(String userRole) {
        USER_ROLE = userRole;
    }

    public static void clearUserData() {
        setUserRole(null);
        setLoggedIn(false);
        setTOKEN(null);
    }
}
