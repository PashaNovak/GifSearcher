package com.novakpavel.gifsearcher.mvp.presentation.view.implementation.activity;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.novakpavel.gifsearcher.R;
import com.novakpavel.gifsearcher.mvp.presentation.presenter.SplashPresenter;
import com.novakpavel.gifsearcher.mvp.presentation.view.interfaces.activity.ISplashView;

public class SplashActivity extends MvpAppCompatActivity implements ISplashView {

    @InjectPresenter
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_screen);
        splashPresenter.navigateToMainScreen();
    }

    @Override
    public void navigateToMainScreen() {
        MainActivity.start(this);
    }
}
