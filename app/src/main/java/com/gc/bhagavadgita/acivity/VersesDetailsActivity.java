package com.gc.bhagavadgita.acivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.gc.bhagavadgita.R;
import com.gc.bhagavadgita.adapter.VerseDetailstAdapter;
import com.gc.bhagavadgita.data.model.VersesListResponse;
import com.gc.bhagavadgita.databinding.ActivityVersesDetailsBinding;
import com.gc.bhagavadgita.interfaces.RecyclerItemClickListner;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

public class VersesDetailsActivity extends AppCompatActivity implements RecyclerItemClickListner {
    ActivityVersesDetailsBinding binding;
    List<VersesListResponse> verse;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verses_details);
        setSupportActionBar(binding.toolbar);
        position = getIntent().getIntExtra("position", 0);
        verse = (ArrayList) getIntent().getSerializableExtra("verse");
        setActionBarTitle();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.pager.setAdapter(new VerseDetailstAdapter(this, verse));
        binding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                position = i;
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
        getSupportActionBar().setTitle("अध्याय " + verse.get(position).getChapter_number() + ", श्लोक " + verse.get(position).getVerse_number());
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
        InterstitialAd mInterstitialAd = new InterstitialAd(this);
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial1));
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
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
