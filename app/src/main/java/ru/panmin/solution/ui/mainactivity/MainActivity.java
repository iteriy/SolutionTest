package ru.panmin.solution.ui.mainactivity;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import ru.panmin.solution.R;
import ru.panmin.solution.ui.progress.EmptyBundle;
import ru.panmin.solution.ui.toolbar.ToolbarActivity;

public class MainActivity extends ToolbarActivity implements MainActivityMvpView {

    @Inject MainActivityPresenter mainActivityPresenter;

    public MainActivity() {
    }

    public static Intent createStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected int getDataView() {
        return R.layout.content_main;
    }

    @Override
    protected EmptyBundle getEmptyBundle() {
        return null;
        // TODO: на этом окне такого быть не может
    }

    @Override
    protected void attachView() {
        mainActivityPresenter.attachView(this);
    }

    @Override
    protected void initToolbar() {
        setTitle(R.string.app_name);
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void detachView() {
        mainActivityPresenter.detachView();
    }

    @Override
    protected void emptyButtonClick() {
    }

    @Override
    protected void errorButtonClick() {
    }

}