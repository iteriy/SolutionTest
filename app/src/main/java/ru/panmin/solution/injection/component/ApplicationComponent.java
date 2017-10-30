package ru.panmin.solution.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ru.panmin.solution.data.DataManager;
import ru.panmin.solution.data.local.PreferencesHelper;
import ru.panmin.solution.data.remote.ApiSolutionService;
import ru.panmin.solution.injection.ApplicationContext;
import ru.panmin.solution.injection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();

    PreferencesHelper preferencesHelper();

    ApiSolutionService apiSolutionService();

}