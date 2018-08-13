package com.novakpavel.gifsearcher.di.modules;

import com.novakpavel.gifsearcher.mvp.model.intecractor.implementation.GifInteractorImpl;
import com.novakpavel.gifsearcher.mvp.model.intecractor.interfaces.IGifInteractor;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public interface InteractorModule {

    @Binds
    @Singleton
    IGifInteractor provideGifInteractor(GifInteractorImpl gifInteractor);
}
