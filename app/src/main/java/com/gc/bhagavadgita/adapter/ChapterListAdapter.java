package com.gc.bhagavadgita.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.bhagavadgita.data.model.ChapterListResponse;
import com.gc.bhagavadgita.databinding.ItemChapterBinding;
import com.gc.bhagavadgita.interfaces.RecyclerItemClickListner;

import java.util.List;

public class ChapterListAdapter extends RecyclerView.Adapter<ChapterListAdapter.ViewHolder> {


    private final RecyclerItemClickListner listner;
    List<ChapterListResponse> list;

    public ChapterListAdapter(RecyclerItemClickListner listner, List<ChapterListResponse> list) {
        this.listner=listner;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemChapterBinding itemChapterBinding = ItemChapterBinding.inflate(layoutInflater, viewGroup, false);
        return new ViewHolder(itemChapterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemChapterBinding binding;

        public ViewHolder(@NonNull ItemChapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        public void  bind(){
          binding.setChapter(list.get(getAdapterPosition()));
          binding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            listner.onItemClick(getAdapterPosition());
        }
    }
}
