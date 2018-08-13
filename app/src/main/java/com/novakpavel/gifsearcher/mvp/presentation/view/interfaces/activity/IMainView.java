package com.novakpavel.gifsearcher.mvp.presentation.view.interfaces.activity;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.novakpavel.gifsearcher.mvp.presentation.view.interfaces.base.IBaseView;

@StateStrategyType(SkipStrategy.class)
public interface IMainView extends IBaseView {
}
