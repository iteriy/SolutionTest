package ru.panmin.solution.utils;

import android.text.TextUtils;

public class Utils {

    private Utils() {
    }

    public static boolean isInternetError(Throwable e) {
        return !TextUtils.isEmpty(e.getMessage())
                && e.getMessage().contains("Unable to resolve host")
                && e.getMessage().contains("No address associated with hostname");
    }

}