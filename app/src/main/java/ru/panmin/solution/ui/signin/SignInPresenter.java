package ru.panmin.solution.ui.signin;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import ru.panmin.solution.data.DataManager;
import ru.panmin.solution.data.remote.params.UserLoginParams;
import ru.panmin.solution.data.remote.responses.CheckLicensingResponse;
import ru.panmin.solution.data.remote.responses.PartnerLoginResponse;
import ru.panmin.solution.data.remote.responses.UserLoginResponse;
import ru.panmin.solution.ui.progress.ProgressPresenter;
import ru.panmin.solution.utils.RxUtil;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class SignInPresenter extends ProgressPresenter<SignInMvpView> {

    private static final String PASSWORD_PATTERN = "";

    private final DataManager dataManager;

    private Subscription checkLicensingSubscriber;
    private Subscription partnerLoginSubscriber;
    private Subscription userLoginSubscriber;

    private RequestType currentRequest = RequestType.CHECK_LICENSING;

    @Inject
    SignInPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    protected void unsubscribe() {
        RxUtil.unsubscribe(checkLicensingSubscriber);
        RxUtil.unsubscribe(partnerLoginSubscriber);
        RxUtil.unsubscribe(userLoginSubscriber);
    }

    void checkLicensing(boolean isOnline) {
        currentRequest = RequestType.CHECK_LICENSING;
        if (isOnline) {
            getMvpView().setStateLoading();
            RxUtil.unsubscribe(checkLicensingSubscriber);
            checkLicensingSubscriber = dataManager.checkLicensing()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<CheckLicensingResponse>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            requestError(e);
                        }

                        @Override
                        public void onNext(CheckLicensingResponse response) {
                            if (response.isSuccess()) {
                                getMvpView().setStateData();
                                if (!response.getResult().isAllowed()) {
                                    getMvpView().showNotification();
                                }
                            } else {
                                if (TextUtils.isEmpty(response.getMessage())) {
                                    getMvpView().setStateUnknownServerError();
                                } else {
                                    getMvpView().setStateError(response.getMessage());
                                }
                            }
                        }
                    });
        } else {
            getMvpView().setStateInternetError();
        }
    }

    void partnerLogin(boolean isOnline) {
        currentRequest = RequestType.PARTNER_LOGIN;
        if (isOnline) {
            getMvpView().setStateLoading();
            RxUtil.unsubscribe(partnerLoginSubscriber);
            partnerLoginSubscriber = dataManager.partnerLogin()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<PartnerLoginResponse>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            requestError(e);
                        }

                        @Override
                        public void onNext(PartnerLoginResponse response) {
                            if (response.isSuccess()) {
                                getMvpView().userLogin();
                            } else {
                                if (TextUtils.isEmpty(response.getMessage())) {
                                    getMvpView().setStateUnknownServerError();
                                } else {
                                    getMvpView().setStateError(response.getMessage());
                                }
                            }
                        }
                    });
        } else {
            getMvpView().setStateInternetError();
        }
    }

    void userLogin(boolean isOnline, String email, String password) {
        currentRequest = RequestType.USER_LOGIN;
        if (isOnline) {
            getMvpView().setStateLoading();
            RxUtil.unsubscribe(userLoginSubscriber);
            userLoginSubscriber = dataManager.userLogin(new UserLoginParams(email, password))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<UserLoginResponse>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            requestError(e);
                        }

                        @Override
                        public void onNext(UserLoginResponse response) {
                            if (response.isSuccess()) {
                                getMvpView().setStateData();
                            } else {
                                if (TextUtils.isEmpty(response.getMessage())) {
                                    getMvpView().setStateUnknownServerError();
                                } else {
                                    getMvpView().setStateError(response.getMessage());
                                }
                            }
                        }
                    });
        } else {
            getMvpView().setStateInternetError();
        }
    }

    void repeatRequest() {
        switch (currentRequest) {
            case CHECK_LICENSING:
                getMvpView().checkLicensing();
                break;
            case PARTNER_LOGIN:
                getMvpView().partnerLogin();
                break;
            case USER_LOGIN:
                getMvpView().userLogin();
                break;
            default:
                getMvpView().checkLicensing();
                break;
        }
    }

    void validInfo(String email, String password) {
        boolean isEmailValid = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if (isEmailValid) {
            getMvpView().hideEmailError();
        } else {
            getMvpView().showEmailError();
        }

        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        boolean isPasswordValid = matcher.matches();
        if (isPasswordValid) {
            getMvpView().hidePasswordError();
        } else {
            getMvpView().showPasswordError();
        }
        if (isEmailValid && isPasswordValid) {
            getMvpView().hideKeyboard();

        }
    }

    private enum RequestType {
        CHECK_LICENSING, PARTNER_LOGIN, USER_LOGIN
    }

}