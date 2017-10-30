package ru.panmin.solution.ui.progress;

import android.support.annotation.DrawableRes;

import ru.panmin.solution.ui.base.MvpView;

public interface ProgressMvpView extends MvpView {

    int STATE_LOADING = 0;
    int STATE_DATA = 1;
    int STATE_EMPTY = 2;
    int STATE_ERROR = 3;

    void initStateEmpty();

    void setStateLoading();

    void setStateData();

    void setStateEmpty();

    void setStateInternetError();

    void setStateUnknownServerError();

    void setStateError(String textDescription);

    void setStateError(
            @DrawableRes int image,
            String textTitle,
            String textDescription
    );

    void setStateError(
            @DrawableRes int image,
            String textTitle,
            String textDescription,
            String textButton
    );

}