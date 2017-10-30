package ru.panmin.solution.ui.toolbar;

import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import ru.panmin.solution.R;
import ru.panmin.solution.ui.progress.ProgressActivity;

public abstract class ToolbarActivity extends ProgressActivity implements ToolbarMvpView {

    @BindView(R.id.toolbar) protected Toolbar toolbar;
    @BindView(R.id.toolbarShadow) protected View toolbarShadow;

    public ToolbarActivity() {
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_toolbar;
    }

    @Override
    protected void init() {
        initToolbar();
        super.init();
    }

    @Override
    public void showToolbarShadow() {
        toolbarShadow.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideToolbarShadow() {
        toolbarShadow.setVisibility(View.GONE);
    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void setTitle(@StringRes int title) {
        toolbar.setTitle(title);
    }

    @Override
    public void inflateMenu(@MenuRes int menuRes) {
        toolbar.inflateMenu(menuRes);
    }

    @Override
    public void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    @Override
    public MenuItem getMenuItem(@IdRes int menuItemId) {
        return toolbar.getMenu().findItem(menuItemId);
    }

    @Override
    public void removeMenuItem(@IdRes int menuItemId) {
        toolbar.getMenu().removeItem(menuItemId);
    }

    protected abstract void initToolbar();

}