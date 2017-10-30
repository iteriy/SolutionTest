package ru.panmin.solution.ui.toolbar;

import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ru.panmin.solution.ui.progress.ProgressMvpView;

public interface ToolbarMvpView extends ProgressMvpView {

    void showToolbarShadow();

    void hideToolbarShadow();

    void setTitle(String title);

    void setTitle(@StringRes int title);

    void inflateMenu(@MenuRes int menuRes);

    void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener onMenuItemClickListener);

    MenuItem getMenuItem(@IdRes int menuItemId);

    void removeMenuItem(@IdRes int menuItemId);

}