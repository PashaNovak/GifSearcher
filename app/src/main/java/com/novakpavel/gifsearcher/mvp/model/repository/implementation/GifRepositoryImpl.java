package com.novakpavel.gifsearcher.mvp.model.repository.implementation;

import com.novakpavel.gifsearcher.constants.IAppConstants;
import com.novakpavel.gifsearcher.mvp.model.repository.dto.GifResponse;
import com.novakpavel.gifsearcher.mvp.model.repository.interfaces.ApiInterface;
import com.novakpavel.gifsearcher.mvp.model.repository.interfaces.IGifRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;

public class GifRepositoryImpl implements IGifRepository {

    private final ApiInterface mApiInterface;
    private final Scheduler mIOscheduler;

    @Inject
    public GifRepositoryImpl(ApiInterface mApiInterface,
                             @Named(IAppConstants.IO_SCHEDULER) Scheduler mIOscheduler) {
        this.mApiInterface = mApiInterface;
        this.mIOscheduler = mIOscheduler;
    }

    @Override
    public Single<GifResponse> getTrendingGifs(int limit, int offset) {
        return mApiInterface.getTrendingGifs(limit, offset)
                .subscribeOn(mIOscheduler);
    }

    @Override
    public Single<GifResponse> getSearchingGifs(String searchQuery, int limit, int offset) {
        return mApiInterface.getSearchingGifs(searchQuery, limit, offset)
                .subscribeOn(mIOscheduler);
    }
}
