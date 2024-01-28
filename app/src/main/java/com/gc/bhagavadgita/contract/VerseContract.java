package com.gc.bhagavadgita.contract;

import com.gc.bhagavadgita.data.model.VersesListResponse;

import java.util.List;

public class VerseContract {
    public interface Presenter {
        void getVerses(String number, String slokNum);
    }

    public interface View extends BaseView {
        void setVerses(VersesListResponse versesListResponseList);
    }

}
