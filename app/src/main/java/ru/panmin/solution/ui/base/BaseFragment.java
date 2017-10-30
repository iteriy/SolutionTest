package ru.panmin.solution.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected Unbinder unbinder;

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inject();
        attachView();
        init();
    }

    @Override
    public void onDestroyView() {
        detachView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    protected void init() {
        initViews();
    }

    public void finishActivity() {
        ((BaseActivity) getActivity()).finishActivity();
    }

    @LayoutRes
    protected abstract int getLayout();

    protected abstract void inject();

    protected abstract void attachView();

    protected abstract void initViews();

    protected abstract void detachView();

}