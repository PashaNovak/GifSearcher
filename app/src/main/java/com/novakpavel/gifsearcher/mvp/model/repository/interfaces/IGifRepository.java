package com.novakpavel.gifsearcher.mvp.model.repository.interfaces;

import com.novakpavel.gifsearcher.mvp.model.repository.dto.GifResponse;

import io.reactivex.Single;

public interface IGifRepository {

    Single<GifResponse> getTrendingGifs(int limit, int offset);

    Single<GifResponse> getSearchingGifs(String searchQuery, int limit, int offset);
}
