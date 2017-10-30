package ru.panmin.solution.ui.toolbar;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ru.panmin.solution.ui.progress.ProgressFragment;

public abstract class ToolbarFragment extends ProgressFragment implements ToolbarMvpView {

    private ToolbarMvpView toolbarMvpView;

    public ToolbarFragment() {
    }

    @Override
    public void onAttach(Context context) {
        toolbarMvpView = (ToolbarMvpView) context;
        super.onAttach(context);
    }

    @Override
    protected void init() {
        initToolbar();
        super.init();
    }

    @Override
    public void showToolbarShadow() {
        toolbarMvpView.showToolbarShadow();
    }

    @Override
    public void hideToolbarShadow() {
        toolbarMvpView.hideToolbarShadow();
    }

    @Override
    public void setTitle(String title) {
        toolbarMvpView.setTitle(title);
    }

    @Override
    public void setTitle(@StringRes int title) {
        toolbarMvpView.setTitle(title);
    }

    @Override
    public void inflateMenu(@MenuRes int menuRes) {
        toolbarMvpView.inflateMenu(menuRes);
    }

    @Override
    public void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        toolbarMvpView.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    @Override
    public MenuItem getMenuItem(@IdRes int menuItemId) {
        return toolbarMvpView.getMenuItem(menuItemId);
    }

    @Override
    public void removeMenuItem(@IdRes int menuItemId) {
        toolbarMvpView.removeMenuItem(menuItemId);
    }

    protected abstract void initToolbar();

}