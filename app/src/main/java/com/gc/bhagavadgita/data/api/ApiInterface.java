package com.gc.bhagavadgita.data.api;

import com.gc.bhagavadgita.data.model.AccessTokenResponse;
import com.gc.bhagavadgita.data.model.ChapterListResponse;
import com.gc.bhagavadgita.data.model.VersesListResponse;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    String API_END = "api/v1/";
    String AUTH_END = "auth/oauth/token";
    String CHAPTERS = API_END + "chapters";
    String VERSES = CHAPTERS + "/{chapter_num}/verses";


    @Multipart
    @POST(AUTH_END)
    Call<AccessTokenResponse> getAccessToken(@PartMap() Map<String, RequestBody> params);

    @GET(CHAPTERS)
    Call<List<ChapterListResponse>> getChapterList(@Query("access_token") String accessToken, @Query("language") String language);

    @GET(VERSES)
    Call<List<VersesListResponse>> getVersesList(@Path("chapter_num") String number, @Query("access_token") String accessToken, @Query("language") String language);

}
