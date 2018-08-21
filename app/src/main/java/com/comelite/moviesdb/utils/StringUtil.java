package com.comelite.moviesdb.utils;

import android.support.annotation.StringRes;

import com.comelite.moviesdb.core.MoviesDBApplication;

public abstract class StringUtil {

    private static final String UTF_8 = "UTF-8";

    public static String getStringRes(@StringRes int stringRes) {
        return MoviesDBApplication.get().getString(stringRes);
    }

    public static boolean isEmpty(String value) {
        return (value == null) || value.trim().equals("");
    }
}
