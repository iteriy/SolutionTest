package ru.panmin.solution;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import ru.panmin.solution.injection.component.ApplicationComponent;
import ru.panmin.solution.injection.component.DaggerApplicationComponent;
import ru.panmin.solution.injection.module.ApplicationModule;

public class SolutionApplication extends Application {

    ApplicationComponent mApplicationComponent;

    public static SolutionApplication get(Context context) {
        return (SolutionApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}