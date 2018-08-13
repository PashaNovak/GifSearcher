package com.novakpavel.gifsearcher.mvp.presentation.view.implementation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.novakpavel.gifsearcher.R;
import com.novakpavel.gifsearcher.mvp.presentation.presenter.MainScreenPresenter;
import com.novakpavel.gifsearcher.mvp.presentation.presenter.base.BaseAppPresenter;
import com.novakpavel.gifsearcher.mvp.presentation.view.implementation.activity.base.BaseActivityWithFragments;
import com.novakpavel.gifsearcher.mvp.presentation.view.implementation.fragment.GifsFragment;
import com.novakpavel.gifsearcher.mvp.presentation.view.interfaces.activity.IMainView;

import butterknife.BindView;

public class MainActivity extends BaseActivityWithFragments<MainScreenPresenter> implements IMainView {

    @InjectPresenter
    MainScreenPresenter mainScreenPresenter;

    @Override
    protected MainScreenPresenter getPresenter() {
        return mainScreenPresenter;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public int getRootViewResId() {
        return R.id.r_main_activity;
    }

    public static void start(@Nullable Context context) {
        if (context != null) {
            context.startActivity(new Intent(context, MainActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GifsFragment gifsFragment = GifsFragment.getInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(getRootViewResId(), gifsFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
