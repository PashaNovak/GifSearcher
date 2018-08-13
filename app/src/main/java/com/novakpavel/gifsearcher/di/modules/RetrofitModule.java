package com.novakpavel.gifsearcher.di.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.novakpavel.gifsearcher.BuildConfig;
import com.novakpavel.gifsearcher.constants.IAppConstants;
import com.novakpavel.gifsearcher.mvp.model.manager.implementation.network.HeaderInterceptor;
import com.novakpavel.gifsearcher.utils.AppLog;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    Cache provideCache(Context context) {
        File file = new File(context.getCacheDir(), "RESPONSE");
        return new Cache(file, IAppConstants.CACH_SIZE);
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor( ) {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor((message -> AppLog.logObject(HttpLoggingInterceptor.class, message)));
        httpLoggingInterceptor.
                setLevel(BuildConfig.IS_LOG_ENABLED ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return httpLoggingInterceptor;
    }

    @Provides
    @Singleton
    @Named(IAppConstants.GIFS)
    OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor,
                              Cache cache,
                              HeaderInterceptor headerInterceptor) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(IAppConstants.CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(IAppConstants.WRITE_CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(IAppConstants.READ_CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        if (BuildConfig.IS_LOG_ENABLED) {
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);
        }
        okHttpClientBuilder.retryOnConnectionFailure(true);
        okHttpClientBuilder.cache(cache);
        okHttpClientBuilder.addInterceptor(headerInterceptor);
        return okHttpClientBuilder.build();
    }

    @Provides
    @Singleton
    @Named(IAppConstants.GIFS)
    Retrofit provideNewsRetrofit(@Named(IAppConstants.GIFS)OkHttpClient okHttpClient,
                                 RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                                 GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .baseUrl(BuildConfig.API_URL)
                .build();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory rxJava2CallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }
}
