package com.novakpavel.gifsearcher.adapters.base.base_adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.novakpavel.gifsearcher.mvp.model.repository.dto.Identifiable;

public abstract class BasePaginationRecyclerViewAdapter<Data extends Identifiable, VH extends RecyclerView.ViewHolder>
        extends BaseRecyclerViewAdapter<Data, VH> {

    protected BasePaginationRecyclerViewAdapter(@Nullable OnItemClickListener<Data> onItemClickListener, @Nullable OnItemWithPositionClickListener<Data> onItemWithPositionClickListener) {
        super(onItemClickListener, onItemWithPositionClickListener);
    }

    public @Nullable
    String getLastItemId() {
        return super.getDataList().get(super.getRealItemsCount() - 1).getId();
    }
}
