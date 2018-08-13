package com.novakpavel.gifsearcher.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.novakpavel.gifsearcher.App;
import com.novakpavel.gifsearcher.mvp.presentation.presenter.base.BaseAppPresenter;
import com.novakpavel.gifsearcher.mvp.presentation.view.interfaces.activity.IMainView;

@InjectViewState
public class MainScreenPresenter extends BaseAppPresenter<IMainView>{

    public MainScreenPresenter() {
        App.getAppComponent().inject(this);
    }
}
