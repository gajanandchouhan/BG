package com.gc.bhagavadgita.presenter;

import android.content.Context;

import com.gc.bhagavadgita.R;
import com.gc.bhagavadgita.contract.VerseContract;
import com.gc.bhagavadgita.data.model.VersesListResponse;
import com.gc.bhagavadgita.utils.Utils;

import java.util.List;

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
    public void getVerses(String number, String slokNum) {
        Call<VersesListResponse> chpatResponse = apiInterface.getVersesList(number, slokNum);
        chpatResponse.enqueue(new Callback<VersesListResponse>() {
            @Override
            public void onResponse(Call<VersesListResponse> call, Response<VersesListResponse> response) {
                if (response.body() != null) {
                    view.setVerses(response.body());
                } else {
                    Utils.showToast(context.getString(R.string.response_error), context);
                }

            }

            @Override
            public void onFailure(Call<VersesListResponse> call, Throwable t) {
                Utils.showToast(context.getString(R.string.response_error), context);
            }
        });
    }

}
