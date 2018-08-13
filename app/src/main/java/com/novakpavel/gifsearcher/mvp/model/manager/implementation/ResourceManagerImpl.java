package com.novakpavel.gifsearcher.mvp.model.manager.implementation;

import android.content.Context;
import android.support.annotation.NonNull;

import com.novakpavel.gifsearcher.mvp.model.manager.interfaces.IResourceManager;

import javax.inject.Inject;

public class ResourceManagerImpl implements IResourceManager {

    @NonNull
    private final Context mAppContext;

    @Inject
    public ResourceManagerImpl(@NonNull Context appContext){
        mAppContext = appContext;
    }

    @NonNull
    @Override
    public String getString(int strResId) {
        String str = null;
        try {
            str = mAppContext.getString(strResId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str != null ? str : "";
    }

    @NonNull
    @Override
    public String getString(int strResId, @NonNull Object... formatArgs) {
        String str = null;
        try {
            str = mAppContext.getString(strResId, formatArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str != null ? str : "";
    }
}
