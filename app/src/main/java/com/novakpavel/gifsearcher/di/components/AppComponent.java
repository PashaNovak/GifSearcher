package com.novakpavel.gifsearcher.di.components;

import com.novakpavel.gifsearcher.App;
import com.novakpavel.gifsearcher.di.modules.AppActivityModule;
import com.novakpavel.gifsearcher.di.modules.ApiModule;
import com.novakpavel.gifsearcher.di.modules.ApplicationModule;
import com.novakpavel.gifsearcher.di.modules.AppFragmentModule;
import com.novakpavel.gifsearcher.di.modules.InteractorModule;
import com.novakpavel.gifsearcher.di.modules.ManagerModule;
import com.novakpavel.gifsearcher.di.modules.ParseModule;
import com.novakpavel.gifsearcher.di.modules.RepositoryModule;
import com.novakpavel.gifsearcher.di.modules.RetrofitModule;
import com.novakpavel.gifsearcher.di.modules.SchedulersModule;
import com.novakpavel.gifsearcher.mvp.presentation.presenter.GifsFragmentPresenter;
import com.novakpavel.gifsearcher.mvp.presentation.presenter.MainScreenPresenter;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppActivityModule.class,
        ApiModule.class,
        AppFragmentModule.class,
        InteractorModule.class,
        ManagerModule.class,
        RepositoryModule.class,
        RetrofitModule.class,
        SchedulersModule.class,
        ParseModule.class,
        ApplicationModule.class})
public interface AppComponent {

    void inject(App app);

    void inject(MainScreenPresenter mainScreenPresenter);

    void inject(GifsFragmentPresenter gifsFragmentPresenter);

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder appContext(App app);
        AppComponent build();
    }
}
