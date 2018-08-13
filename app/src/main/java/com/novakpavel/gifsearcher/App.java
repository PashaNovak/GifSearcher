package com.novakpavel.gifsearcher;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import com.novakpavel.gifsearcher.di.components.AppComponent;
import com.novakpavel.gifsearcher.di.components.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector{

    private static AppComponent component;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    public static AppComponent getAppComponent(){
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger2();
    }

    private void initDagger2(){
        component = DaggerAppComponent.builder()
                .appContext(this)
                .build();

        component.inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
