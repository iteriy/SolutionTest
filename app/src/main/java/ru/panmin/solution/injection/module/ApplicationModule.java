package ru.panmin.solution.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.panmin.solution.data.remote.ApiSolutionService;
import ru.panmin.solution.injection.ApplicationContext;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    ApiSolutionService provideApiSolutionService() {
        return ApiSolutionService.Creator.newApiService(application);
    }

}