package com.novakpavel.gifsearcher.mvp.presentation.presenter;

import android.support.annotation.Nullable;

import com.arellomobile.mvp.InjectViewState;
import com.novakpavel.gifsearcher.App;
import com.novakpavel.gifsearcher.mvp.model.intecractor.interfaces.IGifInteractor;
import com.novakpavel.gifsearcher.mvp.presentation.presenter.base.BasePaginationPresenter;
import com.novakpavel.gifsearcher.mvp.presentation.view.interfaces.fragment.IGifsFragment;
import com.novakpavel.gifsearcher.utils.AppLog;

import javax.inject.Inject;

@InjectViewState
public class GifsFragmentPresenter extends BasePaginationPresenter<IGifsFragment> {

    @Inject
    IGifInteractor gifInteractor;

    private String type = "tranding";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GifsFragmentPresenter() {
        App.getAppComponent().inject(this);
    }

    @Override
    protected void loadData(int totalItemCount, @Nullable String lastItemId) {
        super.loadData(totalItemCount, lastItemId);

        if (getType().equals("tranding")) {
            addPaginationDisposable(gifInteractor.getTrandingGifs(getItemsCountPerPage(), getTotalItemCount() == 0 ? 0 : getTotalItemCount() - getItemsCountPerPage())
                    .observeOn(mUIScheduler)
                    .doOnSuccess(dataElement -> getViewState().addData(dataElement.getData()))
                    .doFinally(() -> getViewState().hidePaginationProgress())
                    .subscribe(list -> AppLog.logPresenter(this, "Load Data success"), this));
        } else {
            addPaginationDisposable(gifInteractor.getSearchingGifs(getType(), getItemsCountPerPage(), getTotalItemCount() == 0 ? 0 : getTotalItemCount() - getItemsCountPerPage())
            .observeOn(mUIScheduler)
            .doOnSuccess(dataElement -> {
                if(dataElement.getData().isEmpty()){
                    getViewState().showNoGifsMessage();
                } else {
                    getViewState().skipNoGifsMessage();
                    getViewState().addData(dataElement.getData());
                }
            })
            .doFinally(() -> getViewState().hidePaginationProgress())
            .subscribe(list -> {
                System.out.println(list.getData().toString());
                AppLog.logPresenter(this, "Load Data success");
            }, this));
        }
    }

    public void searchBtnClicked(String searchQuery){
        setType(searchQuery);
        refreshData();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        refreshData();
    }

    @Override
    protected int getItemsCountPerPage() {
        return 10;
    }
}
