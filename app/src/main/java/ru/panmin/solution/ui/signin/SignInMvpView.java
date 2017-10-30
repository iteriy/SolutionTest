package ru.panmin.solution.ui.signin;

import ru.panmin.solution.ui.progress.ProgressMvpView;

interface SignInMvpView extends ProgressMvpView {

    void checkLicensing();

    void partnerLogin();

    void userLogin();

    void repeatRequest();

    void hideEmailError();

    void showEmailError();

    void hidePasswordError();

    void showPasswordError();

    void hideKeyboard();

    void showNotification();

}