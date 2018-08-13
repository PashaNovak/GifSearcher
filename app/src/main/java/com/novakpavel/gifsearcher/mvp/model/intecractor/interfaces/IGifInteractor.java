package com.novakpavel.gifsearcher.mvp.model.intecractor.interfaces;

import com.novakpavel.gifsearcher.mvp.model.repository.dto.GifResponse;

import io.reactivex.Single;

public interface IGifInteractor {

    Single<GifResponse> getTrandingGifs(int limit, int offset);

    Single<GifResponse> getSearchingGifs(String searchQuery, int limit, int offset);
}
