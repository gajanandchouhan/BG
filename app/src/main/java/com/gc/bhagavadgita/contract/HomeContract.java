package com.gc.bhagavadgita.contract;

import com.gc.bhagavadgita.data.model.ChapterListResponse;

import java.util.List;

public class HomeContract {
    public interface Presenter {
        void getChapter();
    }

    public interface HomeView extends BaseView {
        void setChapter(List<ChapterListResponse> chapterListResponseList);
    }

}
