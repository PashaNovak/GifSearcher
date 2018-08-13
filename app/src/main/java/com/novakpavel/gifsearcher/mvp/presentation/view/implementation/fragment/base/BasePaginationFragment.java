package com.novakpavel.gifsearcher.mvp.presentation.view.implementation.fragment.base;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.novakpavel.gifsearcher.R;
import com.novakpavel.gifsearcher.adapters.base.base_adapters.BasePaginationRecyclerViewAdapter;
import com.novakpavel.gifsearcher.mvp.presentation.presenter.base.BasePaginationPresenter;
import com.novakpavel.gifsearcher.mvp.presentation.view.interfaces.base.IBasePaginationView;
import com.novakpavel.gifsearcher.utils.RecyclerViewUtil;

import butterknife.BindView;

public abstract class BasePaginationFragment<P extends BasePaginationPresenter> extends BaseFragment<P> implements IBasePaginationView {

    private RecyclerView.LayoutManager layoutManager;

    protected boolean isRecyclerReverse;

    @BindView(R.id.srl_pagination_progress)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rv_pagination_items)
    protected RecyclerView recyclerView;

    private Runnable mEnableRefreshRunnable = () -> {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
    };

    private Runnable mDisableRefreshRunnable = () -> {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    };

    @Override
    protected void initViewBeforePresenterAttach() {
        super.initViewBeforePresenterAttach();
        setLayoutManager();
        setPaginationRecyclerAdapter();
        setScrollListener();
        setRefreshListener();
    }

    protected void setScrollListener() {
        RecyclerViewUtil.setOnScrollListener(recyclerView, getPresenter());
    }

    protected void setIsRecyclerReverse(){};

    @Nullable
    protected abstract BasePaginationRecyclerViewAdapter getBasePaginationRecyclerViewAdapter();

    protected abstract void setPaginationRecyclerAdapter();

    protected abstract void setLayoutManager();

    protected void setRefreshListener() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(() -> getPresenter().refreshData());
    }

    @Override
    public void showPaginationProgress() {
        swipeRefreshLayout.removeCallbacks(mDisableRefreshRunnable);
        swipeRefreshLayout.post(mEnableRefreshRunnable);
    }

    @Override
    public void hidePaginationProgress() {
        swipeRefreshLayout.removeCallbacks(mEnableRefreshRunnable);
        swipeRefreshLayout.post(mDisableRefreshRunnable);
    }

    @Override
    public void removeData() {
        BasePaginationRecyclerViewAdapter basePaginationRecyclerViewAdapter = getBasePaginationRecyclerViewAdapter();
        if (basePaginationRecyclerViewAdapter != null) {
            basePaginationRecyclerViewAdapter.removeAllData();
        }
    }

    @Override
    public void onDestroyView() {
        hidePaginationProgress();
        super.onDestroyView();
    }
}
