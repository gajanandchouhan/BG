package com.gc.bhagavadgita.presenter;

import android.content.Context;

import com.gc.bhagavadgita.R;
import com.gc.bhagavadgita.data.api.ApiClient;
import com.gc.bhagavadgita.data.api.ApiInterface;
import com.gc.bhagavadgita.data.local.PrefHelper;
import com.gc.bhagavadgita.data.model.AccessTokenResponse;
import com.gc.bhagavadgita.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BasePresenter<V> {
    protected final Context context;
    V view;
    ApiInterface apiInterface;

    public BasePresenter(Context context) {
        this.context = context;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public abstract void setView(V view);

    public abstract void dropView();

    public HashMap<String, RequestBody> getRequestParams(Map<String, String> stringParams) {

        HashMap<String, RequestBody> params = new HashMap<>();
        try {
            for (Map.Entry<String, String> entry : stringParams.entrySet()) {
                if (entry.getValue() != null) {
                    RequestBody requestBody = RequestBody.create(
                            MultipartBody.FORM, entry.getValue());
                    params.put(entry.getKey(), requestBody);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    public void getAuthtoken() {
        HashMap<String, String> params = new HashMap<>();
        params.put("client_id", ApiClient.clientId);
        params.put("client_secret", ApiClient.clientSecret);
        params.put("grant_type", "client_credentials");
        params.put("scope", "verse chapter");
        Call<AccessTokenResponse> accessToken = apiInterface.getAccessToken(getRequestParams(params));
        accessToken.enqueue(new Callback<AccessTokenResponse>() {
            //            @Override
            public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {
                if (response.body() != null) {
                    PrefHelper.getInstance(context).setAccessToken(response.body().getAccess_token());
                    onTokenRefreshed();
                } else {
                    Utils.showToast(context.getString(R.string.response_error), context);
                }
            }

            @Override
            public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
                Utils.showToast(context.getString(R.string.response_error), context);
            }
        });
    }

    protected abstract void onTokenRefreshed();
}
