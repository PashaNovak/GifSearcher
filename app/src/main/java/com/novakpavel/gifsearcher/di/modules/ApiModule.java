package com.novakpavel.gifsearcher.di.modules;

import com.novakpavel.gifsearcher.constants.IAppConstants;
import com.novakpavel.gifsearcher.mvp.model.repository.interfaces.ApiInterface;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @Singleton
    ApiInterface provideRetrofitApiInterface(@Named(IAppConstants.GIFS) Retrofit retrofit){
        return retrofit.create(ApiInterface.class);
    }
}
