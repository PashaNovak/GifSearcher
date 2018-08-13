package com.novakpavel.gifsearcher.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

import com.novakpavel.gifsearcher.constants.IAppConstants;
import com.novakpavel.gifsearcher.mvp.presentation.view.interfaces.activity.ISplashView;

@InjectViewState
public class SplashPresenter extends MvpPresenter<ISplashView> {

    private PublishSubject<Integer> subject = PublishSubject.create();

    private Disposable disposable = subject.delay(IAppConstants.DELAY_SPLASH_SCREEN, TimeUnit.SECONDS)
            .subscribe(integer -> getViewState().navigateToMainScreen());

    public void navigateToMainScreen() {
        subject.onNext(1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
