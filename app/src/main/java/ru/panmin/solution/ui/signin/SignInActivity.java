package ru.panmin.solution.ui.signin;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import ru.panmin.solution.R;
import ru.panmin.solution.SolutionApplication;
import ru.panmin.solution.ui.progress.EmptyBundle;
import ru.panmin.solution.ui.progress.ProgressActivity;

public class SignInActivity extends ProgressActivity implements SignInMvpView {

    @Inject
    SignInPresenter signInPresenter;

    @BindView(R.id.textInputLayoutEmail)
    TextInputLayout textInputLayoutEmail;

    @BindView(R.id.textInputLayoutPassword)
    TextInputLayout textInputLayoutPassword;

    @BindView(R.id.buttonSignIn)
    AppCompatButton buttonSignIn;

    @BindView(R.id.pandoraS)
    TextView pandoraS;

    public SignInActivity() {
    }

    public static Intent createStartIntent(Context context) {
        return new Intent(context, SignInActivity.class);
    }

    @Override
    protected int getDataView() {
        return R.layout.content_sign_in;
    }

    @Override
    protected EmptyBundle getEmptyBundle() {
        return null;
    }

    @Override
    protected void inject() {
        activityComponent().inject(this);
    }

    @Override
    protected void attachView() {
        signInPresenter.attachView(this);
    }

    @Override
    protected void initViews() {
        checkLicensing();
        buttonSignIn.setOnClickListener(view -> partnerLogin());
    }

    @Override
    protected void detachView() {
        signInPresenter.detachView();
    }

    @Override
    protected void emptyButtonClick() {
        repeatRequest();
    }

    @Override
    protected void errorButtonClick() {
        repeatRequest();
    }

    @Override
    public void checkLicensing() {
        signInPresenter.checkLicensing(SolutionApplication.get(this).isOnline());
    }

    @Override
    public void partnerLogin() {
        signInPresenter.partnerLogin(SolutionApplication.get(this).isOnline());
    }

    @Override
    public void userLogin() {
        String email = "";
        AppCompatEditText editTextEmail = (AppCompatEditText) textInputLayoutEmail.getEditText();
        if (editTextEmail != null) {
            email = editTextEmail.getText().toString();
        }
        String password = "";
        AppCompatEditText editTextPassword = (AppCompatEditText) textInputLayoutPassword.getEditText();
        if (editTextPassword != null) {
            password = editTextPassword.getText().toString();
        }
        signInPresenter.userLogin(SolutionApplication.get(this).isOnline(), email, password);
    }

    @Override
    public void repeatRequest() {
        signInPresenter.repeatRequest();
    }

    @Override
    public void hideEmailError() {
        textInputLayoutEmail.setError("");
        textInputLayoutEmail.setErrorEnabled(false);
    }

    @Override
    public void showEmailError() {
        textInputLayoutEmail.setErrorEnabled(true);
        textInputLayoutEmail.setError("not a valid email address");
    }

    @Override
    public void hidePasswordError() {
        textInputLayoutPassword.setError("");
        textInputLayoutPassword.setErrorEnabled(false);
    }

    @Override
    public void showPasswordError() {
        textInputLayoutPassword.setErrorEnabled(true);
        textInputLayoutPassword.setError("Incorrect password");
    }

    @Override
    public void showNotification() {
        pandoraS.setVisibility(View.VISIBLE);
    }

}