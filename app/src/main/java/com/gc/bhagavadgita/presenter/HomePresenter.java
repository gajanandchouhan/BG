package com.gc.bhagavadgita.presenter;

import android.content.Context;

import com.gc.bhagavadgita.R;
import com.gc.bhagavadgita.contract.HomeContract;
import com.gc.bhagavadgita.data.local.PrefHelper;
import com.gc.bhagavadgita.data.model.ChapterListResponse;
import com.gc.bhagavadgita.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter extends BasePresenter<HomeContract.HomeView> implements HomeContract.Presenter {

    HomeContract.HomeView view;

    public HomePresenter(Context context) {
        super(context);
    }

    @Override
    public void setView(HomeContract.HomeView view) {
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
    public void getChapter() {
        view.showProgress();
        Call<List<ChapterListResponse>> chpatResponse = apiInterface.getChapterList(PrefHelper.getInstance(context).getAccessToken(), "hi");
        chpatResponse.enqueue(new Callback<List<ChapterListResponse>>() {
            @Override
            public void onResponse(Call<List<ChapterListResponse>> call, Response<List<ChapterListResponse>> response) {
                view.hidProgress();
                if (response.code() == 401) {
                    getAuthtoken();
                } else if (response.body() != null) {
                    view.setChapter(response.body());
                } else {
                    Utils.showToast(context.getString(R.string.response_error), context);
                }

            }

            @Override
            public void onFailure(Call<List<ChapterListResponse>> call, Throwable t) {
                view.hidProgress();
                Utils.showToast(context.getString(R.string.response_error), context);
            }
        });
    }
}
