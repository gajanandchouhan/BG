package com.gc.bhagavadgita.presenter;

import android.content.Context;

import com.gc.bhagavadgita.R;
import com.gc.bhagavadgita.contract.SplashContract;
import com.gc.bhagavadgita.data.api.ApiClient;
import com.gc.bhagavadgita.data.local.PrefHelper;
import com.gc.bhagavadgita.data.model.AccessTokenResponse;
import com.gc.bhagavadgita.utils.Utils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashPresenter extends BasePresenter<SplashContract.SplashView> implements SplashContract.Presenter {
    SplashContract.SplashView view;

    public SplashPresenter(Context context) {
        super(context);
    }

    @Override
    public void setView(SplashContract.SplashView view) {
        this.view = view;
    }

    @Override
    public void dropView() {

    }

    @Override
    protected void onTokenRefreshed() {
        view.onTokenRefresh();
    }
    @Override
    public void getAuthToken() {
        super.getAuthtoken();
    }
}
