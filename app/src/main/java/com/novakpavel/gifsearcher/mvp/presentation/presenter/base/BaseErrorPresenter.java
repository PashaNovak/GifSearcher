package com.novakpavel.gifsearcher.mvp.presentation.presenter.base;

import com.novakpavel.gifsearcher.mvp.presentation.view.interfaces.base.IBaseView;
import com.novakpavel.gifsearcher.utils.AppLog;
import com.novakpavel.gifsearcher.utils.exceptions.GifSearcherException;

import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;

public abstract class BaseErrorPresenter<View extends IBaseView> extends BasePresenter<View> implements Consumer<Throwable> {

    @Override
    public void accept(@Nullable Throwable throwable) throws Exception {
        if (throwable != null){
            throwable.printStackTrace();
        }
        AppLog.logPresenter(this, "ERROR_HANDLING", throwable);
    }

    protected void defaultErrorHandling(@Nullable Throwable throwable){
        if (throwable != null && throwable instanceof Exception){
            GifSearcherException exception = (GifSearcherException) throwable;
            if (exception.getStatus() != null){
                switch (exception.getStatus()){

                }
            } if (exception.getResId() != null){
                getViewState().showAutocloseableMessage(getString(exception.getResId()));
            }
        } else {
            handleUnkbownError(throwable);
        }
    }

    protected void handleUnkbownError(@Nullable Throwable throwable){
        getViewState().showAutocloseableMessage(throwable.getMessage());
    }
}
