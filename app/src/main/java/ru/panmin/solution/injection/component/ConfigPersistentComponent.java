package ru.panmin.solution.injection.component;

import dagger.Component;
import ru.panmin.solution.injection.ConfigPersistent;
import ru.panmin.solution.injection.module.ActivityModule;

@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

}