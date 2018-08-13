package com.novakpavel.gifsearcher.di.components;

import com.novakpavel.gifsearcher.di.annotations.ActivityScope;
import com.novakpavel.gifsearcher.di.modules.ActivityModule;

import dagger.Component;

@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = {AppComponent.class})
public interface ActivityComponent {
}
