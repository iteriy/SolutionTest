package ru.panmin.solution.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

public class MessageUtils {

    private MessageUtils() {
    }

    public static Snackbar getShortSnackBar(View view, @NonNull String text) {
        return getSnackBar(view, text, Snackbar.LENGTH_SHORT);
    }

    public static Snackbar getShortSnackBar(View view, @StringRes int text) {
        return getSnackBar(view, view.getResources().getString(text), Snackbar.LENGTH_SHORT);
    }

    public static Snackbar getLongSnackBar(View view, @NonNull String text) {
        return getSnackBar(view, text, Snackbar.LENGTH_LONG);
    }

    public static Snackbar getLongSnackBar(View view, @StringRes int text) {
        return getSnackBar(view, view.getResources().getString(text), Snackbar.LENGTH_LONG);
    }

    public static Snackbar getIndefiniteSnackBar(View view, @NonNull String text) {
        return getSnackBar(view, text, Snackbar.LENGTH_INDEFINITE);
    }

    public static Snackbar getIndefiniteSnackBar(View view, @StringRes int text) {
        return getSnackBar(view, view.getResources().getString(text), Snackbar.LENGTH_INDEFINITE);
    }

    private static Snackbar getSnackBar(View rootView, @NonNull String text, int length) {
        return Snackbar.make(rootView, text, length);
    }

    public static void showShortToast(Context context, @NonNull String text) {
        showToast(context, text, Toast.LENGTH_SHORT);
    }

    public static void showShortToast(Context context, @StringRes int text) {
        showToast(context, context.getResources().getString(text), Toast.LENGTH_SHORT);
    }

    public static void showLongToast(Context context, @NonNull String text) {
        showToast(context, text, Toast.LENGTH_LONG);
    }

    public static void showLongToast(Context context, @StringRes int text) {
        showToast(context, context.getResources().getString(text), Toast.LENGTH_LONG);
    }

    private static void showToast(Context context, String text, int length) {
        Toast.makeText(context, text, length).show();
    }

}