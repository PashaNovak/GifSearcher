package com.novakpavel.gifsearcher.mvp.model.manager.interfaces;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public interface IResourceManager {

    @NonNull
    String getString(@StringRes int strResId);

    @NonNull
    String getString(@StringRes int strResId, @NonNull Object... formatArgs);
}
