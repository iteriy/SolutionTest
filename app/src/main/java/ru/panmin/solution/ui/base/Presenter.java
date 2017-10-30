package ru.panmin.solution.ui.base;

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();

}