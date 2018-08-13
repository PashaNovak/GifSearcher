package com.novakpavel.gifsearcher.mvp.model.manager.implementation.network;

import android.support.annotation.NonNull;

import com.novakpavel.gifsearcher.BuildConfig;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    @Inject
    public HeaderInterceptor(){}

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();

        builder.header("api_key", BuildConfig.GIPHTY_API_KEY);
        return chain.proceed(builder.build());
    }
}
