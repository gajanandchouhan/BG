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
    String CHAPTERS = "chapters";
    String SLOK = "slok/{chapter_num}/{slok_num}";



    @GET(CHAPTERS)
    Call<List<ChapterListResponse>> getChapterList(@Query("access_token") String accessToken, @Query("language") String language);

    @GET(SLOK)
    Call<VersesListResponse> getVersesList(@Path("chapter_num") String number, @Path("slok_num") String slokNum);

}
