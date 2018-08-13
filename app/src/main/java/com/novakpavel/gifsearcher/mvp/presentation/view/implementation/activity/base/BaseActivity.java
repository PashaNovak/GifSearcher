package com.novakpavel.gifsearcher.mvp.presentation.view.implementation.activity.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.novakpavel.gifsearcher.R;
import com.novakpavel.gifsearcher.constants.IAppConstants;
import com.novakpavel.gifsearcher.mvp.model.manager.interfaces.IResourceManager;
import com.novakpavel.gifsearcher.mvp.presentation.presenter.base.BaseAppPresenter;
import com.novakpavel.gifsearcher.mvp.presentation.view.interfaces.base.IBaseView;
import com.novakpavel.gifsearcher.utils.AppLog;
import com.novakpavel.gifsearcher.utils.KeyboardUtil;
import com.novakpavel.gifsearcher.utils.ShowDialogUtil;
import com.novakpavel.gifsearcher.utils.ShowSnackBarUtil;

import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.reactivex.Scheduler;

public abstract class BaseActivity<P extends BaseAppPresenter> extends MvpAppCompatActivity implements IBaseView {

    @Inject
    @Named(IAppConstants.COMPUTATION_SCHEDULER)
    protected Scheduler mComputationScheduler;

    @Inject
    @Named(IAppConstants.IO_SCHEDULER)
    protected Scheduler mIOScheduler;

    @Inject
    @Named(IAppConstants.UI_SCHEDULER)
    protected Scheduler mUIScheduler;

    @Inject
    IResourceManager mIResourcesManager;

    protected abstract P getPresenter();

    @Nullable
    private AlertDialog mDialog;

    @Nullable
    private Snackbar mSnackBar;

    protected final Handler mHandler = new Handler();

    @LayoutRes
    public abstract int getLayoutResId();

    @IdRes
    public abstract int getRootViewResId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppLog.logActivity(this);
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);

        if (getIntent() != null) {
            if (getIntent().getExtras() != null) {
                initFromIntentExtras(getIntent().getExtras());
            }
        }

        initBeforeLayoutAttach();
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initViewBeforePresenterAttach();
        getMvpDelegate().onAttach();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getMvpDelegate().onAttach();
    }

    @Override
    protected void onStart() {
        AppLog.logActivity(this);
        super.onStart();
    }

    @Override
    protected void onResume() {
        AppLog.logActivity(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        AppLog.logActivity(this);
        hideKeyboard();
        super.onPause();
    }

    @Override
    protected void onStop() {
        AppLog.logActivity(this);
        hideProgress();
        hideDialogMessage();
        hideMessage();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        AppLog.logActivity(this);
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getPresenter().onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getPresenter().onActivityResult(requestCode, resultCode, data, this);
    }

    @Override
    public void hideKeyboard() {
        KeyboardUtil.hideKeyboard(this);

    }

    @Override
    public void cancelScreen() {

    }

    @Override
    public void showAutocloseableMessage(@NonNull String message) {
        showMessage(message, true, null, null);
    }

    @Override
    public void showDialogMessage(@NonNull String message, boolean closable) {
        String title = getResources().getString(R.string.app_name);
        showDialogWithOptions(title,
                message,
                null,
                null,
                null,
                null,
                null,
                closable);

    }

    @Override
    public void showDialogWithOptions(@NonNull String title,
                                      @NonNull String message,
                                      @Nullable View elementView,
                                      @Nullable String positiveOptionMessage,
                                      @Nullable String negativeOptionMessage,
                                      @Nullable DialogInterface.OnClickListener positiveListener,
                                      @Nullable DialogInterface.OnClickListener negativeListener,
                                      boolean cancelable) {
        AppLog.logActivity(this);
        hideDialogMessage();
        hideKeyboard();
        mDialog = ShowDialogUtil.showMessageDialog(this,
                title,
                message,
                elementView,
                positiveOptionMessage,
                negativeOptionMessage,
                positiveListener,
                negativeListener,
                cancelable);
    }

    @Override
    public void showMessage(@NonNull String message, boolean closable,
                            @Nullable String actionMessage,
                            @Nullable View.OnClickListener actionListener) {
        hideMessage();
        @Nullable
        View rootView = findViewById(getRootViewResId());
        /* @BaseTransientBottomBar.Duration   */
        int duration = closable ? BaseTransientBottomBar.LENGTH_SHORT : BaseTransientBottomBar.LENGTH_INDEFINITE;
        if (rootView == null) {
            rootView = getWindow().getDecorView();
        }
        mSnackBar = ShowSnackBarUtil.showSnackBar(rootView, this, message, actionMessage, actionListener, duration);

    }

    @Override
    public void hideDialogMessage() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }

    }

    @Override
    public void hideMessage() {
        if (mSnackBar != null) {
            mSnackBar.dismiss();
        }

    }

    @Override
    public void showProgress() {
        showProgress(null);
    }

    @Override
    public void showProgress(@Nullable String messageText) {
        hideProgress();
        String titleText = getResources().getString(R.string.progress_title);
        mDialog = ShowDialogUtil.showProgressDialog(this, titleText, messageText);

    }

    @Override
    public void hideProgress() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    protected void initFromIntentExtras(@NonNull Bundle bundle) {

    }

    protected void initBeforeLayoutAttach() {

    }

    protected void initViewBeforePresenterAttach() {

    }
}
