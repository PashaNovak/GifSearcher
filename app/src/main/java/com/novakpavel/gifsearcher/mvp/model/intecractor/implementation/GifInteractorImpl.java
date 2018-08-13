package com.novakpavel.gifsearcher.mvp.model.intecractor.implementation;

import com.novakpavel.gifsearcher.mvp.model.intecractor.interfaces.IGifInteractor;
import com.novakpavel.gifsearcher.mvp.model.repository.dto.GifResponse;
import com.novakpavel.gifsearcher.mvp.model.repository.interfaces.IGifRepository;
import com.novakpavel.gifsearcher.utils.exceptions.ErrorHandlingUtil;

import javax.inject.Inject;

import io.reactivex.Single;

public class GifInteractorImpl implements IGifInteractor {

    private final IGifRepository mGifRepository;

    @Inject
    public GifInteractorImpl(IGifRepository mGifRepository) {
        this.mGifRepository = mGifRepository;
    }

    @Override
    public Single<GifResponse> getTrandingGifs(int limit, int offset) {
        return mGifRepository.getTrendingGifs(limit, offset).onErrorResumeNext(ErrorHandlingUtil::defaultHandle);
    }

    @Override
    public Single<GifResponse> getSearchingGifs(String searchQuery, int limit, int offset) {
        return mGifRepository.getSearchingGifs(searchQuery, limit, offset).onErrorResumeNext(ErrorHandlingUtil::defaultHandle);
    }
}
