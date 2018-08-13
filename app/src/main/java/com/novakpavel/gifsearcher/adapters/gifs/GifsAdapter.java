package com.novakpavel.gifsearcher.adapters.gifs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.novakpavel.gifsearcher.R;
import com.novakpavel.gifsearcher.adapters.base.base_adapters.BasePaginationRecyclerViewAdapter;
import com.novakpavel.gifsearcher.adapters.base.base_view_holders.BaseViewHolder;
import com.novakpavel.gifsearcher.mvp.model.repository.dto.Datum;

public class GifsAdapter extends BasePaginationRecyclerViewAdapter<Datum, BaseViewHolder>{

    public GifsAdapter(@Nullable OnItemClickListener<Datum> onItemClickListener,
                          @Nullable OnItemWithPositionClickListener<Datum> onItemWithPositionClickListener) {
        super(onItemClickListener, onItemWithPositionClickListener);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gif, parent, false);
        return new GifsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindView(getDataList().get(position));
    }
}
