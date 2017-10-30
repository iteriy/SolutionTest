package ru.panmin.solution.ui.progress;

import android.text.TextUtils;

import ru.panmin.solution.ui.base.BasePresenter;
import ru.panmin.solution.utils.Utils;

public abstract class ProgressPresenter<T extends ProgressMvpView> extends BasePresenter<T> {

    protected void requestError(Throwable e) {
        if (Utils.isInternetError(e)) {
            getMvpView().setStateInternetError();
        } else {
            if (TextUtils.isEmpty(e.getMessage())) {
                getMvpView().setStateError(e.getMessage());
            } else {
                getMvpView().setStateUnknownServerError();
            }
        }
    }

}