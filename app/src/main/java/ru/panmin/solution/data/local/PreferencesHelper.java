package ru.panmin.solution.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.panmin.solution.injection.ApplicationContext;

@Singleton
public class PreferencesHelper {

    private static final String PREF_FILE_NAME = "ru.panmin.solution.shared.preferences";

    private static final String PREFS_PARTNER_LOGIN_REQUEST_TIME = "ru.panmin.solution.partner.login.request.time";
    private static final long DEFAULT_PREFS_PARTNER_LOGIN_REQUEST_TIME = 0;
    private static final String PREFS_PARTNER_ID = "ru.panmin.solution.partner.id";
    private static final String DEFAULT_PREFS_PARTNER_ID = "";
    private static final String PREFS_PARTNER_AUTH_TOKEN = "ru.panmin.solution.partner.auth.token";
    private static final String DEFAULT_PREFS_PARTNER_AUTH_TOKEN = "";
    private static final String PREFS_SYNC_TIME = "ru.panmin.solution.sync.time";
    private static final String DEFAULT_PREFS_SYNC_TIME = "";

    private final SharedPreferences pref;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        pref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        pref.edit().clear().apply();
    }

    public long getPartnerLoginRequestTime() {
        return pref.getLong(PREFS_PARTNER_LOGIN_REQUEST_TIME, DEFAULT_PREFS_PARTNER_LOGIN_REQUEST_TIME);
    }

    public void setPartnerLoginRequestTime(long partnerLoginRequestTime) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(PREFS_PARTNER_LOGIN_REQUEST_TIME, partnerLoginRequestTime);
        editor.apply();
    }

    public String getPartnerId() {
        return pref.getString(PREFS_PARTNER_ID, DEFAULT_PREFS_PARTNER_ID);
    }

    public void setPartnerId(String partnerId) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREFS_PARTNER_ID, partnerId);
        editor.apply();
    }

    public String getPartnerAuthToken() {
        return pref.getString(PREFS_PARTNER_AUTH_TOKEN, DEFAULT_PREFS_PARTNER_AUTH_TOKEN);
    }

    public void setPartnerAuthToken(String partnerAuthToken) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREFS_PARTNER_AUTH_TOKEN, partnerAuthToken);
        editor.apply();
    }

    public long getSyncTime() {
        return Long.parseLong(pref.getString(PREFS_SYNC_TIME, DEFAULT_PREFS_SYNC_TIME));
    }

    public void setSyncTime(String syncTime) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREFS_SYNC_TIME, syncTime);
        editor.apply();
    }

}