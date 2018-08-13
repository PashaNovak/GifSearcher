package com.novakpavel.gifsearcher.di.modules;

import com.novakpavel.gifsearcher.di.annotations.FragmentScope;
import com.novakpavel.gifsearcher.mvp.presentation.view.implementation.fragment.GifsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidSupportInjectionModule.class})
public interface AppFragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = {FragmentModule.class})
    GifsFragment gifsFragmentInjector();
}
