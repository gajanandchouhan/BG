package com.gc.bhagavadgita.presenter;

import android.content.Context;

import com.gc.bhagavadgita.R;
import com.gc.bhagavadgita.contract.HomeContract;
import com.gc.bhagavadgita.contract.VerseContract;
import com.gc.bhagavadgita.data.local.PrefHelper;
import com.gc.bhagavadgita.data.model.ChapterListResponse;
import com.gc.bhagavadgita.data.model.VersesListResponse;
import com.gc.bhagavadgita.utils.Utils;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VersesPresenter extends BasePresenter<VerseContract.View> implements VerseContract.Presenter {

    VerseContract.View view;

    public VersesPresenter(Context context) {
        super(context);
    }

    @Override
    public void setView(VerseContract.View view) {
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
    public void getVerses(String number) {
        view.showProgress();
        Call<List<VersesListResponse>> chpatResponse = apiInterface.getVersesList(number, PrefHelper.getInstance(context).getAccessToken(), "hi");
        chpatResponse.enqueue(new Callback<List<VersesListResponse>>() {
            @Override
            public void onResponse(Call<List<VersesListResponse>> call, Response<List<VersesListResponse>> response) {
                view.hidProgress();
                if (response.code() == 401) {
                    getAuthtoken();
                } else if (response.body() != null) {
                    view.setVerses(response.body());
                } else {
                    Utils.showToast(context.getString(R.string.response_error), context);
                }

            }

            @Override
            public void onFailure(Call<List<VersesListResponse>> call, Throwable t) {
                view.hidProgress();
                Utils.showToast(context.getString(R.string.response_error), context);
            }
        });
    }

}
