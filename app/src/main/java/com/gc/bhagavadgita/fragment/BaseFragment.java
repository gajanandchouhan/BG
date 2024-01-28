package com.gc.bhagavadgita.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gc.bhagavadgita.contract.BaseView;

public abstract class BaseFragment<P> extends Fragment implements BaseView {

    P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initializePresenter();
    }

    public abstract P initializePresenter();

    @Override
    public void showProgress() {

    }

    @Override
    public void hidProgress() {

    }
}
