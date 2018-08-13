package com.novakpavel.gifsearcher.di.modules;

import com.novakpavel.gifsearcher.mvp.model.repository.implementation.GifRepositoryImpl;
import com.novakpavel.gifsearcher.mvp.model.repository.interfaces.IGifRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public interface RepositoryModule {

    @Binds
    @Singleton
    IGifRepository provideGifRepository(GifRepositoryImpl gifRepository);
}
