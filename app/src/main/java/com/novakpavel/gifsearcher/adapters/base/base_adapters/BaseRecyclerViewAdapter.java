package com.novakpavel.gifsearcher.adapters.base.base_adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.novakpavel.gifsearcher.utils.AppLog;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<Data, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public interface OnItemClickListener<T>{

        void onItemClicked(T item);
    }

    public interface OnItemWithPositionClickListener<T> {

        void onItemClicked(int position, T item);
    }

    @Nullable
    protected OnItemClickListener<Data> onItemClickListener;

    @Nullable
    protected OnItemWithPositionClickListener<Data> onItemWithPositionClickListener;

    private final List<Data> mDataList = new ArrayList<>();

    protected BaseRecyclerViewAdapter(@Nullable OnItemClickListener<Data> onItemClickListener, @Nullable OnItemWithPositionClickListener<Data> onItemWithPositionClickListener){
        this.onItemClickListener = onItemClickListener;
        this.onItemWithPositionClickListener = onItemWithPositionClickListener;
    }

    public void addData(List<Data> dataItems){
        AppLog.logObject(BaseRecyclerViewAdapter.class, "");
        if (dataItems.size() > 0) {
            int size = mDataList.size();
            this.mDataList.addAll(dataItems);
            notifyItemRangeInserted(size, dataItems.size());
        }
    }

    public void addData(Data item){
        AppLog.logObject(BaseRecyclerViewAdapter.class, "");
        int position = mDataList.size();
        mDataList.add(item);
        notifyItemInserted(position);
    }

    public void addData(int position, Data item) {
        AppLog.logObject(BaseRecyclerViewAdapter.class, "");
        mDataList.add(position, item);
        notifyItemInserted(position);
    }

    public void changeData(int position, Data item) {
        AppLog.logObject(BaseRecyclerViewAdapter.class, "");
        mDataList.set(position, item);
        notifyItemChanged(position);
    }

    public void moveData(int fromPosition, int toPosition) {
        Data tempData = mDataList.get(fromPosition);
        mDataList.remove(fromPosition);
        mDataList.set(toPosition, tempData);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void removeData(int position) {
        AppLog.logObject(BaseRecyclerViewAdapter.class, "");
        mDataList.remove(position);
        notifyItemRemoved(position);
    }

    public void removeData(Data item) {
        AppLog.logObject(BaseRecyclerViewAdapter.class, "");
        int position = mDataList.indexOf(item);
        if (position != -1) {
            mDataList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeAllData() {
        AppLog.logObject(BaseRecyclerViewAdapter.class, "");
        int size = mDataList.size();
        mDataList.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public int getItemCount() {
        AppLog.logObject(BaseRecyclerViewAdapter.class, "");
        return mDataList.size();
    }

    public int getRealItemsCount(){
        AppLog.logObject(BaseRecyclerViewAdapter.class, "");
        return getItemCount();
    }

    protected List<Data> getDataList(){
        AppLog.logObject(BaseRecyclerViewAdapter.class, "");
        return mDataList;
    }
}
