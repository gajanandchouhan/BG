package com.gc.bhagavadgita.acivity;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.gc.bhagavadgita.R;
import com.gc.bhagavadgita.adapter.VerseDetailstAdapter;
import com.gc.bhagavadgita.data.model.ChapterListResponse;
import com.gc.bhagavadgita.data.model.VersesListResponse;
import com.gc.bhagavadgita.databinding.ActivityVersesDetailsBinding;
import com.gc.bhagavadgita.interfaces.RecyclerItemClickListner;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.ArrayList;
import java.util.List;

public class VersesDetailsActivity extends AppCompatActivity implements RecyclerItemClickListner {
    ActivityVersesDetailsBinding binding;
    ChapterListResponse verse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verses_details);
        setSupportActionBar(binding.toolbar);
        verse = (ChapterListResponse) getIntent().getSerializableExtra("verse");
        setActionBarTitle();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.pager.setAdapter(new VerseDetailstAdapter(this, verse));
        binding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                position = i+1;
                setActionBarTitle();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        binding.pager.post(() -> {
            binding.pager.setCurrentItem(position);
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);

        setUpAdd();
    }

    private void setActionBarTitle() {
        getSupportActionBar().setTitle("अध्याय " + verse.getChapter_number() + ", श्लोक " + verse.get(position).getVerse_number());
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
        InterstitialAd.load(this, getString(R.string.interstitial1),adRequest, new InterstitialAdLoadCallback() {
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
        int currentItem = binding.pager.getCurrentItem();
        if (currentItem < verse.size() - 1) {
            binding.pager.setCurrentItem(currentItem + 1);
        }
    }

    public void onPreviousClick(View view) {
        int currentItem = binding.pager.getCurrentItem();
        if (currentItem > 0) {
            binding.pager.setCurrentItem(currentItem - 1);
        }
    }
}
