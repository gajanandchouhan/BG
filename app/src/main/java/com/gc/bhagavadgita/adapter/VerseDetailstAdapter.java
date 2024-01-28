package com.gc.bhagavadgita.adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.bhagavadgita.data.model.VersesListResponse;
import com.gc.bhagavadgita.databinding.ItemVerseDetailsBinding;
import com.gc.bhagavadgita.databinding.ItemVersesBinding;

import java.util.List;

public class VerseDetailstAdapter extends PagerAdapter {
    private Context mContext;
    List<VersesListResponse> list;

    public VerseDetailstAdapter(Context context, List<VersesListResponse> verse) {
        mContext = context;
        list = verse;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ItemVerseDetailsBinding binding = ItemVerseDetailsBinding.inflate(inflater, container, false);
        binding.setDetails(list.get(position));
        binding.executePendingBindings();
        container.addView(binding.getRoot());
        return binding.getRoot();
    }
}
