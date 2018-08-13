package com.novakpavel.gifsearcher.mvp.presentation.presenter.base;

import android.support.annotation.Nullable;

import com.novakpavel.gifsearcher.constants.IAppConstants;
import com.novakpavel.gifsearcher.mvp.presentation.view.interfaces.base.IBasePaginationView;
import com.novakpavel.gifsearcher.utils.AppLog;

import java.util.List;

import io.reactivex.disposables.Disposable;

public abstract class BasePaginationPresenter<V extends IBasePaginationView> extends BaseAppPresenter<V> {

    protected boolean mIsNextPageAllow = true;

    @Nullable
    private Disposable mPaginationDisposable = null;

    protected int mTotalItemCount = 0;

    protected String mLastItemId = null;

    @Override
    protected void defaultErrorHandling(Throwable throwable) {
        AppLog.logPresenter(this);
        getViewState().hidePaginationProgress();
        super.defaultErrorHandling(throwable);
    }

    @Override
    protected void clearLiteDisposable() {
        AppLog.logPresenter(this);
        clearPaginationDisposable();
        super.clearLiteDisposable();
    }

    public void loadDataCheck(int totalItemCount,
                              int lastVisibleItemPosition,
                              @Nullable String lastItemId) {
        AppLog.logPresenter(this);
        mTotalItemCount = totalItemCount;
        if (lastItemId != null) {
            mLastItemId = lastItemId;
        }
        if (lastVisibleItemPosition + getItemsCountPerPage() / 3 > mTotalItemCount
                && mIsNextPageAllow
                && !isLoading()) {
            loadData(mTotalItemCount, mLastItemId);
        }
    }

    protected void addPaginationDisposable(@Nullable Disposable disposable) {
        AppLog.logPresenter(this);
        if (disposable != null) {
            clearPaginationDisposable();
            mPaginationDisposable = disposable;
        }
    }

    protected void clearPaginationDisposable() {
        AppLog.logPresenter(this);
        if (isLoading()) {
            if (mPaginationDisposable != null){
                mPaginationDisposable.dispose();
            }
        }
    }

    private boolean isLoading() {
        AppLog.logPresenter(this);
        return mPaginationDisposable != null && !mPaginationDisposable.isDisposed();
    }

    public void refreshData() {
        AppLog.logPresenter(this);
        mIsNextPageAllow = true;
        mTotalItemCount = 0;
        mLastItemId = null;
        clearPaginationDisposable();
        getViewState().removeData();
        loadData(mTotalItemCount, null);
    }

    protected void loadData(int totalItemCount, @Nullable String lastItemId) {
        AppLog.logPresenter(this);
        getViewState().showPaginationProgress();
    }

    public String getmLastItemId() {
        return mLastItemId;
    }

    protected int getItemsCountPerPage() {
        return IAppConstants.DEFAULT_ITEMS_COUNT_PER_PAGE;
    }

    protected void setNextPageAllow(@Nullable List list) {
        mIsNextPageAllow = !(list == null || (list.size() < getItemsCountPerPage()));
    }

    protected int getTotalItemCount(){
        return mTotalItemCount;
    }

    protected int getPaginationPage() {
        return mTotalItemCount / getItemsCountPerPage() + 1;
    }
}
