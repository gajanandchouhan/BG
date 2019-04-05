package com.gc.bhagavadgita.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.bhagavadgita.R;
import com.gc.bhagavadgita.acivity.VersesActivity;
import com.gc.bhagavadgita.adapter.ChapterListAdapter;
import com.gc.bhagavadgita.contract.HomeContract;
import com.gc.bhagavadgita.data.model.ChapterListResponse;
import com.gc.bhagavadgita.databinding.FragmentHomeBinding;
import com.gc.bhagavadgita.interfaces.RecyclerItemClickListner;
import com.gc.bhagavadgita.presenter.HomePresenter;
import com.google.android.gms.ads.AdRequest;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<HomePresenter> implements RecyclerItemClickListner, HomeContract.HomeView {

    FragmentHomeBinding binding;
    List<ChapterListResponse> list;
    private ChapterListAdapter adapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public HomePresenter initializePresenter() {
        return new HomePresenter(getActivity());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new ChapterListAdapter(this, list);
        binding.recyclerView.setAdapter(adapter);
        presenter.setView(this);
        presenter.getChapter();

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), VersesActivity.class);
        intent.putExtra("chapter", list.get(position));
        startActivity(intent);
    }

    @Override
    public void setChapter(List<ChapterListResponse> chapterListResponseList) {

        list.clear();
        list.addAll(chapterListResponseList);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onTokenRefresh() {
        presenter.getChapter();
    }
}
