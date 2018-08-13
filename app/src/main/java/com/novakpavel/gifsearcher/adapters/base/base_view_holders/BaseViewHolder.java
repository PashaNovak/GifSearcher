package com.novakpavel.gifsearcher.adapters.base.base_view_holders;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseViewHolder<ListItem> extends RecyclerView.ViewHolder {

    @Nullable
    protected ListItem mListItem;

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindView(@Nullable ListItem item) {
        mListItem = item;
    }
}
