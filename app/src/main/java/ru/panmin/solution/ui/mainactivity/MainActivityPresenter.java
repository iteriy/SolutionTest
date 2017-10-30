package ru.panmin.solution.ui.mainactivity;

import javax.inject.Inject;

import ru.panmin.solution.ui.toolbar.ToolbarPresenter;

class MainActivityPresenter extends ToolbarPresenter<MainActivityMvpView> {

    @Inject
    MainActivityPresenter() {
    }

    @Override
    protected void unsubscribe() {
    }

}