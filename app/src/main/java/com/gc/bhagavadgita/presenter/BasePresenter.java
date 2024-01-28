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


}
