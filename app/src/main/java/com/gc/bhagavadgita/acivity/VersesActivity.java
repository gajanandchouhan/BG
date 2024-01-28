package com.gc.bhagavadgita.acivity;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.gc.bhagavadgita.R;
import com.gc.bhagavadgita.adapter.VersesListAdapter;
import com.gc.bhagavadgita.contract.VerseContract;
import com.gc.bhagavadgita.data.model.ChapterListResponse;
import com.gc.bhagavadgita.data.model.VersesListResponse;
import com.gc.bhagavadgita.databinding.ActivityVersesBinding;
import com.gc.bhagavadgita.interfaces.RecyclerItemClickListner;
import com.gc.bhagavadgita.presenter.VersesPresenter;

import java.util.ArrayList;
import java.util.List;

public class VersesActivity extends BaseActivity<VersesPresenter> implements RecyclerItemClickListner,VerseContract.View {
    ActivityVersesBinding binding;
    List<VersesListResponse> list;
    private int number;
    private VersesListAdapter adapter;
    private ChapterListResponse chapterListResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verses);
        setSupportActionBar(binding.toolbar);
        chapterListResponse= (ChapterListResponse) getIntent().getSerializableExtra("chapter");
        number = chapterListResponse.getChapter_number();
        String name = chapterListResponse.getName();
        getSupportActionBar().setTitle("अध्याय " + number + "- " + name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new VersesListAdapter(this,list);
        binding.recyclerView.setAdapter(adapter);
        presenter.setView(this);
        presenter.getVerses(String.valueOf(number),"1" );
        binding.setChapterDetails(chapterListResponse);
    }

    @Override
    public VersesPresenter initializePresenter() {
        return new VersesPresenter(this);
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
        Intent intent = new Intent(this, VersesDetailsActivity.class);
        intent.putExtra("verse",(ArrayList)list);
        intent.putExtra("position",position);
        startActivity(intent);
    }


    @Override
    public void setVerses(VersesListResponse versesListResponseList) {

    }
}
