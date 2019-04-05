package com.gc.bhagavadgita.contract;

import com.gc.bhagavadgita.data.model.ChapterListResponse;
import com.gc.bhagavadgita.data.model.VersesListResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerseContract {
    public interface Presenter {
        void getVerses(String number);
    }

    public interface View extends BaseView {
        void setVerses(List<VersesListResponse> versesListResponseList);
    }

}
