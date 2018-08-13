package com.novakpavel.gifsearcher.di.modules;

import com.novakpavel.gifsearcher.mvp.model.manager.implementation.ResourceManagerImpl;
import com.novakpavel.gifsearcher.mvp.model.manager.interfaces.IResourceManager;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public interface ManagerModule {

    @Binds
    @Singleton
    IResourceManager provideResourceManager(ResourceManagerImpl resourceManager);
}
