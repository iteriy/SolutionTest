package ru.panmin.solution.injection.component;

import dagger.Subcomponent;
import ru.panmin.solution.injection.PerActivity;
import ru.panmin.solution.injection.module.ActivityModule;
import ru.panmin.solution.ui.mainactivity.MainActivity;
import ru.panmin.solution.ui.signin.SignInActivity;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    //активити
    void inject(MainActivity activity);

    void inject(SignInActivity activity);


    //фрагменты

}