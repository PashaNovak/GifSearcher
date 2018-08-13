package com.novakpavel.gifsearcher.mvp.model.repository.interfaces;

import android.support.annotation.IntRange;

import com.novakpavel.gifsearcher.BuildConfig;
import com.novakpavel.gifsearcher.mvp.model.repository.dto.GifResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("trending")
    Single<GifResponse> getTrendingGifs(@Query("limit") @IntRange(from = 1) int limit,
                                        @Query("offset") @IntRange(from = 0) int offset);

    @GET("search")
    Single<GifResponse> getSearchingGifs(@Query("q") String searchQuery,
                                         @Query("limit") @IntRange(from = 1) int limit,
                                         @Query("offset") @IntRange(from = 0) int offset);
}
