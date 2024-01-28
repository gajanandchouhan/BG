package com.gc.bhagavadgita.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gc.bhagavadgita.data.model.VersesListResponse;
import com.gc.bhagavadgita.databinding.ItemChapterBinding;
import com.gc.bhagavadgita.databinding.ItemVersesBinding;
import com.gc.bhagavadgita.interfaces.RecyclerItemClickListner;

import java.util.List;

public class VersesListAdapter extends RecyclerView.Adapter<VersesListAdapter.ViewHolder> {


    private final RecyclerItemClickListner listner;
    List<VersesListResponse> list;

    public VersesListAdapter(RecyclerItemClickListner listner, List<VersesListResponse> list) {
        this.listner = listner;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemVersesBinding itemVersesBinding = ItemVersesBinding.inflate(layoutInflater, viewGroup, false);
        return new ViewHolder(itemVersesBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(i);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemVersesBinding binding;

        public ViewHolder(@NonNull ItemVersesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.readButon.setOnClickListener(this);
        }

        public void bind(int position) {
            binding.setVerse(list.get(position));
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            listner.onItemClick(getAdapterPosition());
        }
    }
}
