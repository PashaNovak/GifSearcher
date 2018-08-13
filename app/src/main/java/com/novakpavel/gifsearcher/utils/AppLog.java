package com.novakpavel.gifsearcher.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.novakpavel.gifsearcher.BuildConfig;
import com.novakpavel.gifsearcher.mvp.presentation.presenter.base.BasePresenter;
import com.novakpavel.gifsearcher.mvp.presentation.view.implementation.fragment.base.BaseFragment;

public abstract class AppLog {

    private static String APP_TAG = "GIFSEARCHER_TAG";

    public static void logPresenter(@NonNull BasePresenter presenter){
        if (isLogEnabled()){
            Log.i(getTag(), getInfo(presenter));
        } else Log.i(getTag(), "isLogEnabled false");
    }

    public static void logPresenter(@NonNull BasePresenter presenter, @NonNull String message) {

        if (isLogEnabled()) {
            Log.i(getTag(), message);
        }
    }
    public static void logPresenter(@NonNull BasePresenter presenter, @NonNull Throwable exception) {

        if (isLogEnabled()) {
            Log.i(getTag(), exception.getMessage());
        }
    }

    public static void logPresenter(@NonNull BasePresenter presenter,
                                    @Nullable String message,
                                    @Nullable Throwable throwable){
        if (isLogEnabled()) {
            Log.i(getTag(), getInfo(presenter) + createMessageFromThrowableAndMessage(message, throwable));
        }
    }

    @NonNull
    private static String createMessageFromThrowableAndMessage(@Nullable String message, @Nullable Throwable throwable) {
        return " " + createMessage(message) + " " + createMessageFromThrowable(throwable);
    }

    @NonNull
    private static String createMessageFromThrowable(@Nullable Throwable throwable) {
        return throwable != null
                ? (throwable.getClass().getName() + " " + createMessage(throwable.getMessage()))
                : "nullable_throwable";
    }

    public static void logFragment(@NonNull BaseFragment fragment){
        if (isLogEnabled()){
            Log.i(getTag(), getInfo(fragment));
        } else Log.i(getTag(), "isLogEnabled false");
    }

    public static void logActivity(@NonNull Activity activity){
        if (isLogEnabled()){
            Log.i(getTag(), getInfo(activity));
        } else Log.i(getTag(), "isLogEnabled false");
    }

    public static void logObject(Class clazz, @Nullable String message) {
        if (isLogEnabled()) {
            Log.i(getTag(), clazz.getSimpleName() + "." + getMethodName() + " " + createMessage(message));
        }
    }

    @NonNull
    private static String createMessage(@Nullable String message) {
        return !TextUtils.isEmpty(message) ? message : "Message is null";
    }

    private static boolean isLogEnabled() {
        return BuildConfig.DEBUG;
    }

    private static String getMethodName() {
        try {
            return Thread.currentThread().getStackTrace()[5].getMethodName();
        } catch (Exception e){
            return "Unknown method";
        }
    }

    private static String getClassName(Object object){
        try{
            return object.getClass().getSimpleName();
        } catch (Exception e){
            return "Unknown class";
        }
    }

    private static String getInfo(Object object){
        return getClassName(object) + "." + getMethodName() + "()";
    }

    private static String getTag(){
        return APP_TAG;
    }
}
