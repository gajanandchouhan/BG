package com.gc.bhagavadgita.acivity;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gc.bhagavadgita.contract.BaseView;

public abstract class BaseActivity<P> extends AppCompatActivity implements BaseView {

    P presenter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initializePresenter();
    }

    public abstract P initializePresenter();

    @Override
    public void showProgress() {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setCancelable(false);
        }
        dialog.show();

    }

    @Override
    public void hidProgress() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

}
