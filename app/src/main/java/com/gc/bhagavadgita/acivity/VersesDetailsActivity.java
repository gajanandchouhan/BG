package com.gc.bhagavadgita.acivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.gc.bhagavadgita.R;
import com.gc.bhagavadgita.contract.VerseContract;
import com.gc.bhagavadgita.data.model.ChapterListResponse;
import com.gc.bhagavadgita.data.model.VersesListResponse;
import com.gc.bhagavadgita.databinding.ActivityVersesDetailsBinding;
import com.gc.bhagavadgita.interfaces.RecyclerItemClickListner;
import com.gc.bhagavadgita.presenter.VersesPresenter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class VersesDetailsActivity extends BaseActivity<VersesPresenter> implements RecyclerItemClickListner, VerseContract.View {
    ActivityVersesDetailsBinding binding;
    ChapterListResponse chapter;
    private int slokNum = 1;

    @Override
    public void showProgress() {
        super.showProgress();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verses_details);
        setSupportActionBar(binding.toolbar);
        presenter.setView(this);
        chapter = (ChapterListResponse) getIntent().getSerializableExtra("chapter");
        setActionBarTitle();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);
        getSlok();
        setUpAdd();
    }

    private void getSlok() {
        binding.pBar.setVisibility(View.VISIBLE);
        presenter.getVerses(String.valueOf(chapter.getChapter_number()), String.valueOf(slokNum));
    }

    @Override
    public VersesPresenter initializePresenter() {
        return new VersesPresenter(this);
    }

    private void setActionBarTitle() {
        getSupportActionBar().setTitle("अध्याय " + chapter.getChapter_number() + ", श्लोक " + slokNum);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position) {

    }

    void setUpAdd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, getString(R.string.interstitial1), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                Log.i("TAG", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.d("TAG", loadAdError.toString());
            }
        });
    }


    public void onNextClick(View view) {

        if (slokNum < Integer.parseInt(chapter.getVerses_count())) {
            slokNum = slokNum + 1;
            getSlok();
            setActionBarTitle();
        }
    }

    public void onPreviousClick(View view) {
        if (slokNum > 1) {
            slokNum = slokNum - 1;
            getSlok();
            setActionBarTitle();
        }
    }

    @Override
    public void setVerses(VersesListResponse versesListResponseList) {
        binding.pBar.setVisibility(View.GONE);
        binding.setDetails(versesListResponseList);
    }
}
