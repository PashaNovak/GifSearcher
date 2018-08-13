package com.novakpavel.gifsearcher.di.modules;

import com.novakpavel.gifsearcher.di.annotations.ActivityScope;
import com.novakpavel.gifsearcher.mvp.presentation.view.implementation.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidSupportInjectionModule.class})
public interface AppActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {ActivityModule.class})
    MainActivity mainActivityInjector();
}
